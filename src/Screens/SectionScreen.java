package Screens;

import Manager.Context;
import Manager.SimpleKiosk;
import java.util.List;
import products.MenuCard;
import products.MenuCardSection;

/**
 * Si seleccionamos la opcion de añadir producto individual al pedido desde
 * OrderScreen, pasamos
 * a esta pantalla. Es una pantalla de tipo carrusel en la que se selecciona la
 * seccion
 * De esta pantalla se pasa a la pantalla de eleccion de producto
 */

public class SectionScreen implements CarouselScreen {
    private int currentItem;

    public SectionScreen() {
        this.currentItem = 0;
    }

    /**
     * Se selecciona la seccion del producto a añadir
     * 
     * @param Context
     * @return siguiente pantalla
     */
    @Override
    public KioskScreen show(Context context) {
        SimpleKiosk kiosk = context.getKiosk();
        MenuCard menuCard=context.getMenuCard();
        
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
                    return new ProductScreen(currentItem); // Retornar la pantalla de los productos de la seccion
                                                           // elegida

                }
                // Botón anterior
                case 'G' -> {
                    if (currentItem - 1 < 0) { // Te lleva al final del carrusel al llegar al item -1
                        currentItem = menuCard.getNumberOfSections() - 1;
                    } else {
                        currentItem--;
                    }
                }
                // Botón siguiente
                case 'H' -> {
                    if (currentItem + 1 >= menuCard.getNumberOfSections()) { // Te lleva al inicio del carrusel cuando llegas al final
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

    /**
     * Se configuran los botones, titulo.
     * En este caso, es un boton de seleccionar la seccion
     * 
     * @param SimpleKiosk
     */
    @Override
    public void configureScreenButtons(SimpleKiosk k) {
        k.clearScreen();
        k.setMenuMode();
        k.setTitle("SECCIONES");
        k.setOption('C', "Seleccionar esta sección");
    }
}
