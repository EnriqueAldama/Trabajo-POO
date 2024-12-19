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
    private int orderNumber;
    private List<Product> products;

    public Order(){
        this.products= new ArrayList<Product>();
    }
    
    public int getTotalAmount(){
        return products.size();  
    }
    
    public String getOrderText(){ /*No se de que va este metodo*/
        return null;
        
    }
    
    public int GetNumProducts(){
        return this.products.size();
    }
    
    public void addProduct(Product p){
        this.products.add(p);
    }
}
