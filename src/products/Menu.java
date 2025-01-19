/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author am.machuca.2023
 */
public class Menu implements Product {
    private int discount;
    private ArrayList<IndividualProduct> prod;

    public void setDiscount(int discount) {
        try (BufferedReader Buffr = new BufferedReader(new FileReader("COMANDAS\\discount"))) {

            this.discount = (int) Integer.valueOf(Buffr.readLine());
        } catch (IOException e) {
            System.out.println("Fallo al leer fichero. Llamar a informatico");
        }

    }

    public Menu() {
        this.prod = new ArrayList<IndividualProduct>();
        // this.discount=0;
    }

    @Override
    public int getPrice() {
        int s = 0;
        for (IndividualProduct p : this.prod) {
            s += p.getPrice();
        }
        // return s=s*((100-this.discount)/100);/*descuento del 30, 100-30=70 el precio
        // es el 70 del precio, entonces precio*0,7*/
        return s = s * (100 - this.discount) / 100;
    }

    public IndividualProduct getProduct(int i) {
        return this.prod.get(i);
    }

    public int getNumProducts() {
        return this.prod.size();
    }

    public void addIndProduct(IndividualProduct p) {
        this.prod.add(p);
    }

    @Override
    public String getName() {
        String s = "Menu:";
        for (int i = 0; i < this.prod.size(); i++) {
            s += "1x " + this.getProduct(i).getName() + " ";
        }
        return s;
    }
}
