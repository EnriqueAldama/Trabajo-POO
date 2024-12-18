/*
 * bhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;
import Manager.SimpleKiosk;
import Manager.TranslatorManager;
import java.util.ArrayList;

import javax.naming.CommunicationException;

import products.Order;
import urjc.UrjcBankServer;

/**
 *
 * @author Alfa
 */
public class PaymentScreen implements KioskScreen {

    @Override
    public KioskScreen show(Context c) {

        //Metodo principal de la clase, desde el cual se produce la totalidad del proceso de pago

        SimpleKiosk sk = c.getKiosk();
        //Order order = c.getOrder(); //No hay Order de momento en context
        TranslatorManager t = c.getTranslator();
        UrjcBankServer bank = new UrjcBankServer();

        System.out.println("Hola mundo");
        
        
        //String orderText = order.getOrderText();
        //int totalAmount = order.getTotalAmount();
        String orderText = "ordertext";
        int totalAmount = 99;

        //configureScreenButtons(k,t);

        configureScreenButtons(sk);
        //sk.setDescription("Introduce la tarjeta de credito para confirmar el pedido o pulsa alguno de los botones inferiores" + orderText + String.valueOf(totalAmount));   //Hecho acorde a enunc pract diseño

        char response = sk.waitEvent(30);

        switch (response){
            case 'A' -> {
                //Boton modifiar pedido. Devolvemos pantalla de menu
                return new OrderScreen(); // antes menuScreen
            }

            case 'B' -> {
                //Boton cancelar pedido. devolv a pantalla bienvenida
                return new IdiomScreen(); //antes welcomeScreen
            }

            case '1' -> {
                //se detecta la tarjeta de credito
                
                sk.retainCreditCard(false); //false, puede ser expulsada con expelCreditCard/()
                long creditCardNumb = sk.getCardNumber();

                if (bank.comunicationAvaiable() == true){
                    int newOrderNumber = incrementOrderNumber(); //hay que implementar este metodo

                    writeOrderToFile(); //hay que implementar este metodo

                    ArrayList <String> ticketStringList = new ArrayList<>(); //**Encpasulac bien?? */

                    ticketStringList.add(orderText);
                    ticketStringList.add(String.valueOf(totalAmount));

                    sk.print(ticketStringList); //imprimimos ticket con ifnormacion. Hay que pasar lista de Strings

                    //boolean opStatus;

                    try {
                        bank.doOperation(creditCardNumb, totalAmount);
                    } catch (CommunicationException e) {
                        // Auto-generated catch block
                        e.printStackTrace();
                        sk.setDescription("Error: no se puedo efectuar el pago");
                        //Añadir boton de vovler a welcomeScreen??
                    }
                    
                    sk.expelCreditCard(12); //el int no se cual meter, lo elijo arbitrariamente

                    return new WelcomeScreen(); //se vuelve a pantalla de inicio
                    //operac realizada

                } //comm available
                
                else { 
                    sk.setDescription("Error: no se pudo establecer conexion con el servidor");

                }//comm not available
                return new WelcomeScreen();
            }

            default -> {
                return this;
            }


        }       
    }

    private void writeOrderToFile() {

    }

    private int incrementOrderNumber() {
        return 10;
    }

    
    private void configureScreenButtons(SimpleKiosk k) {
        

        k.clearScreen();
        k.setMenuMode();
        k.setTitle("Introduce la tarjeta de crédito");

        k.setOption('A', "Modificar pedido");
        k.setOption('B', "Cancelar pago");

        
    }

}