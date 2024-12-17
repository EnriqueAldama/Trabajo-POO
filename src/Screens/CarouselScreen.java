package Screens;

import Manager.SimpleKiosk;

/**
 *
 * @author Alfa
 */

public interface CarouselScreen extends KioskScreen {
    
//    void adjustCarruselButton(int CurrentElement, int numberOfElements);    // TODO: ¿Porque se tiene que pasar el número de elementos?
    
    public void adjustCarruselButton(SimpleKiosk kiosk);

    public void configureScreenButtons(SimpleKiosk kiosk);

}