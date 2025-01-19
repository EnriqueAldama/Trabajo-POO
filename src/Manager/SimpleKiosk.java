package Manager;


import java.util.List;
import sienens.BurgerSelfOrderKiosk;


/**
 *
 * @author jvelez
 */
public class SimpleKiosk {

    private final BurgerSelfOrderKiosk kiosk = new BurgerSelfOrderKiosk();
    private final TranslatorManager translatorManager;

    /**
     * Constructor de SimpleKiosk
     * @param translatorManager, el objetivo de esta clase es facilitarnos la vida y ir traduciendo cada vez que pongamos un boton, descripcion, titulo... para poder programar una unica vez cada boton y no 1 por cada idioma
     */
    public SimpleKiosk(TranslatorManager translatorManager) {
        this.translatorManager = translatorManager;
    }

    /**
     * Configurador de boton, con traducción
     * @param a: El boton que queremos modificar a se encuentra en (A-H)
     * @param buttonText: El texto que queremos que incluya nuestro botón
     */
    public void setOption(char a, String buttonText) {
        String translatedButtonText = translatorManager.translate(buttonText); // se traducen los string que se pasan como parametros a
                                                             // los metodods de Kiosk
        kiosk.setOption(a, translatedButtonText);
    }

    /**
     * Configurador de titulo, con traducción
     * @param title: El titulo que aparecerá en la pantalla
     */
    public void setTitle(String title) {
        String translatedTitle = translatorManager.translate(title);
        kiosk.setTitle(translatedTitle);
    }

    /**
     * Configurador de descripción con traducción
     * @param description: La descripción que queremos que tenga nuestro producto, pantalla...
     */
    public void setDescription(String description) {
        String translatedDescriptioon = translatorManager.translate(description);
        kiosk.setDescription(translatedDescriptioon);
    }

    /**
     * Seleccionamos el menu mode con los botones a la izquierda y foto y descripcion etc a la derecha
     */
    public void setMenuMode() {
        kiosk.setMenuMode();
    }

    /**
     * Selecccionamos el messagemode con los botones abajo
     */
    public void setMessageMode() {
        kiosk.setMessageMode();
    }

    /**
     * Este metodo nos servirá para la comunicación cliente-programa
     * @param i: numero de segundos que esperamos la respuesta del cliente
     * @return c: dovolvemos un char que equivale al boton al que se ha pulsado (A-H)
     */
    public char waitEvent(int i) {
        char c = kiosk.waitEvent(i);
        return c;
    }

    /**
     * Este método es el que imprime el Ticket, nosotros le pasaremos el String que incluye el pedido numero etc y el Kiosko lo imprimirá
     * @param orderText: incluira el numero de pedido, precio y nombre etc
     */
    public void print(List<String> orderText) {
        kiosk.print(orderText);
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

    /**
     * Este método limpia la interfaz de la pantalla
     */
    public void clearScreen() {
        kiosk.setTitle(null);
        kiosk.setImage(null);
        kiosk.setDescription(null);
        for (char cont = 'A'; cont <= 'H'; cont++)
            kiosk.setOption(cont, null);
    }
}
