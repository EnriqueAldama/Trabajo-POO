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
    private List<IndividualProduct> productList;

    public MenuCardSection(String sectionName, String imageFileName, <any> productList) {
        this.sectionName = sectionName ;
        this.imageFileName = imageFileName ;
        this.productList = imageFileName;  /*AQUI HAY QUE LEERLO DE FICHERO*/
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getImageFileName() {
        return imageFileName;
    }
    
    public int GetNumberOfProducts(){
        return this.productList.size();
    }
    
}
