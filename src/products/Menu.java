/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;



/**
 *
 * @author am.machuca.2023
 */
public class Menu implements Product {
    private int discount;
    private IndividualProduct[] products;
    
    public void setDiscount(int discount) {
        this.discount = /*aqui tenemos que poner algo que lo lea del fichero*/
    }
   
    
    public int getPrice(){
        int i, total=0;
        for (i=0;i<this.getNumProducts() ;i++){
            total+=this.products[i].getPrice();
        }
        return total*=(100-this.discount)/100;/*descuento del 30, 100-30=70 el precio es el 70 del precio, entonces precio*0,7*/
    }
    
    public IndividualProduct getProduct(int i){
        return this.products[i];
    }

    public int getNumProducts() {
        return this.products.length;
    }
    
}
