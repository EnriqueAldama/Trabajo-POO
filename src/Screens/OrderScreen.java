/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;
import Manager.SimpleKiosk;
import Manager.TranslatorManager;

/**
 *
 * @author Alfa
 */
public class OrderScreen implements KioskScreen {
    
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k=c.getKiosk();
        TranslatorManager t=c.getTranslator();
        configureScreenButtons(k,t);
        char response=k.waitEvent(30);
        switch (response){
            case 'A': 
                return new MenuScreen(c);
            case 'B':
             return new SectionScreen(c);
            case 'C':
                return new PaymentScreen(c);
            case 'D':
                return new ConfirmCancelScreen(c);
            default:
                return this;    
        }       
    }
    
    private void configureScreenButtons(SimpleKiosk k, TranslatorManager t) {
        k.clearScreen();
        k.setMenuMode('0');
        String optA=t.translate("Añadir menú a pedido");
        String optB=t.translate("Añadir producto individual a pedido");
        String optC=t.translate("Finalizar pedido y pagar");
        String optD=t.translate("Cancelar pedido");
        k.setOption('A', optA);
        k.setOption('B', optB);
        k.setOption('C', optC);
        k.setOption('D', optD);
    }
}
