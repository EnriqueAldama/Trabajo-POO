package Screens;

import Manager.SimpleKiosk;

/**
 * Interfaz de las pantallas de tipo carrusel. Es decir, las pantallas de añadir
 * productos y la de elegir idioma
 */

public interface CarouselScreen extends KioskScreen {

    /**
     * Configuracion de los botones de ir hacia delante y hacia atras del carrusel
     * 
     * @param kiosk
     */
    public void adjustCarruselButton(SimpleKiosk kiosk);

    /**
     * Configuracion de los elementos que aparecen en la pantalla como el titulo, al
     * descripcion o los botones
     * 
     * @param kiosk
     */
    public void configureScreenButtons(SimpleKiosk kiosk);

}