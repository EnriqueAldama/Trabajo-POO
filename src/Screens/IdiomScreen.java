/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;
import Manager.SimpleKiosk;
import Manager.TranslatorManager;
import java.util.List;

/**
 * Pantalla de eleccion de idioma. Muestra los idiomas disponibles en forma de
 * carrusel
 */
public class IdiomScreen implements CarouselScreen {
    private int currentItem;

    /**
     * Constructor que inicializa como idioma por defecto al primeo en la lista de
     * idiomas
     */

    public IdiomScreen() {
        this.currentItem = 0;
    }

    /**
     * Obtiene la lista de idiomas. En el bucle del carrusel,
     * 
     * @param Context
     * @return siguiente pantalla
     */

    @Override
    public KioskScreen show(Context c) {

        SimpleKiosk kiosk = c.getKiosk();
        TranslatorManager t = c.getTranslator();
        List<String> idioms = t.getIdioms(); // se obtiene la lista de idiomas y se guarda en idioms
        configureScreenButtons(kiosk);
        adjustCarruselButton(kiosk);

        // Bucle del carrusel
        while (true) {
            String currentIdiom = idioms.get(currentItem); // elegimos el idioma actual de la lista con el indice
                                                           // currentItem

            String description = "Seleccionar el idioma " + currentIdiom; // descripcion, imagen a mostrar segun idioma
            String im = "languages/" + currentIdiom + ".png";
            kiosk.setDescription(description);
            kiosk.setImage(im);

            char response = kiosk.waitEvent(30);
            switch (response) {
                // Botón cambiar idioma
                case 'D' -> {
                    t.setCurrentIdiom(currentIdiom);
                    return new WelcomeScreen();
                }
                // Botón anterior
                case 'G' -> {
                    if (currentItem - 1 < 0) { // Si se sobrepasa el mínimo de la lista, es decir, si se intenta ir al
                                               // -1, se va al final
                        currentItem = idioms.size() - 1;
                    } else {
                        currentItem--; // Si no, se va al idioma anterior
                    }
                }
                // Botón siguiente
                case 'H' -> {
                    if (currentItem + 1 >= idioms.size()) { // Si se sobrepasa el maximo de la lista, es decir,si se
                                                            // intenta ir al 5, se vuelve al inicio
                        currentItem = 0;
                    } else {
                        currentItem++; // Si no, se va al idioma siguiente
                    }
                }
                default -> {
                    return this;
                }
            }
        }
    }

    /**
     * Configuracion de botones de anterior y siguiente del carrusel
     * 
     * @param SimpleKiosk
     */
    @Override
    public void adjustCarruselButton(SimpleKiosk k) {
        k.setOption('G', "<");
        k.setOption('H', ">");
    }

    /**
     * Se configuran los botones y el titulo
     * En este caso, un unico boton para sleeccionar idioma
     * 
     * @param SimpleKiosk
     */
    @Override
    public void configureScreenButtons(SimpleKiosk k) {
        k.clearScreen();
        k.setMenuMode();
        k.setTitle("Cambiar idioma");
        k.setOption('D', "Seleccionar este idioma");
    }
}
