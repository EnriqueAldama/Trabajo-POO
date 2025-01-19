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
 *
 * @author Alfa
 */
public class MenuScreen implements CarouselScreen {

    @Override
    public KioskScreen show(Context context) {

        SimpleKiosk kiosk = context.getKiosk();
        MenuCard menuCard = context.getMenuCard();
        MenuCardSection menuSection;
        TranslatorManager translator = context.getTranslator();
        Menu menu = new Menu();

        int currentItem = 0;

        for (int i = 0; i <= 2; i++) {

            configureScreenButtons(kiosk);
            adjustCarruselButton(kiosk);

            menuSection = menuCard.getSection(i); // vamos seccion por seccion

            boolean exit = false; // para poder salir del while y pasar a elegir sig producto

            while (!exit) {
                products.IndividualProduct currentProduct = menuSection.getIndividualProduct(currentItem);
                String description = "Product: " + currentProduct.getName();
                String productImage = currentProduct.getImageFileName();
                String title = "Selecciona la " + menuSection.getSectionName();

                kiosk.setDescription(description);
                kiosk.setImage(productImage);
                kiosk.setTitle(title);

                char response = kiosk.waitEvent(30);
                switch (response) {
                    // Botón seleccionar
                    case 'A' -> {
                        menu.addIndProduct(currentProduct);
                        context.getKiosk().clearScreen();
                        context.getKiosk().setMessageMode();
                        context.getKiosk().setDescription("Producto añadido al pedido");
                        kiosk.waitEvent(1);
                        exit = true;

                    }
                    // Boton cancelar
                    case 'B' -> {
                        context.getOrder().cancelOrder();
                        context.getKiosk().clearScreen();
                        context.getKiosk().setMessageMode();
                        context.getKiosk().setDescription("Pedido cancelado");
                        kiosk.waitEvent(1);
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
        context.getOrder().addProduct(menu);
        return new OrderScreen();
    }

    @Override
    public void adjustCarruselButton(SimpleKiosk kiosk) {
        kiosk.setOption('G', "<");
        kiosk.setOption('H', ">");
    }

    @Override
    public void configureScreenButtons(SimpleKiosk kiosk) {
        kiosk.clearScreen();
        kiosk.setMenuMode();

        kiosk.setOption('A', "Añadir al pedido");
        kiosk.setOption('B', "Cancelar pedido");
    }

}