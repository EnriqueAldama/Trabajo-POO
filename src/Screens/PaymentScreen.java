/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;
import Manager.Context;
import Manager.SimpleKiosk;
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

        setOption('A', "Modificar pedido");
        setOption('B', "Cancelar pago");

        
        String orderText = order.getOrderText();
        int totalAmount = order.getTotalAmount();

        sk.setDescription("Introduce la tarjeta de credito para 
                        confirmar el pedido o pulsa alguno de los botones inferiores" 
                        + ordertext + String.valueOf(totalAmount));   //Hecho acorde a enunc pract diseño

        //configureScreenButtons(k,t);

        char response = k.waitEvent(30);
        SimpleKiosk kiosk = c.getKiosk();
        kiosk.clearScreen();
        configureScreenButtons(kiosk);
        char response = kiosk.waitEvent(30);
        switch (response){
            case 'A':           //Boton modifiar pedido. Devolvemos pantalla de menu
                return new MenuScreen(c);

            case 'B':           //Boton cancelar pedido. devolv a pantalla bienvenida
             return new WelcomeScreen(c);

            case 'A': 
                return new OrderScreen();
            case 'B':
             return new IdiomScreen();
            default:
                return this;

            case '1': //se detecta la tarjeta de credito

                sk.retainCard(false); //false, puede ser expulsada con expelCreditCard/()
                long creditCardNumb = sk.getCardNumber;

                if (bank.communicationAvailable() == true){
                    newOrderNumber = incrementOrderNumber(); //hay que implementar este metodo

                    writeOrderToFile(); //hay que implementar este metodo
                    print(orderText + " - Total " + totalAmount + " €"); //imprimimos ticket con ifnormacion

                    boolean opStatus = bank.doOperation(creditCardNumb);
                    if opStatus {
                        expelCreditCard(12); //el int no se cual meter, lo elijo arbitrariamente

                        return new WelcomeScreen(c);

                    }
                    else{
                        

                    }





                }

        

        }       
    }
    
    private void configureScreenButtons(SimpleKiosk k) {
        k.clearScreen();
        k.setTitle("Introduce la tarjeta de crédito");
        k.setDescription("RESUMEN DEL PEDIDO, NO IMPLEMENTADO");
    }
}
