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
public class OrderScreen implements KioskScreen {
    
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        configureScreenButtons(kiosk);
        char response = kiosk.waitEvent(30);
        return switch (response) {
            case 'A' -> new MenuScreen();
            case 'B' -> new SectionScreen();
            case 'C' -> new PaymentScreen();
            // TODO: El botón D es la cancelación, hay que implementar la eliminación de todos los datos
            case 'D' -> new WelcomeScreen();
            default -> this;
        }; 
    }
    
    private void configureScreenButtons(SimpleKiosk kiosk) {
        kiosk.clearScreen();
        kiosk.setMenuMode();
        kiosk.setOption('A', "Añadir menú a pedido");
        kiosk.setOption('B', "Añadir producto individual a pedido");
        kiosk.setOption('C', "Finalizar pedido y pagar");
        kiosk.setOption('D', "Cancelar pedido");
    }
}
