/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import java.util.List;

import Manager.Context;
import Manager.SimpleKiosk;
import javax.crypto.MacSpi;
import products.MenuCard;
import products.MenuCardSection;

/**
 *
 * @author Alfa
 */
public class ProductScreen implements CarouselScreen{

    public ProductScreen(int Section){

    }

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        MenuCard mc = MenuCard.loadFromDisk();  
        List<MenuCardSection> sections = mc.getSectionList();
        
        if (sections == null || sections.isEmpty()) {
            throw new RuntimeException("Error: No hay secciones disponibles.");
        }
        
        configureScreenButtons(kiosk);
        adjustCarruselButton(kiosk);

        // Bucle del carrusel
        while (true) { 
            MenuCardSection currentSection = sections.get(this.currentItem);  
            String description = "Sección: " + currentSection.getSectionName();  
            String im = currentSection.getImageFileName();
            kiosk.setDescription(description);
            kiosk.setImage(im);
            
            char response = kiosk.waitEvent(30);
            switch (response) {
                // Botón seleccionar seccion
                case 'A' -> {
                    return new WelcomeScreen(); // Retornar la pantalla de los productos de la seccion elegida

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
        k.setTitle("Selecciona la "+menucs.getSectionName());
        k.setOption('A', "Añadir producto al pedido");
        k.setOption('B', "Cancelar producto del pedido");
        k.setOption('C', "Cancelar pedido");
    }
    
}
