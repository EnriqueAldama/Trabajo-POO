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
        Order order = c.getOrder(); // No hay Order de momento en context
        TranslatorManager t = c.getTranslator();
        UrjcBankServer bank = new UrjcBankServer();

        String orderText = order.getOrderText();
        int totalAmount = order.getTotalAmount();
        float totalAmountFloat = ((float) order.getTotalAmount()) / 100;

        // int totalAmount = 99;

        // configureScreenButtons(k,t);

        configureScreenButtons(sk);
        sk.setDescription(orderText + "\n Total: " + String.valueOf(totalAmountFloat)
                + " € \n Introduce la tarjeta de credito para confirmar el pedido o pulsa alguno de los botones inferiores");

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
                    int newOrderNumber;

                    try {
                        newOrderNumber = incrementOrderNumber(); // #Hay que gestionar esto mejor con un Throw hacia
                                                                 // arriba
                    } catch (IOException e) {
                        System.out.println("Archivo no encontrado");
                        newOrderNumber = -1;
                    } // hay que implementar este metodo

                    try {
                        writeOrderToFile(order, newOrderNumber);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ArrayList<String> ticketStringList = new ArrayList<>(); // **Encpasulac bien?? */

                    ticketStringList.add(String.valueOf(newOrderNumber));
                    ticketStringList.add("-----------------------------------");
                    ticketStringList.add(orderText);
                    ticketStringList.add("-----------------------------------");
                    ticketStringList.add(String.valueOf(totalAmountFloat) + " €");

                    // Habria que añadir texto auxiliares traducidos

                    sk.print(ticketStringList); // imprimimos ticket con ifnormacion. Hay que pasar lista de Strings

                    // boolean opStatus;

                    try {
                        bank.doOperation(creditCardNumb, totalAmount);

                    } catch (CommunicationException e) {
                        // Auto-generated catch block
                        // e.printStackTrace();
                        sk.clearScreen();
                        sk.setMessageMode();
                        sk.setDescription("Error: no se pudo efectuar el pago");
                        sk.waitEvent(1);
                        // return new WelcomeScreen();

                        // Añadir boton de vovler a welcomeScreen??
                    }

                    sk.expelCreditCard(12); // el int no se cual meter, lo elijo arbitrariamente

                    // return new WelcomeScreen(); se vuelve a pantalla de inicio
                    // operac realizada

                } // comm available

                else {
                    sk.clearScreen();
                    sk.setMessageMode();
                    sk.setDescription("Error: no se pudo establecer conexion con el servidor");
                    sk.waitEvent(1);
                    // return new WelcomeScreen();

                } // comm not available

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

        k.setOption('A', "Modificar pedido");
        k.setOption('B', "Cancelar pago");

    }

    private void writeOrderToFile(Order Order, int orderNumber) throws IOException {

        int number = orderNumber;

        File FicheroCocina = new File("COMANDAS\\KitchenOrders.txt");

        /*
         * catch(FileNotFoundException e)
         * {
         * File FicheroCocina;
         * FicheroCocina.createNewFile();
         * System.out.println("listado de cocina no encontrado, creando uno nuevo");
         * }
         */

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limit = LocalDateTime.now().withHour(5).withMinute(0);
        LocalDateTime ultimaMod = Instant.ofEpochMilli(FicheroCocina.lastModified())
                .atZone(ZoneId.systemDefault()) // uso la zona horaria por defecto
                .toLocalDateTime();
        if (ultimaMod.isBefore(limit) && now.isAfter(limit)) {
            FicheroCocina.delete();
            FicheroCocina.createNewFile();

        }

        BufferedWriter buff = new BufferedWriter(new FileWriter(FicheroCocina, true));

        buff.write("Numero de pedido: " + Integer.toString(number));
        buff.newLine();
        buff.write("Productos: ");
        buff.newLine();
        buff.write(Order.getOrderText());
        buff.newLine();
        buff.close();
    }
}