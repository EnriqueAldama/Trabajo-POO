package demo;

import java.util.ArrayList;
import javax.naming.CommunicationException;
import sienens.BurgerSelfOrderKiosk;
import urjc.UrjcBankServer;

/**
 *
 * @author jvelez
 */
class BurgerSelfOrderKioskManager {
    BurgerSelfOrderKiosk kiosk = new BurgerSelfOrderKiosk();    
    
    UrjcBankServer bank = new UrjcBankServer();
    
    private void clear() {
        kiosk.setTitle(null);
        kiosk.setImage(null);
        kiosk.setDescription(null);
        
        for (char cont = 'A'; cont <= 'H'; cont++)
            kiosk.setOption(cont, null);
    }
    
    
    void run() {
        
        final int waitTime = 30;
        
        while(true) {
            clear();
            kiosk.setMenuMode();
            kiosk.setTitle("URJC Burger - Bienvenido");
            kiosk.setOption('B', "Nuevo pedido");
            kiosk.setOption('D', "Cambiar idioma");
            kiosk.setImage("Logo.png");
            
            char c = kiosk.waitEvent(waitTime);

            clear();
            kiosk.setTitle("¿Qué quieres hacer?");
            kiosk.setOption('A', "Añadir menú al pedido");
            kiosk.setOption('B', "Añadir producto individual a pedido");
            kiosk.setOption('E', "Cancelar");
            kiosk.setImage("Pedido.png");

            c = kiosk.waitEvent(waitTime); 

            clear();
            kiosk.setImage("Hamburguesa.png");
            kiosk.setTitle("Selecciona la hamburguesa del menú");
            kiosk.setOption('H', ">");
            kiosk.setOption('C', "Añadir producto al pedido");
            kiosk.setOption('D', "Cancelar menú del pedido");
            kiosk.setOption('E', "Cancelar pedido");
            kiosk.setDescription("Hamburguesa de ternera\n\n100 gr de carne 100% vacuno\nPrecio: 50€");

            c = kiosk.waitEvent(waitTime);

            clear();
            kiosk.setImage("Pollo.png");            
            kiosk.setTitle("Selecciona la hamburguesa del menú");
            kiosk.setOption('A', null);
            kiosk.setOption('G', "<");
            kiosk.setOption('H', ">");
            kiosk.setOption('D', "Añadir producto al pedido");
            kiosk.setOption('E', "Cancelar menú del pedido");
            kiosk.setOption('F', "Cancelar pedido");
            kiosk.setDescription("Hamburguesa de pollo\n\n100 gr de carne 100% pollo\nPrecio: 40€");

            c = kiosk.waitEvent(waitTime);

            clear();
            kiosk.setTitle("Elige la bebida del menú");
            kiosk.setOption('H', ">");
            kiosk.setOption('D', "Añadir producto al pedido");
            kiosk.setOption('E', "Cancelar menú del pedido");
            kiosk.setOption('F', "Cancelar pedido");
            kiosk.setImage("Cocacola.png");
            kiosk.setDescription("Cocacola normal\n33cc");

            c = kiosk.waitEvent(waitTime);

            clear();
            kiosk.setTitle("Elige la bebida del menú");
            kiosk.setOption('G', "<");
            kiosk.setOption('H', ">");
            kiosk.setOption('B', "Añadir producto al pedido");
            kiosk.setOption('D', "Cancelar menú del pedido");
            kiosk.setOption('E', "Cancelar pedido");
            kiosk.setDescription("Fanta\n33cc");
            kiosk.setImage("Fanta.png");
            c = kiosk.waitEvent(waitTime);


            clear();
            kiosk.setTitle("¿Qué quieres hacer?");
            kiosk.setOption('A', "Añadir menú al pedido");
            kiosk.setOption('B', "Añadir producto individual a pedido");
            kiosk.setOption('C', "Eliminar elemento del pedido");
            kiosk.setOption('D', "Finalizar pedido");
            kiosk.setOption('E', "Cancelar el pedido");
            kiosk.setDescription("Pedido actual:\nMenú x 1");
            kiosk.setImage("Pedido.png");
            
            c = kiosk.waitEvent(waitTime);

            clear();
            kiosk.setTitle("Elige una bebida");
            kiosk.setOption('H', ">");
            kiosk.setOption('D', "Añadir producto al pedido");
            kiosk.setOption('E', "Cancelar menú del pedido");
            kiosk.setOption('F', "Cancelar pedido");
            kiosk.setImage("Cocacola.png");
            kiosk.setDescription("Cocacola normal\n33cc");

            c = kiosk.waitEvent(waitTime);

            clear();
            kiosk.setTitle("¿Qué quieres hacer?");
            kiosk.setOption('A', "Añadir menú al pedido");
            kiosk.setOption('B', "Añadir producto individual a pedido");
            kiosk.setOption('D', "Eliminar elemento del pedido");
            kiosk.setOption('E', "Finalizar pedido");
            kiosk.setOption('F', "Cancelar el pedido");
            kiosk.setDescription("Pedido actual:\nMenú x 1\nCocacola x 1");
            kiosk.setImage("Pedido.png");
            
            c = kiosk.waitEvent(waitTime);

            clear();
            kiosk.setMessageMode();
            kiosk.setTitle("Introduce tu tarjeta de crédito");
            kiosk.setDescription("Pedido actual:\nMenú x1\nCocacola x1\n\nTotal: 200€\n\nIntroduce la tarjeta de crédito para confirmar el pedido o pulsa los botones inferiores para tomar otra decisión");            
            kiosk.setOption('A', "Modificar pedido");
            kiosk.setOption('B', "Cancelar pedido");
            c = kiosk.waitEvent(waitTime);            

            if (c == '1') {
                kiosk.retainCreditCard(false);

                try {
                    boolean ok = bank.doOperation(kiosk.getCardNumber(),200);
                    if (ok) {
                        kiosk.setMessageMode();
                        kiosk.setTitle("Proceso de pago exitoso");
                        kiosk.setDescription("Ya puedes recoger tu tarjeta\nTu número de pedido es 33\nRecoge el ticket\nTe rogamos que permanezcas atento a las pantallas");
                        kiosk.expelCreditCard(waitTime);
                        
                        ArrayList <String> ticketText = new ArrayList<>();
                        ticketText.add("Artículos comprados");
                        ticketText.add("=====================");
                        ticketText.add("Menú - 200€");
                        ticketText.add("=====================");
                        ticketText.add("Total: 200€");
                        ticketText.add("");
                        ticketText.add("Número de pedido: 33");
                        kiosk.print(ticketText);
                        
                    } else {
                        kiosk.setMessageMode();
                        kiosk.setTitle("Problemas en el proceso de pago");
                        kiosk.setDescription("El banco dice que no tienes dinero. Prueba con otra tarjeta.");            
                        kiosk.expelCreditCard(waitTime);
                    }
                } catch(CommunicationException ex) {
                    kiosk.setMessageMode();
                    kiosk.setTitle("Problemas de comunicación en el proceso de pago");
                    kiosk.setDescription("Reintentando");
                }
            }
        }    
    }
}
