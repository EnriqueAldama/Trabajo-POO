/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;
import Manager.SimpleKiosk;
import Manager.TranslatorManager;
import products.Menu;
import products.MenuCard;
import products.MenuCardSection;

/**
 * Pantalla de creacion de menu
 * Se accede desde y se vuelve a la pantalla de pedido
 */
public class MenuScreen implements CarouselScreen {

    /**
     * Si se selcciona la opcion de añadir menu desde orderScreen, se pasa a esta
     * pantalla
     * Mediante un carrusel, posibilita la eleccion de tres productos individuales,
     * cada uno de una seccion, para formar un menu.
     * 
     * @param Context
     * @return siguiente pantalla
     */

    @Override
    public KioskScreen show(Context c) {

        SimpleKiosk kiosk = c.getKiosk();
        MenuCard mc = c.getMenuCard();
        MenuCardSection sc;
        TranslatorManager t = c.getTranslator();
        Menu menu = new Menu();

        // Bucle del carrusel, como el utilizado en el carrusel de productos
        // individuales:
        // Primer se añade bebida, ppal, complemento

        int currentItem = 0;

        for (int i = 0; i <= 2; i++) {

            configureScreenButtons(kiosk);
            adjustCarruselButton(kiosk);

            sc = mc.getSection(i); // vamos seccion por seccion

            boolean exit = false; // para poder salir del while y pasar a elegir sig producto

            while (exit == false) {
                products.IndividualProduct currentProduct = sc.getIndividualProduct(currentItem);
                String description = "Product: " + currentProduct.getName();
                String im = currentProduct.getImageFileName();
                String title = "Selecciona la " + sc.getSectionName();

                kiosk.setDescription(description);
                kiosk.setImage(im);
                kiosk.setTitle(title);

                char response = kiosk.waitEvent(30);
                switch (response) {
                    // Botón seleccionar
                    case 'A' -> {
                        menu.addIndProduct(currentProduct); // Añadimos al menu el prod individual
                        c.getKiosk().clearScreen();
                        c.getKiosk().setMessageMode();
                        c.getKiosk().setDescription("Producto añadido al pedido");
                        kiosk.waitEvent(1);
                        exit = true; // se sale otra vez al bucle for para elegir siguiente producto

                    }
                    // Boton cancelar
                    case 'B' -> {
                        c.getOrder().cancelOrder();
                        c.getKiosk().clearScreen();
                        c.getKiosk().setMessageMode();
                        c.getKiosk().setDescription("Pedido cancelado");
                        kiosk.waitEvent(1);
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
                        if (currentItem + 1 >= sc.getProductList().size()) { // Comprueba si intenta ir más allá y
                                                                             // vuelve al
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

        menu.setDiscount(); // Una vez elegidos todos los productos, aplicamos descuento (que se lee del
                            // archivo)
        c.getOrder().addProduct(menu); // Añadimos al pedido el menu
        return new OrderScreen();
    }

    @Override
    public void adjustCarruselButton(SimpleKiosk kiosk) {
        kiosk.setOption('G', "<");
        kiosk.setOption('H', ">");
    }

    /**
     * Se configuran los botones.
     * En este caso, son botones de añadir al pedido y cancelar el pedido
     * 
     * @param SimpleKiosk
     */

    @Override
    public void configureScreenButtons(SimpleKiosk sk) {
        sk.clearScreen();
        sk.setMenuMode();

        sk.setOption('A', "Añadir al pedido");
        sk.setOption('B', "Cancelar pedido");
        // sk.setOption('C', "Cancelar producto del pedido");
    }

}