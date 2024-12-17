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

    public IndividualProduct(String name, String description, String imageFileName, int price) {
        this.name = name;
        this.description = description ;
        this.imageFileName = imageFileName ;
        this.price = price;  /*Todo esto se tiene que leer de fichero*/
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String s){
        this.name=s;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
