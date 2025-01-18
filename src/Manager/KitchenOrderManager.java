package Manager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

import products.Order;
public class KitchenOrderManager {

private List<Order> KitchenOrderList;

public void addOrderToKitchen(Order order, int orderNumber){

    try{

File FicheroCocina = new File("KitchenOrders.txt");
BufferedWriter buff=new BufferedWriter(new FileWriter(FicheroCocina, true), 10);

buff.write("Numero de pedido: " + orderNumber);
buff.newLine();
buff.write("Productos: ");
buff.newLine();
buff.write(order.getOrderText());
buff.newLine();
buff.close();
}
catch(IOException e)
    {
        try {
            File FicheroCocina = new File("KitchenOrders.txt");
            if (!FicheroCocina.exists()) {
                FicheroCocina.createNewFile();
                System.out.println("Listado de cocina no encontrado, creando uno nuevo");
            }
        } catch (IOException ex) {
            System.out.println("No se puede crear un listado de cocina");
        }
    }

}


}