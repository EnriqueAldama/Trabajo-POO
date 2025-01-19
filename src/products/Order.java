/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author am.machuca.2023
 */
public class Order {
    private List<Product> products;

    /**
     * Constructor de Order
     * Crea un array de productos (menús o productos individuales)
     */
    public Order() {
        this.products = new ArrayList<Product>();
    }

    /**
     * Devuelve el precio total del pedido
     * Utilizado para el procesamiento del pago y la emisión del ticket
     */
    public int getTotalAmount() {
        int s = 0;
        for (Product p : this.products) {
            s += p.getPrice();
        }
        return s;
    }

    /**
     * Devuelve un listado en forma de string de los productos
     * Utilizado para la emisión del ticket
     */
    public String getOrderText() {
        StringBuilder s = new StringBuilder();
        for (Product p : this.products) {
            s.append(p.getName()).append("\n");
        }
        return s.toString();
    }

    /**
     * Devuelve el número de productos del order
     * Se puede utilizar para crear un carrusel para revisar el order
     */
    public int GetNumProducts() {
        return this.products.size();
    }

    /**
     * Añade un producto nuevo al order
     */
    public void addProduct(Product p) {
        this.products.add(p);
    }

    /**
     * Elimina todos los productos del order
     */
    public void cancelOrder() {
        this.products.removeAll(products);
    }

}
