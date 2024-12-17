/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author am.machuca.2023
 */
public class Translator {
    private Map<String,String> palabras;

    public Translator(String fileName) {
        this.palabras = new HashMap<>();
        //this.palabras = loadFromFile(fileName); // TODO: Añadir funcionalidad a esto
    }
   
    public String translate(String s){
       return palabras.getOrDefault(s, s);
    }   
    
}
