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
public class SectionScreen implements CarouselScreen {
    private int currentItem;

    public SectionScreen() {
        this.currentItem = 0;
    }

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        MenuCard mc = MenuCard.loadFromDisk();  // Proporcionar la ruta del archivo
        List<MenuCardSection> sections = mc.getSectionList();
        
        if (sections == null || sections.isEmpty()) {
            throw new RuntimeException("Error: No hay secciones disponibles.");
        }
        
        configureScreenButtons(kiosk);
        adjustCarruselButton(kiosk);

        // Bucle del carrusel
        while (true) { 
            MenuCardSection currentSection = sections.get(this.currentItem);  // Usar 'sections' en lugar de 'mc.getSection'
            String description = "Sección: " + currentSection.getSectionName();  // Corregir descripción
            String im = currentSection.getImageFileName();
            kiosk.setDescription(description);
            kiosk.setImage(im);
            
            char response = kiosk.waitEvent(30);
            switch (response) {
                // Botón cambiar idioma o volver a la pantalla principal
                case 'A' -> {
                    return new WelcomeScreen(); // Retornar la pantalla de bienvenida
                }
                // Botón anterior
                case 'G' -> {
                    if (currentItem - 1 < 0) { // Comprueba si intenta ir al -1 y va al final
                        currentItem = sections.size() - 1;
                    } else {
                        currentItem--;
                    }
                }
                // Botón siguiente
                case 'H' -> {
                    if (currentItem + 1 >= sections.size()) { // Comprueba si intenta ir más allá y vuelve al inicio
                        currentItem = 0;
                    } else {
                        currentItem++;
                    }
                }
                default -> {
                    return this; // Mantener la pantalla actual
                }
            }
        }
    }

    @Override
    public void adjustCarruselButton(SimpleKiosk k) {
        k.setOption('G', "<");
        k.setOption('H', ">");
    }

    @Override
    public void configureScreenButtons(SimpleKiosk k) {
        k.clearScreen();
        k.setMenuMode();
        k.setTitle("SECCIONES");
        k.setOption('C', "Seleccionar esta sección");
    }
}
