/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Manager.Translator;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

/**
 *
 * @author am.machuca.2023
 */
public class TranslatorManager {
    private Translator currentDictionary;
    private Map<String,Translator> dictionaries;
    
    public void setCurrentIdiom(String s){
        
    }
    
    public List getIdioms(){
        
        return null;
        
    }
    
    public String translate(String s){
        return currentDictionary.translate(s);
    }
    
}
