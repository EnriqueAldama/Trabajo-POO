/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Data.Product;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author am.machuca.2023
 */
public class Order {
    private int orderNumber;
    private List<Product> products;
    
    public int getTotalAmount(){
        int i=0;
        Iterator<Product> iterator= products.iterator();
        while (iterator.hasNext()){
            Product product= iterator.next();
            i+=product.getPrice();
        }
        return i;      
    }
    
    public String getOrderText(){ /*No se de que va este metodo*/
        
    }
    
    public int GetNumProducts(){
        return this.products.size();
    }
    
    public void addProduct(Product p){
        this.products.add(p);
    }
}
