/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import products.MenuCard;
import products.Order;

/**
 *
 * @author am.machuca.2023
 */
public class Context {
    private final SimpleKiosk kiosk;
    private final TranslatorManager translator;
    private Order order;
    private final MenuCard menuCard;

    public Context() {
        this.translator = new TranslatorManager();
        this.kiosk = new SimpleKiosk(this.translator);
        this.menuCard = MenuCard.loadFromDisk();
        this.order = new Order();
    }

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

    public void setOrder() {
        this.order.cancelOrder();
    }
}
