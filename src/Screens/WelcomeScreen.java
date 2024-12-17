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
public class WelcomeScreen implements KioskScreen {
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        configureScreenButtons(kiosk);
        char response = kiosk.waitEvent(30);
        return switch (response) {
            case 'A' -> new OrderScreen();
            case 'B' -> new IdiomScreen();
            default -> this;
        };       
    }
    
    private void configureScreenButtons(SimpleKiosk k) {
        k.clearScreen();
        k.setMessageMode();
        k.setOption('A', "Iniciar nuevo pedido");
        k.setOption('B', "Cambiar idioma");
    }    
}
