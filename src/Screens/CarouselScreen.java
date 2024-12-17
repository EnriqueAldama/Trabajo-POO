package Screens;
import Manager.Context;
import Manager.SimpleKiosk;

/**
 *
 * @author Alfa
 */

public interface CarouselScreen extends KioskScreen {

    void adjustCarruselButton(int CurrentElement, int numberOfElements);

    void configureScreenButtons();


}