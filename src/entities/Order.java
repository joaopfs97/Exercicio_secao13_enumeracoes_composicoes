package entities;

import entities.enums.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDateTime moment;
    private OrderStatus status;
    
    private Client client;  
    private List<OrderItem> items = new ArrayList<>();
    
    public Order(Client client){
        this.client = client;
    }
    public Order(LocalDateTime moment, OrderStatus status, Client client) {
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public void addItem(OrderItem item){
        items.add(item);
    }
    
    public void removeItem(OrderItem item){
        items.remove(item);
    }
    
    public Double total(){
        double total = 0;
        for(OrderItem i : items){
            total += i.subTotal();
        }
        
        return total;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Order moment: " + moment.format(dtf) + "\n");
        sb.append("Order status: " + status + "\n");
        sb.append("Client: " + client.getName() + 
                " (" + client.getDate().format(df) + 
                ") - " + client.getEmail() + "\n");
        sb.append("Order items:\n");
        for(OrderItem i : items){
            sb.append(i.getProduct().getName() + ", $" +
                    String.format("%.2f", i.getProduct().getPrice()) + ", " +
                    "Quantity: " + i.getQuantity() + ", " +
                    "Subtotal: $" + String.format("%.2f",i.subTotal()) + "\n");            
        }
        sb.append("Total price: $" + String.format("%.2f", total()));
        
        return sb.toString();
    }
    
}
