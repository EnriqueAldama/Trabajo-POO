/*
 * bhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;
import Manager.SimpleKiosk;
import Manager.TranslatorManager;
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
        Order order = c.getOrder();
        TranslatorManager t = c.getTranslator();
        UrjcBankServer bank = new UrjcBankServer();

        sk.setMenuMode();
        
        String orderText = order.getOrderText();
        int totalAmount = order.getTotalAmount();

        //configureScreenButtons(k,t);

        configureScreenButtons(sk);
        char response = sk.waitEvent(30);

        switch (response){
            case 'A':           //Boton modifiar pedido. Devolvemos pantalla de menu
                return new OrderScreen(); // antes menuScreen
            break;

            case 'B':           //Boton cancelar pedido. devolv a pantalla bienvenida
                return new IdiomScreen(); //antes welcomeScreen
            break;

            case '1': //se detecta la tarjeta de credito

                sk.retainCreditCard(false); //false, puede ser expulsada con expelCreditCard/()
                long creditCardNumb = sk.getCardNumber();

                if (bank.communicationAvailable() == true){
                    newOrderNumber = incrementOrderNumber(); //hay que implementar este metodo

                    writeOrderToFile(); //hay que implementar este metodo
                    print(orderText + " - Total " + totalAmount + " €"); //imprimimos ticket con ifnormacion

                    boolean opStatus = bank.doOperation(creditCardNumb);
                    if opStatus {
                        expelCreditCard(12); //el int no se cual meter, lo elijo arbitrariamente

                        return new WelcomeScreen(); //se vuelve a pantalla de inicio
                    }//operac realizada
                    else{
 

                        sk.setDescription("Error: no se puedo efectuar el pago");
                    }//operac no realiz

                } //comm available

                else { 
                    sk.setDescription("Error: no se pudo establecer conexion con el servidor");

                }//comm not available
            break;

            default:
                return this;

        

        }       
    }

    private void writeOrderToFile() {

    }

    private int incrementOrderNumber() {

    }

    private void configureScreenButtons(SimpleKiosk k) {
        k.clearScreen();
        k.setTitle("Introduce la tarjeta de crédito");

        setOption('A', "Modificar pedido");
        setOption('B', "Cancelar pago");

        k.setDescription("Introduce la tarjeta de credito para 
                 confirmar el pedido o pulsa alguno de los botones inferiores" 
                 + ordertext + String.valueOf(totalAmount));   //Hecho acorde a enunc pract diseño
    }

}