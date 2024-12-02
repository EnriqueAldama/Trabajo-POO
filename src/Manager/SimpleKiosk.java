package Manager;

import java.util.ArrayList;
import java.util.List;
import javax.naming.CommunicationException;
import sienens.BurgerSelfOrderKiosk;
import urjc.UrjcBankServer;

/**
 *
 * @author jvelez
 */
class SimpleKiosk {
    
    BurgerSelfOrderKiosk kiosk = new BurgerSelfOrderKiosk();    
    
    public void setOption(char a, String s){
        kiosk.setOption(a,s);
    }
    
    public void setTitle(String s){
        kiosk.setTitle(s);
    }
    
    public void setDescription(String s){
        kiosk.setDescription(s);
    }
    
    public void setMenuMode(char c){ /*HAY QUE DECIRLE QUE ESTA MAL IMPLEMENTADO EL SETMODE EN LA DE SIENENS*/
        kiosk.setMenuMode=c;
    }
    
    public char waitEvent(int i){
        char c=kiosk.waitEvent(i);
        return c;        
    }
    
    public void print(List<String> s){
        kiosk.print(s);
    }
    
    public void retainCreditCard(boolean b){
        kiosk.retainCreditCard(b);
    }
    
    public void expelCreditCard(int i){
        kiosk.expelCreditCard(i);
    }
    
    public void setImage(String s){
        kiosk.setImage(s);
    }
    
    public void getCardNumber(){
        kiosk.getCardNumber();
    }
    
    public void clearScreen() {
        kiosk.setTitle(null);
        kiosk.setImage(null);
        kiosk.setDescription(null);
        for (char cont = 'A'; cont <= 'H'; cont++)
            kiosk.setOption(cont, null);
    }
    
    
    
    
    
   
}
