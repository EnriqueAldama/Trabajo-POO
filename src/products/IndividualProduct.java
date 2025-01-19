/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

/**
 *
 * @author am.machuca.2023
 */
public class IndividualProduct implements Product{
    private String name;
    private String description;
    private String imageFileName;
    private int price;
    /**
     * Constructor de IndividualProduct. El objetivo de esta clase es guardar todos los datos clave de un producto
     * @param name, El nombre de este producto
     * @param description, La descripción de este producto
     * @param imageFileName, La imagen de este producto
     * @param price, El precio de este producto
     */

    public IndividualProduct(String name, String description, String imageFileName, int price) {
        this.name = name;
        this.description = description ;
        this.imageFileName = imageFileName ;
        this.price = price;
    }
    /**
     * getter para name
     * @return name: Necesitamos mostrar el nombre en sitios como pantallas de selección de producto o el ticket
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * getter para description
     * @return name: Necesitamos mostrar la descripción en las pantallas de selección de producto
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter para imageFileName
     * @return name: Necesitamos mostrar la imagen del producto en las pantallas de selección de producto
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * getter para price
     * @return name: Necesitamos conocer el precio del producto para calcular el precio del ticket o de un menú al que pertenece
     */    
    @Override
    public int getPrice() {
        return price;
    } 
    
}
