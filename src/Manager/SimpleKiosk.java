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
public class SimpleKiosk {

    private BurgerSelfOrderKiosk kiosk = new BurgerSelfOrderKiosk();
    private TranslatorManager translatorManager; // TODO: Ver si es necesaria esta propiedad (no está en el diseño)

    public SimpleKiosk(TranslatorManager t) {
        this.translatorManager = t;
    }

    public void setOption(char a, String s) {
        String translatedS = translatorManager.translate(s);
        kiosk.setOption(a, translatedS);
    }

    public void setTitle(String s) {
        String translatedS = translatorManager.translate(s);
        kiosk.setTitle(translatedS);
    }

    public void setDescription(String s) {
        String translatedS = translatorManager.translate(s);
        kiosk.setDescription(translatedS);
    }

    public void setMenuMode() {
        kiosk.setMenuMode();
    }

    public void setMessageMode() {
        kiosk.setMessageMode();
    }

    public char waitEvent(int i) {
        char c = kiosk.waitEvent(i);
        return c;
    }

    public void print(List<String> s) {
        kiosk.print(s);
    }

    public void retainCreditCard(boolean b) {
        kiosk.retainCreditCard(b);
    }

    public void expelCreditCard(int i) {
        kiosk.expelCreditCard(i);
    }

    public void setImage(String s) {
        kiosk.setImage(s);
    }

    public long getCardNumber() {
        return kiosk.getCardNumber();
    }

    public void clearScreen() {
        kiosk.setTitle(null);
        kiosk.setImage(null);
        kiosk.setDescription(null);
        for (char cont = 'A'; cont <= 'H'; cont++)
            kiosk.setOption(cont, null);
    }
}
