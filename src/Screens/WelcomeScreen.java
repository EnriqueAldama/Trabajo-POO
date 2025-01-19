/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;
import Manager.SimpleKiosk;

/**
 *
 * Pantalla inicial, de bienvenida, desde la cual se puede pasar a elegir el
 * idioma o a efectuar el pedido
 */

public class WelcomeScreen implements KioskScreen {

    /**
     * 
     * @param Context
     * @return siguiente pantalla
     */

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        configureScreenButtons(kiosk);

        c.setOrder(); // reiniciamos el pedido. Mejor hacerlo aqui que en OrderScreen ya que no
                      // queremos borrar
                      // todo el pedido cuando solo queramos hacer una modificacion

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
