/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;


import Manager.Context;
import Manager.SimpleKiosk;
import products.MenuCardSection;

/**
 *
 * @author Alfa
 */
public class ProductScreen implements CarouselScreen{
    private final int section;
    private int currentItem;

    public ProductScreen(int section){
        this.section = section;
        this.currentItem = 0;
    }

    @Override
    public KioskScreen show(Context context) {
        SimpleKiosk kiosk = context.getKiosk();
        MenuCardSection menuSection = context.getMenuCard().getSection(this.section);  
       
        if (menuSection.getProductList() == null ) {
            throw new RuntimeException("Error: No hay productos disponibles de esta sección.");
        }
        
        // Muestra los botones estandar
        configureScreenButtons(kiosk);
        adjustCarruselButton(kiosk);

        // Bucle del carrusel
        while (true) { 
            products.IndividualProduct currentProduct = menuSection.getIndividualProduct(this.currentItem);  
            String description = "Producto: " + currentProduct.getName();
            String productImage = currentProduct.getImageFileName();
            kiosk.setDescription(description);
            kiosk.setImage(productImage);
            String title = "Selecciona la " + menuSection.getSectionName();
            kiosk.setTitle(title);
            char response = kiosk.waitEvent(30);
            switch (response) {
                // Botón seleccionar seccion
                case 'A' -> {
                    context.getOrder().addProduct(currentProduct);
                    context.getKiosk().clearScreen();
                    context.getKiosk().setMessageMode();
                    context.getKiosk().setDescription("Producto añadido al pedido");
                    kiosk.waitEvent(1);
                    return  new OrderScreen();
                }
                case 'B' -> {
                    context.getOrder().cancelOrder();
                    context.getKiosk().clearScreen();
                    context.getKiosk().setMessageMode();
                    context.getKiosk().setDescription("Pedido cancelado");
                    kiosk.waitEvent(1);
                    return new OrderScreen();
                }
                case 'C' -> {
                   return new OrderScreen();
                }
                
                // Botón anterior
                case 'G' -> {
                    if (currentItem - 1 < 0) { // Te lleva al final del carrusel al llegar al item -1
                        currentItem = menuSection.getProductList().size() - 1;
                    } else {
                        currentItem--;
                    }
                }
                // Botón siguiente
                case 'H' -> {
                    if (currentItem + 1 >= menuSection.getProductList().size()) { // Te lleva al inicio del carrusel cuando llegas al final
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
        k.setOption('A', "Añadir producto al pedido");
        k.setOption('B', "Cancelar pedido");
    }
    
}
