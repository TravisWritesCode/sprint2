package pizza_app;
import java.util.ArrayList;
import java.util.List;

public class Pizza extends MenuItem {
    private String crustType;
    private List<String> toppings = new ArrayList<>(); ;

    public Pizza(String itemSize, String crustType){
        super("Pizza", itemSize);
        this.crustType = crustType;
        this.colDisplay = itemSize + " Pizza ;" + crustType;
    }

    public String getCrustType() {
        return crustType;
    }

    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public void addTopping(String topping){
        toppings.add(topping);
        colDisplay = colDisplay + "; " + topping;
    }

    public void incrementItemPrice(double price){
        itemPrice+=price;
    }

    public void decrementItemPrice(double price){
        itemPrice-=price;
    }



}
