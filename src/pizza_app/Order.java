package pizza_app;

import javafx.collections.ObservableList;

public class Order {
    private String item;
    private double price;
    private double subtotal;
    private double fee;
    private double tax;
    private double total;
    private String stringPrice;

    public Order(String item, double price){
        this.item = item;
        this.price = price;
        stringPrice = "$" + String.format("%.2f", price);
    }

    public String getStringPrice() { return stringPrice; }

    public void setStringPrice(String stringPrice) { this.stringPrice = stringPrice; }

    public String getItem (){
        return this.item;
    }

    public void setItem(String item){
        this.item = item;
    }

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
}
