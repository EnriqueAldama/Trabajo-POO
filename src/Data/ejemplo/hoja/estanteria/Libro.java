/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo.hoja.estanteria;

/**
 *
 * @author Alfa
 */
class Libro {
    private int ancho;
    private int alto;
    private String titulo;
    public Libro(int A, int al, String t){
        this.ancho=A;
        this.alto=al;
        this.titulo=t;      
    }
    public int getAncho(){
        return this.ancho;
    }
    public int getAlto(){
        return this.alto;
    }
    public String getTitulo(){
        return this.titulo;
    }