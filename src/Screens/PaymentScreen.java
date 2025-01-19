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
 * Pantalla de pago.
 * Se accede desde la pantalla de pedido y se vuelve a la pantalla de pedido, o
 * a la pantalla de bienvenida
 */

public class PaymentScreen implements KioskScreen {

    @Override // HABRIA QUE AÑADIR UN THROW IOEXCEPTION POR SI NO SE ENCUENTRA FICHEROS Y
              // PONER TRY Y CATCH EN CREACION DE PANTALLA DE PAGO

    /**
     * Se realiza el pago, se actualiza el numero de pedido, se
     * imprime ticket y se anota en el listado de cocina
     * Dependiendo de la opcion elegida se vuelve a pantalla de bienvenida o de
     * pedido
     * 
     * @param Context
     * @return siguiente pantalla
     */

    public KioskScreen show(Context context) {

        // Metodo principal de la clase, desde el cual se produce la totalidad del
        // proceso de pago

        SimpleKiosk kiosk = context.getKiosk();
        Order order = context.getOrder();
        TranslatorManager translator = context.getTranslator();
        UrjcBankServer bank = new UrjcBankServer();

        String orderText = order.getOrderText();
        int totalAmount = order.getTotalAmount();
        float totalAmountFloat = ((float) order.getTotalAmount()) / 100;

        configureScreenButtons(kiosk);
        kiosk.setDescription(
                orderText +
                        "\n Total: " +
                        String.valueOf(totalAmountFloat) +
                        " € \n" +
                        translator.translate(
                                "Introduce la tarjeta de credito para confirmar el pedido o pulsa alguno de los botones inferiores"));

        char response = kiosk.waitEvent(30);

        switch (response) {
            case 'A' -> {
                // Boton modifiar pedido. Devolvemos a pantalla de menu
                return new OrderScreen(); // antes menuScreen
            }

            case 'B' -> {
                // Boton cancelar pedido. Devolvemos a pantalla bienvenida
                return new WelcomeScreen(); // antes welcomeScreen
            }

            case '1' -> {
                // Se detecta la tarjeta de credito

                kiosk.retainCreditCard(false); // false, puede ser expulsada con expelCreditCard()
                long creditCardNumb = kiosk.getCardNumber();

                if (bank.comunicationAvaiable() == true) {
                    // HAY CONEXIÓN CON SERVIDOR
                    int newOrderNumber;

                    // NUM PEDIDO
                    try {
                        newOrderNumber = incrementOrderNumber();
                    } catch (IOException e) {
                        System.out.println("Archivo no encontrado"); // Error de la maquina. Llamar a operario
                        newOrderNumber = -1;
                    }

                    // AÑADIR AL LISTADO DE COCINA
                    writeOrderToFile(order, newOrderNumber);

                    // TICKET
                    ArrayList<String> ticketStringList = new ArrayList<>();
                    ticketStringList.add("nº " + String.valueOf(newOrderNumber));
                    ticketStringList.add("-----------------------------------");
                    ticketStringList.add(orderText);
                    ticketStringList.add("-----------------------------------");
                    ticketStringList.add(String.valueOf(totalAmountFloat) + " €");

                    kiosk.print(ticketStringList); // imprimimos ticket con informacion

                    // PAGO
                    try {
                        bank.doOperation(creditCardNumb, totalAmount);
                        kiosk.clearScreen();
                        kiosk.setMessageMode();
                        kiosk.setDescription(
                                translator.translate("Pago completado con éxito.\n")
                                        + translator.translate("Recoja el ticket por favor\n")
                                        + translator.translate("Número de pedido: ")
                                        + String.valueOf(newOrderNumber));
                        kiosk.waitEvent(1);

                    } catch (CommunicationException e) {
                        kiosk.clearScreen();
                        kiosk.setMessageMode();
                        kiosk.setDescription("Error: no se pudo efectuar el pago");
                        kiosk.waitEvent(1);
                    }

                }
                // NO HAY CONEXION CON SERVIDOR
                else {
                    kiosk.clearScreen();
                    kiosk.setMessageMode();
                    kiosk.setDescription("Error: no se pudo establecer conexion con el servidor");
                    kiosk.waitEvent(1);
                }
                kiosk.expelCreditCard(20);
                return new WelcomeScreen(); // Pase lo que pase se vuelve a WelcomeScreen
            }

            default -> {
                return this;
            }

        }
    }

