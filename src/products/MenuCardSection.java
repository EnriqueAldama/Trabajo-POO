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

    public MenuCardSection(String sectionName, String imageFileName, List<IndividualProduct> productList) {
        this.sectionName = sectionName;
        this.imageFileName = imageFileName;
        this.productList = productList; /* AQUI HAY QUE LEERLO DE FICHERO */
    }

    public IndividualProduct getIndividualProduct(int i) {
        return this.productList.get(i);
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public int getNumberOfProducts() {
        return this.productList.size();
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public List<IndividualProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<IndividualProduct> productList) {
        this.productList = productList;
    }

}
