/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Screens.KioskScreen;
import Screens.WelcomeScreen;

/**
 *
 * @author am.machuca.2023
 */
public class KioskManager {
    private Context context;

    public void start(){
        this.context = new Context();
        KioskScreen screen = (KioskScreen) new WelcomeScreen();
        while(true)
            screen = screen.show(context);
    }
}
