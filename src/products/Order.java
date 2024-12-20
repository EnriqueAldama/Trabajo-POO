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
        int s=0; 
        for (Product p : this.products) { 
            s+=p.getPrice(); 
        }
        return s;
    }
    
    public String getOrderText() {
        StringBuilder s = new StringBuilder(); 
        for (Product p : this.products) { 
            s.append(p.getName()).append("\n"); 
        }
        return s.toString(); 
    }
    
    
    public int GetNumProducts(){
        return this.products.size();
    }
    
    public void addProduct(Product p){
        this.products.add(p);
    }

    public void cancelOrder(){
            this.products.removeAll(products);
    }
}
