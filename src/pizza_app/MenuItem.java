package pizza_app;

/**
 *<p>
 *     This class is used to create instances of menu items with the following attributes:
 *     <li><ul>
 *         item name
 *         item size
 *         item price
 *         item price(as a string)
 *         column display
 *     </ul></li>
 *     For all the above listed, this class includes getters and setters to allow visibility and ability to update.
 *</p>
 */
public class MenuItem {
    private String itemName;
    private String itemSize;
    protected double itemPrice;
    private String itemStringPrice;
    protected String colDisplay;

    /**
     * Overriten constructor that assigns the item name, size, string price, and display.
     * @param itemName
     * @param itemSize
     */
    public MenuItem(String itemName, String itemSize){
        this.itemName = itemName;
        this.itemSize = itemSize;
        this.itemStringPrice = "$"+ java.lang.String.format("%.2f", itemPrice);
        this.colDisplay = itemName + "; " + itemSize;
    }
    /**
     * Overriten constructor that assigns the item name, string price, and display.
     * @param itemName
     */
    public MenuItem(String itemName){
        this.itemName = itemName;
        this.itemStringPrice = "$"+ java.lang.String.format("%.2f", itemPrice);
        this.colDisplay = itemName;
    }

    /**
     * Default constructor
     */
    public MenuItem(){}

    /**
     * Item name getter
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Item name setter
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Item size getter
     * @return item size
     */
    public String getItemSize() {
        return itemSize;
    }

    /**
     * Item size setter
     * @param itemSize
     */
    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    /**
     * Item price getter
     * @return item price
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * Item price setter
     * @param itemPrice
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
        this.itemStringPrice = "$"+ java.lang.String.format("%.2f", itemPrice);
    }

    /**
     * Item price getter as String
     * @return item string price
     */
    public String getItemStringPrice() {
        return itemStringPrice;
    }

    /**
     * Item string price setter
     * @param price
     */
    public void setItemStringPrice(Double price) {
        this.itemStringPrice = "$"+ java.lang.String.format("%.2f", itemPrice);
    }

    /**
     * Column display setter
     * @return col display
     */
    public String getColDisplay(){
        return this.colDisplay;
    }

    /**
     * Col display setter
     * @param colDisplay
     */
    public void setColDisplay(String colDisplay){
        this.colDisplay = colDisplay;
    }
}
