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
        MenuCard mc=c.getMenuCard();
        List<MenuCardSection> sections = mc.getSectionList();
        
        if (sections == null || sections.isEmpty()) {
            throw new RuntimeException("Error: No hay secciones disponibles.");
        }
        
        // Muestra los botones estandar
        configureScreenButtons(kiosk);
        adjustCarruselButton(kiosk);

        // Bucle del carrusel
        while (true) { 
            MenuCardSection currentSection = sections.get(this.currentItem);  
            String description = "Sección: " + currentSection.getSectionName();  
            String sectionImage = currentSection.getImageFileName();
            kiosk.setDescription(description);
            kiosk.setImage(sectionImage);
            
            char response = kiosk.waitEvent(30);
            switch (response) {
                // Botón seleccionar seccion
                case 'C' -> {
                    return new ProductScreen(currentItem); // Retornar la pantalla de los productos de la seccion elegida

                }
                // Botón anterior
                case 'G' -> {
                    if (currentItem - 1 < 0) { // Te lleva al final del carrusel al llegar al item -1
                        currentItem = sections.size() - 1;
                    } else {
                        currentItem--;
                    }
                }
                // Botón siguiente
                case 'H' -> {
                    if (currentItem + 1 >= sections.size()) { // Te lleva al inicio del carrusel cuando llegas al final
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
