package pizza_app;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This class extends our MenuItem class because of our idea that pizzas and toppings are a type of MenuItem. 
 * Additionally, this uses the following two attributes to describe a Pizza object:
 * <li><ul>
 *     crustType(as String)
 *     toppings (as list of Strings)
 * </ul></li>
 * For all the above listed, the class includes getters and setters to allow visibility and ability to update.
 * </p>
 */
public class Pizza extends MenuItem {
    private String crustType;
    private List<String> toppings = new ArrayList<>(); ;

    /**
     * Overriten constructor that adjusts crust type and size accordingly
     * @param itemSize
     * @param crustType
     */
    public Pizza(String itemSize, String crustType){
        super("Pizza", itemSize);
        this.crustType = crustType;
        this.colDisplay = " Pizza- " + itemSize + "; " + crustType;
    }

    /**
     * Crust type getter
     * @return crust type
     */
    public String getCrustType() {
        return crustType;
    }

    /**
     * Crust type setter
     * @param crustType
     */
    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }

    /**
     * Toppings getter
     * @return toppings
     */
    public List<String> getToppings() {
        return toppings;
    }

    /**
     * Toppings setter
     * @param toppings
     */
    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    /**
     * Appends passed topping to the list of toppings for this pizza
     * @param topping
     */
    public void addTopping(String topping){
        toppings.add(topping);
        colDisplay = colDisplay + "; " + topping;
    }

    /**
     * Increments the item price by taking current itemPrice and adding double passed in
     * @param price
     */
    public void incrementItemPrice(double price){
        itemPrice+=price;
    }

    /**
     * Decrements the item price by taking current itemPrice and subtracting double passed in
     * @param price
     */
    public void decrementItemPrice(double price){
        itemPrice-=price;
    }



}
