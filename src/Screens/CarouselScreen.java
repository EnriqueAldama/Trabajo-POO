package Screens;

import Manager.SimpleKiosk;

/**
 *
 * @author Alfa
 */

public interface CarouselScreen extends KioskScreen {
    
//    void adjustCarruselButton(int CurrentElement, int numberOfElements);
    
    public void adjustCarruselButton(SimpleKiosk kiosk);

    public void configureScreenButtons(SimpleKiosk kiosk);

}