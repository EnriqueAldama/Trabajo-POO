/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;
import Manager.SimpleKiosk;

/**
 *
 * Pantalla de pedido
 * Se accede dsde la pantalla de bienvenida y se puede volver a la pantalla de
 * bienvenida, pasar a la pantalla
 * de menu, pasar a la pantalla de seccion o pasar a la pantalla de pago
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
            case 'C' -> {
                if (c.getOrder().getTotalAmount() == 0) {
                    kiosk.clearScreen();
                    kiosk.setMessageMode();
                    kiosk.setDescription("Antes de pagar debe añadir algún producto");
                    kiosk.waitEvent(1);
                    return new OrderScreen();

                } else
                    return new PaymentScreen();
            }

            case 'D' -> {
                c.getOrder().cancelOrder();
                c.getKiosk().clearScreen();
                c.getKiosk().setMessageMode();
                c.getKiosk().setDescription("Pedido cancelado");
                kiosk.waitEvent(1);
                return new WelcomeScreen();
            }
            default -> {
                return this;
            }
        }
    }

    private void configureScreenButtons(SimpleKiosk kiosk) {
        kiosk.clearScreen();
        kiosk.setMenuMode();
        kiosk.setImage("PRODUCTOS/Logo.png");
        kiosk.setOption('A', "Añadir menú a pedido");
        kiosk.setOption('B', "Añadir producto individual a pedido");
        kiosk.setOption('C', "Finalizar pedido y pagar");
        kiosk.setOption('D', "Cancelar pedido");
    }
}
