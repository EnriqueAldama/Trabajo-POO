package products;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

import products.Order;
public class KitchenOrderManager {

private int orderNumber;
private List<Order> KitchenOrderList;

public void addOrderToKitchen(Order){
    try{
int number=incrementOrderNumber();
File FicheroCocina = new File("KitchenOrders.txt");
BufferedWriter buff=new BufferedWriter(new FileWriter(file), 10);

buff.write("Numero de pedido: " + number);
buff.newLine();
buff.write("Productos: ");
buff.newLine();
buff.write(Order.getOrderText());
buff.newLine();

catch(FileNotFoundException e)
    {
        FicheroCocina.createNewFile();
        System.out.println("listado de cocina no encontrado, creando uno nuevo");
    }
catch(IOException e)
    {
        System.out.println("No se puede crear un listado de cocina");
    }
}
}
}