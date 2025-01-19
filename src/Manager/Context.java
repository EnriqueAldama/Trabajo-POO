/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import products.MenuCard;
import products.Order;

/**
 *Esta clase agrupa todo lo que necesitaremos para realizar las operaciones del programa
 * @author am.machuca.2023
 */
public class Context {
    private final SimpleKiosk kiosk;
    private final TranslatorManager translator;
    private Order order;
    private final MenuCard menuCard;


    /**
     * El constructor de context creara un TranslatorManager que nos ayudará con las traducciones, un SimpleKiosk para comunicarnos con la intefaz que nos dan, cargara el menú desde Disco con el método loadFromDisk, y creamos tambien un nuevo Order
     */
    public Context() {
        this.translator = new TranslatorManager();
        this.kiosk = new SimpleKiosk(this.translator);
        this.menuCard = MenuCard.loadFromDisk();
        this.order = new Order();
    }
    /**
     * getter para kiosk
     * @return SimpleKiosk: en ocasiones tendremos que usar un getter de kiosk para configurar pantallas y cosas así
     */
    public SimpleKiosk getKiosk() {
        return kiosk;
    }

    /**
     * getter para TranslatorManager
     * @return TranslatorManager: en ocasiones tendremos que traducir otras cosas que no sean descripciones o botones, o descripciones complejas que incluyan variables no fijas como precio
     */

    public TranslatorManager getTranslator() {
        return translator;
    }

    /**
     * getter para Order
     * @return Order: necesitaremos conocer el pedido en pantalla de pago por ejemplo
     */
    public Order getOrder() {
        return order;
    }

    
    public MenuCard getMenuCard() {
        return menuCard;
    }

    /**
     * Cada vez que cambiamos de cliente tenemos que borrar la Order anterior, este metodo nos ayudará a eso
     */
    public void setOrder() {
        this.order.cancelOrder();
    }
}
