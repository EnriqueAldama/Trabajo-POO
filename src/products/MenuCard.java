/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import products.MenuCardSection;
import java.util.List;
import javax.management.RuntimeErrorException;

/**
 *
 * @author am.machuca.2023
 */
public class MenuCard {
    private List<MenuCardSection> sectionList;
    
    public MenuCardSection getSection(int c){
        return sectionList.get(c);
    }
    
    public int getNumberOfSections(){
        return sectionList.size();
    }
    
    public static MenuCard loadFromDisk(){
        try {
            FileInputStream file= new FileInputStream("Catalog.xml");
            XMLDecoder decoder=new XMLDecoder(file);
            MenuCard mc= (MenuCard) decoder.readObject();
            decoder.close();
            file.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el archivo, llame a un informatico");
        }
    }

    public MenuCard(List<MenuCardSection> sectionList) {
        this.sectionList = sectionList;
    }

    public List<MenuCardSection> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<MenuCardSection> sectionList) {
        this.sectionList = sectionList;
    }
}
