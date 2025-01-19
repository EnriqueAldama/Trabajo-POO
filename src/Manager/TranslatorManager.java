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
    private Translator currentDictionary; // El translator activo
    private Map<String, Translator> dictionaries; // Mapa con traductores de cada idioma

    /**
     * El constructor de TranslatorManager, creamos un mapa que asocie el nombre del idioma con el fichero que incluye las traducciones formato XML para ese idioma (para poder crear luego el mapa del diccionario)
    */
    public TranslatorManager() {
        this.dictionaries = new HashMap<>();
        this.dictionaries.put("español", new Translator("languages/español.xml"));
        this.dictionaries.put("inglés", new Translator("languages/inglés.xml"));
        this.dictionaries.put("francés", new Translator("languages/francés.xml"));
        this.dictionaries.put("alemán", new Translator("languages/alemán.xml"));
        this.dictionaries.put("catalán", new Translator("languages/catalán.xml"));
        this.dictionaries.put("gallego", new Translator("languages/gallego.xml"));
        this.dictionaries.put("vasco", new Translator("languages/vasco.xml"));

        this.currentDictionary = dictionaries.getOrDefault("español", this.currentDictionary); // por defecto el idioma
                                                                                               // es el español
    }

    /**
     * Metodo que nos sirve para saber a que idioma tenemos que hacer las traducciones
     * @param newLanguage: el nombre del idioma que selecciona el cliente
     */
    public void setCurrentIdiom(String newLanguage) {
        this.currentDictionary = dictionaries.getOrDefault(newLanguage, this.currentDictionary);
    }

    /**
     * Método que nos servirá para conocer todos los idiomas que tenemos para traducir, lo necesitaremos para crear la IdiomScreen
     * @return List<String>, lista de los nombres de los idiomas que tenemos disponibles
     */
    public List<String> getIdioms() {
        List<String> idioms = new ArrayList<>(this.dictionaries.keySet()); // se crea lista de los idiomas a partir del
                                                                           // conjuntod e claves
        return idioms;
    }

    /**
     * El método que se comunica con la clase Translator que es la capacitada para hacer las traducciones
     * @param s: el texto que queremos traducir
     * @return String del texto ya traducido
     */
    public String translate(String s) {
        return currentDictionary.translate(s);
    }

}
