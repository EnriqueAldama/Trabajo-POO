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
public class WelcomeScreen implements KioskScreen {

   
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k=c.getKiosk();
        TranslatorManager t=c.getTranslator();
        configureScreenButtons(k,t);
        char response=k.waitEvent(30);
        switch (response){
            case 'A': 
                return new OrderScreen(c);
            case 'B':
             return new IdiomScreen(c);
            default:
                return this;
        }       
    }
    
    private void configureScreenButtons(SimpleKiosk k, TranslatorManager t) {
        k.clearScreen();
        k.setMenuMode('0');
        String optA=t.translate("Iniciar nuevo pedido");
        String optB=t.translate("Cambiar idoma");
        k.setOption('A', optA);
        k.setOption('B', optB);
    }
    
   
    
}
