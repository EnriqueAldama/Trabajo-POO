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
    /**
     * Constructor de MenuCard
     * @param sectionList Una lista de las secciones (Ej. Comida, bebida, postre), cada sección contiene los Individualproduct de dicha seccion
     */
    public MenuCard(List<MenuCardSection> sectionList) {
        this.sectionList = sectionList;
    }

    /**
     * Getter de las secciones
     * @param c Sección que se obtiene. Es utilizado para conseguir los productos de una sección y crear un carrusel de los productos
     */
    public MenuCardSection getSection(int c) {
        return sectionList.get(c);
    }

    /**
     * Obtiene el número de secciones
     * Es utilizado para crear un carrusel con las secciones
     */
    public int getNumberOfSections() {
        return sectionList.size();
    }

    /**
     * Se lee el archivo .xml con los IndividualProducts en sus respectivas secciones
     * Utilizado para popular con datos un objeto de la clase MenuCard
     */
    public static MenuCard loadFromDisk() {
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

}
