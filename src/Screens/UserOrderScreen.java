package Screens;

import Manager.Context;
import Manager.SimpleKiosk;
import java.util.ArrayList;
import products.Product;
 
 
 public class UserOrderScreen implements KioskScreen {
 
     private int currentItem;
 
     public UserOrderScreen() {
         this.currentItem = 0;
     }
 
     @Override
     public KioskScreen show(Context c) {
         SimpleKiosk kiosk = c.getKiosk();
         ArrayList<Product> orderProducts = c.getOrder().getProducts();
         
         if (orderProducts == null || orderProducts.isEmpty()) {
             kiosk.clearScreen();
             kiosk.setMessageMode();
             kiosk.setDescription("Antes debes de añadir un producto");
             kiosk.waitEvent(1);
             return new WelcomeScreen();
         }else{
         
         configureScreenButtons(kiosk);
         adjustCarruselButton(kiosk);
                         
                                 // Bucle del carrusel
                                 while (true) { 
                                     Product currentProduct = orderProducts.get(this.currentItem);  
                                     String title=currentProduct.getName();
                                     kiosk.setTitle(title); 
                                     char response = kiosk.waitEvent(30);
                                     switch (response) {
                                         // Botón seleccionar seccion
                                         case 'A' -> {
                                             c.getOrder().getProducts().remove(currentProduct);
                                             if (currentItem - 1 < 0) { // Comprueba si intenta ir al -1 y va al final
                                                 currentItem = orderProducts.size() - 1;
                                             } else {
                                                 currentItem--;
                                             }
                                         }
                                         case 'B' -> {
                                             return new OrderScreen();
                                         }
                                         case 'C' -> {
                                             return new WelcomeScreen();
                                         }
                                         // Botón anterior
                                         case 'G' -> {
                                             if (currentItem - 1 < 0) { // Comprueba si intenta ir al -1 y va al final
                                                 currentItem = orderProducts.size() - 1;
                                             } else {
                                                 currentItem--;
                                             }
                                         }
                                         // Botón siguiente
                                         case 'H' -> {
                                             if (currentItem + 1 >= orderProducts.size()) { // Comprueba si intenta ir más allá y vuelve al inicio
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
                         }
 
             @Override           
             private void adjustCarruselButton(SimpleKiosk kiosk) {
                 kiosk.setOption('G', "<");
                 kiosk.setOption('H', ">");
             }
 
             @Override
             private void configureScreenButtons(SimpleKiosk kiosk) {
                 kiosk.clearScreen();
                 kiosk.setMenuMode();
                 kiosk.setOption('A', "Eliminar producto al pedido");
                 kiosk.setOption('B', "Volver al menú del pedido");
                 kiosk.setOption('C', "Cancelar todo el pedido");
             }

         }
 