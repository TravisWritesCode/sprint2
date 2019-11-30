package pizza_app;

import javafx.collections.ObservableList;

public class Order {
    private Pizza[] pizzas;
    private ObservableList<MenuItem> menuItems;
    private String customerPhoneNumber;
    private int orderId;
    private String orderDate;
    private String paymentType;
    private boolean isDelivery;
    private float orderTotal;
    private double price;
    private double subtotal;
    private double fee;
    private double tax;

    public Pizza[] getPizzas() {
        return pizzas;
    }

    public void setPizzas(Pizza[] pizzas) {
        this.pizzas = pizzas;
    }

    private double total;
    private String stringPrice;

    public Order(){}

    public String getStringPrice() { return stringPrice; }

    public void setStringPrice(String stringPrice) { this.stringPrice = stringPrice; }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(Double price){
        this.price = price;
        stringPrice = "$" + String.format("%.2f", price);
    }

    public double getSubtotal (){ return this.subtotal; }

    public void setSubtotal(Double subtotal){
        this.subtotal = subtotal;
    }

    public double getFee() { return fee; }

    public void setFee(Double fee) { this.fee = fee; }

    public double getTax() { return tax; }

    public void setTax(Double tax) { this.tax = tax; }

    public double getTotal() { return total; }

    public void setTotal(Double total) { this.total = total; }

    public void setMenuItems(ObservableList<MenuItem> menuItems){
        this.menuItems = menuItems;
    }
}
