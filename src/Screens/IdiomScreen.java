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
 *
 * @author Alfa
 */
public class IdiomScreen implements CarouselScreen {
    private int currentItem;

    public IdiomScreen() {
        this.currentItem = 0;
    }

    @Override
    public KioskScreen show(Context c){
        SimpleKiosk kiosk = c.getKiosk();
        TranslatorManager t = c.getTranslator();
        List<String> idioms = t.getIdioms();
        configureScreenButtons(kiosk);
        adjustCarruselButton(kiosk);
        // Bucle del carrusel
        while (true) { 
            String currentIdiom = idioms.get(currentItem);
            String description = "Seleccionar el idioma " + currentIdiom;
            String im = "languages/"+currentIdiom +".png";
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
                    if(currentItem - 1 < 0) { // Comprueba se intenta ir al -1 y va al final
                        currentItem = idioms.size()-1;
                    }
                    else {
                        currentItem--;
                    }
                }
                // Botón siguiente
                case 'H' -> {
                    if(currentItem + 1 >= idioms.size()) { // Comprueba si intenta ir al 5 y vuelve al inicio
                        currentItem = 0;
                    }
                    else {
                        currentItem++;
                    }
                }
                default -> {
                    return this;
                }
            }
        }
    }
    
    /* TODO: ¿ESTO ESTÁ BIEN O ES MEJOR USAR SOLO configureScreenButtons?*/ /*Todo esto nos lo deja a nosotros */
    @Override
    public void adjustCarruselButton(SimpleKiosk k){
        k.setOption('G', "<");
        k.setOption('H', ">");
    }
    
    @Override
    public void configureScreenButtons(SimpleKiosk k) {
        k.clearScreen();
        k.setMenuMode();
        k.setTitle("Cambiar idioma");
        k.setOption('D', "Seleccionar este idioma");
    }  
}
