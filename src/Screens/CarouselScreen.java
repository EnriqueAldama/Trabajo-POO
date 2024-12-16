package Screens;

/**
 *
 * @author Alfa
 */

interface Carousel implements KioskScreen{

    public show KioskScreen (Context c);

    adjustCarruselButton(int CurrentElement, int numberOfElements);

    configureScreenButtons();


}