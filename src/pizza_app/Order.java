package pizza_app;

import javafx.collections.ObservableList;
/**
 *<p>
 *     This class is used to create instances of orders with the following attributes:
 *     <li><ul>
 *         Pizza(ass array)
 *         menuItems(as Observable List)
 *         customer phone number
 *         order ID
 *         order date
 *         payment type
 *         is delivery(as boolean)
 *         order total
 *         price
 *         subtotal
 *         fee
 *         tax
 *     </ul></li>
 *     For all the above listed, this class includes getters and setters to allow visibility and ability to update.
 *</p>
 */
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

    /**
     * Pizza getter
     * @return pizzas(as array of pizzas)
     */
    public Pizza[] getPizzas() {
        return pizzas;
    }

    /**
     * Pizza setter
     * @param pizzas
     */
    public void setPizzas(Pizza[] pizzas) {
        this.pizzas = pizzas;
    }
    
    private double total;
    private String stringPrice;

    /**
     * Default constructor
     */
    public Order(){}

    /**
     * String price getter
     * @return price(as string)
     */
    public String getStringPrice() { return stringPrice; }

    /**
     * String price setter
     * @param stringPrice
     */
    public void setStringPrice(String stringPrice) { this.stringPrice = stringPrice; }

    /**
     * Price getter
     * @return price
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Price setter, which also sets string price
     * @param price
     */
    public void setPrice(Double price){
        this.price = price;
        stringPrice = "$" + String.format("%.2f", price);
    }

    /**
     * Subtotal getter
     * @return subtotal
     */
    public double getSubtotal (){ return this.subtotal; }

    /**
     * Subtotal setter
     * @param subtotal
     */
    public void setSubtotal(Double subtotal){
        this.subtotal = subtotal;
    }

    /**
     * Fee getter
     * @return fee
     */
    public double getFee() { return fee; }

    /**
     * Fee setter
     * @param fee
     */
    public void setFee(Double fee) { this.fee = fee; }

    /**
     * Tax getter
     * @return tax
     */
    public double getTax() { return tax; }

    /**
     * tax setter
     * @param tax
     */
    public void setTax(Double tax) { this.tax = tax; }

    /**
     * Total getter
     * @return total
     */
    public double getTotal() { return total; }

    /**
     * total setter
     * @param total
     */
    public void setTotal(Double total) { this.total = total; }

    /**
     * Menu Item setter
     * @param menuItems
     */
    public void setMenuItems(ObservableList<MenuItem> menuItems){
        this.menuItems = menuItems;
    }
}
