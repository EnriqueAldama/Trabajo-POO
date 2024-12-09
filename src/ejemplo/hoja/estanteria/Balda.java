/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo.hoja.estanteria;

import java.util.List;

/**
 *
 * @author Alfa
 */
class Balda {
    private List<Libro> listaLibros;
    private int alto;
    private Estanteria estanteria;
    
    public Balda(Estanteria e){
        this.estanteria=e;
    }
    
    public void insertarLibro(Libro l){
       
        this.listaLibros.add(l);
    }
    
    public boolean estaLLena(Libro l){
        
        return false;
        
    }
           
}
