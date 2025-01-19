/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author am.machuca.2023
 */
public class MenuCard {
    private List<MenuCardSection> sectionList;

    public MenuCard(List<MenuCardSection> sectionList) { // En el constructor se pasa la lista de secciones. Cada una
                                                         // contiene los Individualproduct de dicha seccion
        this.sectionList = sectionList;
    }

    public MenuCardSection getSection(int c) { // getter de las secciones
        return sectionList.get(c);
    }

    public int getNumberOfSections() {
        return sectionList.size();
    }

    public static MenuCard loadFromDisk() { // Se lee el archivo xml con los productos
        try {
            MenuCard mc;
            try (FileInputStream file = new FileInputStream("PRODUCTOS/Catalog.xml")) {
                XMLDecoder decoder = new XMLDecoder(file);
                mc = (MenuCard) decoder.readObject();
                decoder.close();
            }
            return mc;
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo, llame a un informatico");
        }
    }

    public List<MenuCardSection> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<MenuCardSection> sectionList) {
        this.sectionList = sectionList;
    }
}
