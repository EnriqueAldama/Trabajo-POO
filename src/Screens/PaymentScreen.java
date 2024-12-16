/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

/**
 * 17/12/24
 * @author Alfa
 */
public class PaymentScreen {
    

    @Override
    public KioskScreen show(Context c) {

        //Metodo principal de la calse, desde el cual se produce la totalidad del proceso de pago

        SimpleKiosk k=c.getKiosk();
        TranslatorManager t=c.getTranslator();
        configureScreenButtons(k,t);
        char response=k.waitEvent(30);
        switch (response){
            case 'A':           //modifiar pedido. Devolvemos pantalla de menu
                return new MenuScreen(c);
            case 'B':           //cancelar pedido
             return new WelcomeScreen(c);
            default:
                return this;
        }       






    }

    //Metodos privados auxiliares

    private void configureScreenButtons(SimpleKiosk k, TranslatorManager t) {
        /*k.clearScreen();
        k.setMenuMode(2);
        String optA=t.translate("Modificar pedido"); //añadimos strings de las opciones traducidos
        String optB=t.translate("Cancelar pago");
        k.setOption('A', optA);
        k.setOption('B', optB);*/
    }

    private void writeOrderToFile(){


    }

    private int incrementOrderNumber(){


    }

}
