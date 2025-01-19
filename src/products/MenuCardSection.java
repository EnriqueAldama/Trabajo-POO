/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.util.List;

/**
 *
 * @author am.machuca.2023
 */
public class MenuCardSection {
    private String sectionName;
    private String imageFileName;
    private List<IndividualProduct> productList; // los IndividualProoducts de la seccion

    /**
     * Constructor de MenuCardSection
     * @param sectionName Nombre de la sección
     * @param imageFileName Imagen de la sección, utilizada en el carrusel
     * @param productList Listado de productos pertenecientes a esa sección
     */
    public MenuCardSection(String sectionName, String imageFileName, List<IndividualProduct> productList) {
        this.sectionName = sectionName;
        this.imageFileName = imageFileName;
        this.productList = productList;
    }

    /**
     * Obtiene un producto de productList
     * Utilizado para obtener el precio de los productos y hacer carruseles con los productos
     */
    public IndividualProduct getIndividualProduct(int i) {
        return this.productList.get(i);
    }

    /**
     * Getter de sectionName
     * Utilizado para mostrar en que sección estás al comprar un menú
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * Getter de imageFileName
     * Utilizado para crear un carrusel de las secciones
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * Devuelve cuantos productos hay en una sección
     * Utilizado para crear un carrusel con los productos
     */
    public int getNumberOfProducts() {
        return this.productList.size();
    }

    /**
     * Getter de productList
     * Utilizado para crear un carrusel con los productos
     */
    public List<IndividualProduct> getProductList() {
        return productList;
    }

}
