/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author am.machuca.2023
 */
public class Translator {
    private Map<String, String> palabras;

    /**
     * Este metodo es el constructor del Mapa de palabras que funciona a modo de diccionario
     * @param fileName: el nombre del fichero XML que incluye el diccionario de palabras
     */
    public Translator(String fileName) { // se carga el archivo xml correspondiente al idioma
        this.palabras = loadFromFile(fileName);
    }

    /**
     * Este método aprovecha el metodo getOrDefault de los mapas para dado un elemento devolvernos por asi decirlo su pareja
     * @param s: es el texto que queremos traducir
     * @return devuelve el texto traducido al idioma correspondiente
     */
    public String translate(String s) {
        return palabras.getOrDefault(s, s);
    }

    /**
     * En este metodo usando XMLDecoder le introducimos un archivo xml con las frases y su traducción y nos devolverá
     * @param fileName
     * @return nos devuelve el objeto Mapa ya construido con todas las asociaciones creadas
     */
    private Map<String, String> loadFromFile(String fileName) {
        try {
            try (FileInputStream file = new FileInputStream(fileName)) {
                XMLDecoder decoder = new XMLDecoder(file);
                palabras = (Map<String, String>) decoder.readObject();
                decoder.close();
            }
            return palabras;
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de idiomas, llame a un informatico");
        }
    }
}
