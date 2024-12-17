/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author am.machuca.2023
 */
public class TranslatorManager {
    private Translator currentDictionary;
    private Map<String,Translator> dictionaries;
    private String prueba;
    
    public TranslatorManager(){
        this.prueba = "a";  // TODO: Leer el archivo, crear los translators y poner currentDictionary
        this.currentDictionary = new Translator("Prueba");  // TODO: Leer el archivo, crear los translators y poner currentDictionary
    }
    
    public void setCurrentIdiom(String newLanguage){
        this.prueba = newLanguage;  // TODO: Cuando esté creado el mapa con los translators, descomentar el código
        /*
        this.currentDictionary = getOrDefault(newLanguage, this.currentDictionary);
        */
    }
    
    // TODO: Eliminar cuando este la prueba
    public String getPrueba(){
        return this.prueba;
    }
    
    public List getIdioms(){
        List<String> idioms = new ArrayList<>(this.dictionaries.keySet());
        return idioms;
    }
    
    
    public String translate(String s){
        return currentDictionary.translate(s);
    }
    
}
