/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import products.MenuCard;

/**
 *
 * @author am.machuca.2023
 */
public class Translator {
    private Map<String, String> palabras;

    public Translator(String fileName) { // se carga el archivo xml correspondiente al idioma
        this.palabras = loadFromFile(fileName);
    }

    public String translate(String s) {
        return palabras.getOrDefault(s, s);
    }

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