    /**
     * Incrementa el numero de pedido en el archivo en el que se guarda y lo
     * devuelve
     * 
     * @return el numero del pedido
     * @throws IOException
     */

    private int incrementOrderNumber() throws IOException {

        String rutaArchivo = "COMANDAS\\numTicket.txt";

        FileReader file = new FileReader(rutaArchivo);
        BufferedReader bufferReader = new BufferedReader(file);
        String line;
        int numTicket = 0;
        line = bufferReader.readLine(); // leemos la linea con el numero de ticket
        numTicket = Integer.valueOf(line) + 1; // lo incrementamos en 1 y si es 100 lo ponemos a 0

        if (numTicket == 100) {
            numTicket = 0;
        }

        FileWriter output = new FileWriter(rutaArchivo); // Ahora escribimos la linea con el num ticket actualizado
        BufferedWriter bufferWriter = new BufferedWriter(output);
        line = Integer.toString(numTicket);
        bufferWriter.write(line);

        bufferReader.close();
        bufferWriter.close();

        return numTicket;
    }

    /**
     * Se configuran los botones, el modo y el titulo
     * En este caso, son botones de modificar pedido y cancelar pago
     * 
     * @param SimpleKiosk
     */

    private void configureScreenButtons(SimpleKiosk kiosk) {
        kiosk.clearScreen();
        kiosk.setMessageMode();
        kiosk.setTitle("Introduce la tarjeta de crédito");
        kiosk.setOption('A', "Modificar pedido");
        kiosk.setOption('B', "Cancelar pago");
    }

    /**
     * Anota la informacion del pedido en el arhivo de texto correspondiente al
     * listado de cocina
     * 
     * @param Order
     * @param orderNumber
     */

    private void writeOrderToFile(Order Order, int orderNumber) {

        int number = orderNumber;
        File kitchenOrders = new File("COMANDAS\\KitchenOrders.txt");

        // CHECK PARA CREAR EL kitchenOrders
        if (!kitchenOrders.exists()) {
            System.out.println("Listado de cocina no encontrado, creando uno nuevo");
            try {
                kitchenOrders.createNewFile();
            } catch (IOException e) {
                System.out.println("No se puede comunicar el pedido a la cocina, pida ayuda a un empleado");
            }
        }

        // CHECK PARA RESETEAR EL kitchenOrders A LAS 5AM
        // Se obtiene la hora actual, la de ultima modificacion y la del limite (5AM)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limit = LocalDateTime.now().withHour(5).withMinute(0);
        LocalDateTime lastOrder = Instant.ofEpochMilli(kitchenOrders.lastModified())
                .atZone(ZoneId.systemDefault()) // uso la zona horaria por defecto
                .toLocalDateTime();

        // Si el último pedido fue antes del limite (5AM) y ahora es despues se resetea
        // kitchenOrders
        if (lastOrder.isBefore(limit) && now.isAfter(limit)) {

            String fechaAyer = now.minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            File FicheroAntiguo = new File("COMANDAS\\KitchenOrders_" + fechaAyer + ".txt");
            kitchenOrders.renameTo(FicheroAntiguo);
            try {
                kitchenOrders.createNewFile();
            } catch (IOException f) {
                System.out.println("Algo ha salido mal, pida ayuda a un empleado");
            }
        }

        // CUANDO PASE TODOS LOS CHECKS, SE ESCRIBE EL NUEVO PEDIDO
        try {
            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(kitchenOrders, true)); // true para añadir
                                                                                                   // despues
            // de lo que haya escrito

            bufferWriter.write("Numero de pedido: " + Integer.toString(number));
            bufferWriter.newLine();
            bufferWriter.write("Productos: ");
            bufferWriter.newLine();
            bufferWriter.write(Order.getOrderText());
            bufferWriter.newLine();
            bufferWriter.close();
        } catch (IOException g) {
            System.out.println("ha habido un error");
        }
    }
}