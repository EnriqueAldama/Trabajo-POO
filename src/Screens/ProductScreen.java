/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;
import Manager.SimpleKiosk;
import products.MenuCardSection;

/**
 * Una vez seleccionada la seccion (pantalla de seccion), se pasa a esta
 * pantalla de eleccion de producto individual de dicha seccion. De tipo
 * carrusel
 * Se vuelve a pantalla de pedido
 */
public class ProductScreen implements CarouselScreen {
    private final int section;
    private int currentItem;

    public ProductScreen(int section) {
        this.section = section;
        this.currentItem = 0;
    }

    /**
     * Segun la seccion que sea, se itera por los productos de su carta con un
     * carrusel
     * 
     * @param Context
     * @return siguiente pantalla
     */

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        MenuCardSection sc = c.getMenuCard().getSection(this.section); // Obtiene la carta de productos de la seccion
        // List<IndividualProduct> products = sc.getProductList();

        if (sc.getProductList() == null) {
            throw new RuntimeException("Error: No hay productos disponibles de esta sección.");
        }

        configureScreenButtons(kiosk);
        adjustCarruselButton(kiosk);

        // Bucle del carrusel
        while (true) {
            products.IndividualProduct currentProduct = sc.getIndividualProduct(this.currentItem);
            String description = "Producto: " + currentProduct.getName();
            String im = currentProduct.getImageFileName();
            kiosk.setDescription(description);
            kiosk.setImage(im);
            String title = "Selecciona la " + sc.getSectionName();
            kiosk.setTitle(title);
            char response = kiosk.waitEvent(30);

            switch (response) {
                // Botón seleccionar seccion
                case 'A' -> {
                    c.getOrder().addProduct(currentProduct);
                    c.getKiosk().clearScreen();
                    c.getKiosk().setMessageMode();
                    c.getKiosk().setDescription("Producto añadido al pedido");
                    kiosk.waitEvent(1);
                    return new OrderScreen();
                }
                case 'B' -> {
                    c.getOrder().cancelOrder();
                    c.getKiosk().clearScreen();
                    c.getKiosk().setMessageMode();
                    c.getKiosk().setDescription("Pedido cancelado");
                    kiosk.waitEvent(1);
                    return new OrderScreen();
                }
                case 'C' -> {
                    return new OrderScreen();
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
                    if (currentItem + 1 >= sc.getProductList().size()) { // Comprueba si intenta ir más allá y vuelve al
                                                                         // inicio
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

    /**
     * Configuracion de botones de siguiente y anterior del carrusel
     */
    @Override
    public void adjustCarruselButton(SimpleKiosk k) {
        k.setOption('G', "<");
        k.setOption('H', ">");
    }

    /**
     * Se configuran los botones.
     * En este caso, son botones de añadir producto al pedido y cancelar pedido
     * 
     * @param SimpleKiosk
     */
    @Override
    public void configureScreenButtons(SimpleKiosk k) {
        k.clearScreen();
        k.setMenuMode();
        k.setOption('A', "Añadir producto al pedido");
        k.setOption('B', "Cancelar pedido");
    }

}
