/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Manager.Context;

/**
 *
 * @author am.machuca.2023
 */
public class KioskManager {
    private Context context;
    public void KioskManager(){
        Context context=new Context();
    }

    public void start(){
        Screen screen= new WelcomeScreeN();
        while(true)
            screen=screen.show(context);
    }
}
