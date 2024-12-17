/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;
import Manager.Context;
import Manager.SimpleKiosk;
import java.util.List;
import products.MenuCard;
import products.MenuCardSection;

/**
 *
 * @author Alfa
 */
public class SectionScreen implements CarouselScreen{
    private int currentItem;

public SectionScreen() {
    this.currentItem = 0;
}

@Override
public KioskScreen show(Context c){
    SimpleKiosk kiosk = c.getKiosk();
    MenuCard mc = mc.loadFromDisk();
    List<MenuCardSection> sections = mc.getSectionList();
    configureScreenButtons(kiosk);
    adjustCarruselButton(kiosk);
    // Bucle del carrusel
    while (true) { 
        MenuCardSection currentSection = mc.getSection(this.currentItem);
        String description = "Seleccionar el idioma " + currentSection.getSectionName();
        String im = currentSection.getImageFileName();
        kiosk.setDescription(description);
        kiosk.setImage(im);
        char response = kiosk.waitEvent(30);
        switch (response) {
            // Botón cambiar idioma
            case 'A' -> {

                return new WelcomeScreen();
            }
            // Botón antermcior
            case 'G' -> {
                if(currentItem - 1 < 0) { // Comprueba se intenta ir al -1 y va al final
                    currentItem = sections.size()-1;
                }
                else {
                    currentItem--;
                }
            }
            // Botón siguiente
            case 'H' -> {
                if(currentItem + 1 >= sections.size()) { // Comprueba si intenta ir al 5 y vuelve al inicio
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
    k.setTitle("SECCIONES");
    k.setOption('C', "Seleccionar esta seccion");
}  
}
}
