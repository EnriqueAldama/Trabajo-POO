/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Manager.Context;

/**
 * Interfaz de las pantallas del kiosko
 */
public interface KioskScreen {

     /**
      * Contiene el código que se ejecuta al acceder a la pantalla
      * 
      * @param Context
      * @return siguiente pantalla
      */
     public KioskScreen show(Context c);

}
