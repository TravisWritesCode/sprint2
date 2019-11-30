package pizza_app;

public class MenuItem {
    private String itemName;
    private String itemSize;
    protected double itemPrice;
    private String itemStringPrice;
    protected String colDisplay;

    public MenuItem(String itemName, String itemSize){
        this.itemName = itemName;
        this.itemSize = itemSize;
        this.itemStringPrice = "$"+ java.lang.String.format("%.2f", itemPrice);
        this.colDisplay = itemName + "; " + itemSize;
    }

    public MenuItem(String itemName){
        this.itemName = itemName;
        this.itemStringPrice = "$"+ java.lang.String.format("%.2f", itemPrice);
        this.colDisplay = itemName;
    }

    public MenuItem(){}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
        this.itemStringPrice = "$"+ java.lang.String.format("%.2f", itemPrice);
    }

    public String getItemStringPrice() {
        return itemStringPrice;
    }

    public void setItemStringPrice(Double price) {
        this.itemStringPrice = "$"+ java.lang.String.format("%.2f", itemPrice);
    }

    public String getColDisplay(){
        return this.colDisplay;
    }

    public void setColDisplay(String colDisplay){
        this.colDisplay = colDisplay;
    }
}
