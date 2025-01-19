/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

/**
 *
 * @author am.machuca.2023
 */
interface Product {
    /**
     * Getter de price
     * Utilizado para calcular el precio final
     */
    public int getPrice();
    /**
     * Getter de name
     * Utilizado para mostrar el nombre del producto individual 
     * o del listado de productos que contiene un menú
     */
    public String getName();
}
