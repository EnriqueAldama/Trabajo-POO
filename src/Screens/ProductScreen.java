/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import java.util.List;

import Manager.Context;
import Manager.SimpleKiosk;
import java.util.spi.CurrencyNameProvider;
import javax.crypto.MacSpi;
import products.MenuCard;
import products.MenuCardSection;

/**
 *
 * @author Alfa
 */
public class ProductScreen implements CarouselScreen{
    private final int section;
    private int currentItem;

    public ProductScreen(int section){
        this.section=section;
        this.currentItem=0;
    }

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        MenuCardSection sc = c.getMenuCard().getSection(this.section);  
        //List<IndividualProduct> products = sc.getProductList();
        
        if (sc.getProductList() == null ) {
            throw new RuntimeException("Error: No hay productos disponibles de esta sección.");
        }
        
        configureScreenButtons(kiosk);
        adjustCarruselButton(kiosk);

        // Bucle del carrusel
        while (true) { 
            products.IndividualProduct currentProduct = sc.getIndividualProduct(this.currentItem);  
            String description = "Product: " + currentProduct.getName();
            String im = currentProduct.getImageFileName();
            kiosk.setDescription(description);
            kiosk.setImage(im);
            
            char response = kiosk.waitEvent(30);
            switch (response) {
                // Botón seleccionar seccion
                case 'A' -> {
                    c.getKiosk().setMessageMode();
                    c.getKiosk().setDescription("Producto añadido con exito");
                    Thread.sleep(30);
                    c.getKiosk().setMenuMode();
                    return this;
                }
                case 'B' -> {
                    
                }
                case 'C' -> {
                   
                }

                
                // Botón anterior
                case 'G' -> {
                    if (currentItem - 1 < 0) { // Comprueba si intenta ir al -1 y va al final
                        currentItem = sc.getProductList().size() - 1;
                    } else {
                        currentItem--;
                    }
                }
                // Botón siguiente
                case 'H' -> {
                    if (currentItem + 1 >= sc.getProductList().size()) { // Comprueba si intenta ir más allá y vuelve al inicio
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
        k.setTitle("Selecciona la "+sc.getSectionName());
        k.setOption('A', "Añadir producto al pedido");
        k.setOption('B', "Cancelar producto del pedido");
        k.setOption('C', "Cancelar pedido");
    }
    
}
