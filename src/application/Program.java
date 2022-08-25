package application;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter client data:");
        System.out.print("Name: ");
        String clientName = sc.nextLine();
        System.out.print("Email: ");
        String clientEmail = sc.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        String clientBirthDay = sc.nextLine();
        LocalDate birthDay = LocalDate.parse(clientBirthDay.substring(6) +"-"+
                clientBirthDay.substring(3,5) + "-" +
                clientBirthDay.substring(0,2));
        Client client = new Client(clientName, clientEmail, birthDay);
        
        Order order = new Order(client);
        System.out.println("Enter order data:");
        System.out.print("Status: ");
        String orderStatus = sc.nextLine();
        order.setStatus(OrderStatus.valueOf(orderStatus));
        System.out.print("How many items to this order? ");
        int nItems = sc.nextInt();
        for (int i = 0; i < nItems; i++){
            System.out.println("Enter #" + (i+1) + " item data:");
            sc.nextLine();
            System.out.print("Product name: ");
            String productName = sc.nextLine();
            System.out.print("Product price: ");
            Double productPrice = sc.nextDouble();
            Product product = new Product(productName, productPrice);
            System.out.print("Quantity: ");
            int productQuantity = sc.nextInt();
            OrderItem oI = new OrderItem(productQuantity, productPrice, product);
            order.addItem(oI);
        }
        order.setMoment(LocalDateTime.now());
        
        System.out.println();
        System.out.println("ORDER SUMMARY");
        System.out.println(order);
        sc.close();
    }
    
}
