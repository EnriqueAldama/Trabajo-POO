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
         switch (response) {
            case 'A' -> {
               return new MenuScreen();
            }
            case 'B' -> {
               return new SectionScreen();
            } 
            case 'C' ->{
                return new PaymentScreen();
            } 
            case 'D' ->{
                c.getOrder().cancelOrder();
                c.getKiosk().clearScreen();
                c.getKiosk().setMessageMode();
                c.getKiosk().setDescription("Pedido cancelado");
                kiosk.waitEvent(1);
                return new OrderScreen();
            }
            default -> {
                return this;
            }
        }
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
