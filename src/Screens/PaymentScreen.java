/*
 * bhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;
import Manager.SimpleKiosk;
import Manager.TranslatorManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.naming.CommunicationException;

import products.Order;
import urjc.UrjcBankServer;

/**
 *
 * @author Alfa
 */
public class PaymentScreen implements KioskScreen {

    @Override // HABRIA QUE AÑADIR UN THROW IOEXCEPTION POR SI NO SE ENCUENTRA FICHEROS Y
              // PONER TRY Y CATCH EN CREACION DE PANTALLA DE PAGO

    public KioskScreen show(Context c) {

        // Metodo principal de la clase, desde el cual se produce la totalidad del
        // proceso de pago

        SimpleKiosk sk = c.getKiosk();
        Order order = c.getOrder();
        TranslatorManager t = c.getTranslator();
        UrjcBankServer bank = new UrjcBankServer();

        String orderText = order.getOrderText();
        int totalAmount = order.getTotalAmount();
        float totalAmountFloat = ((float) order.getTotalAmount()) / 100;

        configureScreenButtons(sk);
        sk.setDescription(orderText + "\n Total: " + String.valueOf(totalAmountFloat)
                + " € \n" + t.translate(
                        "Introduce la tarjeta de credito para confirmar el pedido o pulsa alguno de los botones inferiores"));

        char response = sk.waitEvent(30);

        switch (response) {
            case 'A' -> {
                // Boton modifiar pedido. Devolvemos pantalla de menu
                return new OrderScreen(); // antes menuScreen
            }

            case 'B' -> {
                // Boton cancelar pedido. devolv a pantalla bienvenida
                return new WelcomeScreen(); // antes welcomeScreen
            }

            case '1' -> {
                // se detecta la tarjeta de credito

                sk.retainCreditCard(false); // false, puede ser expulsada con expelCreditCard/()
                long creditCardNumb = sk.getCardNumber();

                if (bank.comunicationAvaiable() == true) {
                    // *HAY CONEX CON SERVIDOR
                    int newOrderNumber;

                    // NUM PEDIDO**
                    try {
                        newOrderNumber = incrementOrderNumber(); // #Hay que gestionar esto mejor con un Throw hacia
                                                                 // arriba
                    } catch (IOException e) {
                        System.out.println("Archivo no encontrado"); // error de la maquina. Llamar a operario
                        newOrderNumber = -1;
                    } // hay que implementar este metodo

                    // AÑADIR AL LISTADO DE COCINA**
                        writeOrderToFile(order, newOrderNumber);
                    // TICKET****

                    ArrayList<String> ticketStringList = new ArrayList<>(); // **Encpasulac bien?? */

                    ticketStringList.add("nº " + String.valueOf(newOrderNumber));
                    ticketStringList.add("-----------------------------------");
                    ticketStringList.add(orderText);
                    ticketStringList.add("-----------------------------------");
                    ticketStringList.add(String.valueOf(totalAmountFloat) + " €");

                    // Habria que añadir texto auxiliares traducidos

                    sk.print(ticketStringList); // imprimimos ticket con ifnormacion. Hay que pasar lista de Strings

                    // PAGO****

                    try {
                        bank.doOperation(creditCardNumb, totalAmount);
                        sk.clearScreen();
                        sk.setMessageMode();
                        sk.setDescription("Pago completado con éxito. \nRecoja el ticket por favor\n Número de pedido: "
                                + String.valueOf(newOrderNumber));
                        sk.waitEvent(1);

                    } catch (CommunicationException e) {

                        sk.clearScreen();
                        sk.setMessageMode();
                        sk.setDescription("Error: no se pudo efectuar el pago");
                        sk.waitEvent(1);

                    }

                } // **END HAY CONEX SERV

                else { // *NO HAY CONEXION CON SERVIDOR
                    sk.clearScreen();
                    sk.setMessageMode();
                    sk.setDescription("Error: no se pudo establecer conexion con el servidor");
                    sk.waitEvent(1);
                    // return new WelcomeScreen();

                }

                sk.expelCreditCard(12);
                return new WelcomeScreen(); // Pase lo que pase se vuelve a WelcomeScreen

            } // final case 1

            default -> {
                return this;
            }

        }
    }

    private int incrementOrderNumber() throws IOException {

        String rutaArchivo = "COMANDAS\\numTicket.txt";

        FileReader in = new FileReader(rutaArchivo); // Usamos buffered Reader para no tener que ir leyendo uno a uno
                                                     // los caracteres
        BufferedReader bufr = new BufferedReader(in); // default size. Pasamos el fileReader
        String linea;
        int numTicket = 0;
        linea = bufr.readLine(); // leemos la linea con el numero de ticket
        numTicket = Integer.valueOf(linea) + 1; // lo incrementamos en 1 y si es 100 lo ponemos a 0

        if (numTicket == 100) {
            numTicket = 0;
        }

        FileWriter out = new FileWriter(rutaArchivo); // Ahora escribimos la linea con el num ticket actualiz
        BufferedWriter bufw = new BufferedWriter(out);
        linea = Integer.toString(numTicket);
        bufw.write(linea);

        bufr.close();
        bufw.close();

        return numTicket;
    }

    private void configureScreenButtons(SimpleKiosk k) {

        k.clearScreen();
        k.setMessageMode();
        k.setTitle("Introduce la tarjeta de crédito");

        k.setOption('A', "Modificar pedido"); // se traducen solos desde SimpleKiosk
        k.setOption('B', "Cancelar pago");

    }

    private void writeOrderToFile(Order Order, int orderNumber){

        int number = orderNumber;
        File FicheroCocina = new File("COMANDAS\\KitchenOrders.txt");
        if (!FicheroCocina.exists()) {
            
            System.out.println("Listado de cocina no encontrado, creando uno nuevo");
            try{
                FicheroCocina.createNewFile();
            }
            catch(IOException e){
                System.out.println("No se puede comunicar el pedido a la cocina, pida ayuda a un empleado");
            }
        }
        //La siguiente seccion obtiene la hora actual, la de ultima modificacion y establece el limite
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limit = LocalDateTime.now().withHour(5).withMinute(0);
        LocalDateTime ultimaMod = Instant.ofEpochMilli(FicheroCocina.lastModified())
                .atZone(ZoneId.systemDefault()) // uso la zona horaria por defecto
                .toLocalDateTime();
        //Si la ultima edicion fue antes que el instante limite y ahora es despues hemos pasado por la hora de reinicio
        if (ultimaMod.isBefore(limit) && now.isAfter(limit)) {
            String fechaAyer = now.minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            File FicheroAntiguo=new File("COMANDAS\\KitchenOrders_"+fechaAyer+".txt");
            FicheroCocina.renameTo(FicheroAntiguo);
            try{
                FicheroCocina.createNewFile();
            }catch(IOException f){
                System.out.println("algo ha salido mal, pida ayuda a un empleado");
        }
        }
        try{
        BufferedWriter buff = new BufferedWriter(new FileWriter(FicheroCocina, true)); // true para añadir despues de lo

        buff.write("Numero de pedido: " + Integer.toString(number));
        buff.newLine();
        buff.write("Productos: ");
        buff.newLine();
        buff.write(Order.getOrderText());
        buff.newLine();
        buff.close();
        } catch(IOException g){
            System.out.println("ha habido un error");
        }
    }
}