/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Data.MenuCard;
import Data.Order;

/**
 *
 * @author am.machuca.2023
 */
public class Context {
    private SimpleKiosk kiosk;
    private TranslatorManager translator;
    private Order order;
    private MenuCard menuCard;

    public SimpleKiosk getKiosk() {
        return kiosk;
    }

    public TranslatorManager getTranslator() {
        return translator;
    }

    public Order getOrder() {
        return order;
    }

    public MenuCard getMenuCard() {
        return menuCard;
    }
    
    public void setOrder(Order o){
            
    }
}
