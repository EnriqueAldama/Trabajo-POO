/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author am.machuca.2023
 */
public class TranslatorManager {
    private Translator currentDictionary;
    private Map<String,Translator> dictionaries;
    
    public TranslatorManager(){
        this.dictionaries = new HashMap<>();
        this.dictionaries.put("español", new Translator("languages/español.xml"));
        this.dictionaries.put("inglés", new Translator("languages/inglés.xml"));
        this.dictionaries.put("francés", new Translator("languages/francés.xml"));
        this.dictionaries.put("alemán", new Translator("languages/alemán.xml"));
        this.currentDictionary = dictionaries.getOrDefault("español", this.currentDictionary);
    }
    
    public void setCurrentIdiom(String newLanguage){
        this.currentDictionary = dictionaries.getOrDefault(newLanguage, this.currentDictionary);
    }
    
    public List<String> getIdioms(){
        List<String> idioms = new ArrayList<>(this.dictionaries.keySet());
        return idioms;
    }
    
    
    public String translate(String s){
        return currentDictionary.translate(s);
    }
    
}
