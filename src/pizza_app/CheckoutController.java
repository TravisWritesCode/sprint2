package pizza_app;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * <p>This class implements the JavaFX interface Initializable which allows for intereaction
 * to variables injected with @FXML. The type of FXML variables that were used are as follows:
 * <li><ul>
 *     TableColumn
 *     ChoiceBox
 *     JFXButton
 *     JFXTextField
 *     JFXComboBox
 *     JFXCheckBox
 *     JFXRadioButton
 *     ObservableList
 * </ul></li>
 * This class also includes an overriden initialize method and other methods to assist in making
 * changes to pizza order information.
 * </p>
 */
public class CheckoutController implements Initializable {

    @FXML
    protected TableView<MenuItem> orderTable;

    @FXML
    private TableColumn<MenuItem, String> itemColumn;

    @FXML
    private TableColumn<MenuItem, String> priceColumn;

    @FXML
    private TableColumn<MenuItem, String> reviewItemColumn;

    @FXML
    private TableColumn<MenuItem, String> reviewPriceColumn;

    @FXML
    private ChoiceBox item;

    @FXML
    private ChoiceBox price;

    @FXML
    private TableColumn<String, String> summaryDescriptionColumn;

    @FXML
    private TableColumn<String, String> totalColumn;

    @FXML
    private JFXTextField subtotal;

    @FXML
    private JFXTextField fee;

    @FXML
    private JFXTextField tax;

    @FXML
    private JFXTextField total;

    @FXML
    private JFXComboBox size;

    @FXML
    private JFXComboBox type;

    @FXML
    private JFXCheckBox pepperoni;

    @FXML
    private JFXCheckBox sausage;

    @FXML
    private JFXCheckBox ham;

    @FXML
    private JFXCheckBox extraCheese;

    @FXML
    private JFXCheckBox greenPeppers;

    @FXML
    private JFXCheckBox pineapple;

    @FXML
    private JFXCheckBox onion;

    @FXML
    private JFXCheckBox tomato;

    @FXML
    private JFXCheckBox mushrooms;

    @FXML
    private JFXRadioButton delivery;

    @FXML
    private JFXRadioButton pickup;

    @FXML
    private JFXComboBox flavors;

    @FXML
    private JFXCheckBox breadsticks;

    @FXML
    private JFXCheckBox breadstickBites;

    @FXML
    private JFXCheckBox cookie;

    @FXML
    private JFXRadioButton small;

    @FXML
    private JFXRadioButton medium;

    @FXML
    private JFXRadioButton large;

    @FXML
    private Hyperlink viewToppingPrices;

    @FXML
    private TableView<MenuItem> reviewOrderTable;


    //this is a list to contain the items in the order
    private static ObservableList<MenuItem> menuItems = getItemList();

    public static ObservableList<MenuItem> getMenuItems(){
        return menuItems;
    }

    public static void clearMenuItems(){
       menuItems.setAll();
    }

    //Create order
    Order order = new Order();

    /**
     * <p>
     *     This is an overriden method for initialize which instantiates all necessary GUI
     * features for the checkout process in our "Pizza App". It also utilizes JavaFX's
     * ObservableList to actively listen and track any changes when they occur for the
     * following attributes of the order:
     * <li><ul>
     *     pizza size
     *     crust type
     *     drink flavor
     * </ul></li>
     * </p>
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Pizza size dropdown
        final ArrayList<String> PIZZASIZES = new ArrayList<String>();
        ObservableList<String> pizzaSizes = FXCollections.observableList(PIZZASIZES);
        pizzaSizes.add("Small +$4.00");
        pizzaSizes.add("Medium +$6.00");
        pizzaSizes.add("Large +$8.00");
        pizzaSizes.add("Extra Large +$10.00");
        size.setItems(pizzaSizes);

        //crust type dropdown
        final ArrayList<String> CRUSTTYPE = new ArrayList<String>();
        ObservableList<String> crustType = FXCollections.observableList(CRUSTTYPE);
        crustType.add("Thin");
        crustType.add("Regular");
        crustType.add("Pan");
        type.setItems(crustType);

        //drink flavor dropdown
        final ArrayList<String> DRINKFLAVORS = new ArrayList<String>();
        ObservableList<String> drinkFlavors = FXCollections.observableList(DRINKFLAVORS);
        drinkFlavors.add("Pepsi");
        drinkFlavors.add("Diet Pepsi");
        drinkFlavors.add("Orange");
        drinkFlavors.add("Diet Orange");
        drinkFlavors.add("Root Beer");
        drinkFlavors.add("Diet Root Beer");
        drinkFlavors.add("Sierra Mist");
        drinkFlavors.add("Lemonade");
        flavors.setItems(drinkFlavors);

        //set pickup by default
        pickup.setSelected(true);

        //set small by default
        small.setSelected(true);

        //Set totals in order summary pane
        resetTotals();

        //Used text boxes to display info, so I made them un-editable
        subtotal.setEditable(false);
        fee.setEditable(false);
        tax.setEditable(false);
        total.setEditable(false);

        //define columns in order summary table
        itemColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("colDisplay"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemStringPrice"));
        itemColumn.setCellFactory(TooltippedTableCell.forTableColumn());
        orderTable.setItems(menuItems);


        //Set delivery fee set to 0.00 in order summary box
        fee.setText("$0.00");

    }

    /**
     * <p>
     *     Returns all selected items in the current order session by calling on
     *     FXCollections.orbservableArrayList()
     * </p>
     * @return ObservableList of type MenuItem
     */
    // this method returns all of the items in the order
    private static ObservableList<MenuItem> getItemList(){
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
        return menuItems;
    }

    /**
     * <p>
     *     Specifically to pizza sides, this utilizes multiple if conditionals to create a menu item, set item price, set
     *     string price and add the new item to the orderTable Object once one of the following
     *     checkboxes are selected:
     *     <li><ul>
     *         breadstick
     *         breadstickBites
     *         cookie
     *     </ul></li>
     *     If none of these are selected, it alerts user that their selection was invalid.
     * </p>
     * @param e
     */
    // adds item to order
    @FXML
    private void addSide(ActionEvent e) {
        if ( !breadsticks.isSelected() && !breadstickBites.isSelected() && !cookie.isSelected()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops. Invalid Entry.");
            alert.setHeaderText("No side items are selected.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
        else {
            MenuItem side = new MenuItem();
            if (breadsticks.isSelected()) {
                side = new MenuItem ("Bread Sticks");
                side.setItemPrice(2);
                side.setItemStringPrice(side.getItemPrice());
                orderTable.getItems().add(side);
            }
            if (breadstickBites.isSelected()){
                side = new MenuItem ("Bread Stick Bites");
                side.setItemPrice(2);
                side.setItemStringPrice(side.getItemPrice());
                orderTable.getItems().add(side);
            }
            if (cookie.isSelected()){
                side = new MenuItem ("Big Chocolate Chip Cookie");
                side.setItemPrice(4);
                side.setItemStringPrice(side.getItemPrice());
                orderTable.getItems().add(side);
            }
        }
        resetTotals();
    }

    /**
     * <p>
     *     Specifically to drink sizes, this utilizes multiple if conditionals to create a menu item, set item price, set
     *     string price and add the new item to the orderTable Object once one of the following
     *     checkboxes are selected:
     *     <li><ul>
     *         small
     *         medium
     *         large
     *     </ul></li>
     *     If none of these are selected, it alerts user that their selection was invalid.
     * </p>
     * @param e
     */
    // adds item to order
    @FXML
    private void addDrink(ActionEvent e) {
        if (flavors.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops. Invalid Entry.");
            alert.setHeaderText("A flavor must be selected.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
        else {
            MenuItem drink = new MenuItem();
            if (small.isSelected()) {
                drink = new MenuItem ("Small " + flavors.getValue().toString());
                drink.setItemPrice(1);
                drink.setItemStringPrice(drink.getItemPrice());
                orderTable.getItems().add(drink);
            }
            if (medium.isSelected()){
                drink = new MenuItem ("Medium " + flavors.getValue().toString());
                drink.setItemPrice(1);
                drink.setItemStringPrice(drink.getItemPrice());
                orderTable.getItems().add(drink);
            }
            if (large.isSelected()){
                drink = new MenuItem ("Large " + flavors.getValue().toString());//yo I changed the string from medium to large
                drink.setItemPrice(1);
                drink.setItemStringPrice(drink.getItemPrice());
                orderTable.getItems().add(drink);
            }
        }
        resetTotals();
    }

    /**
     * <p>
     *     Specifically to drink sizes, this utilizes multiple if conditionals to add a pizza with specified size,
     *     crust type, and toppings by selecting the coordinating buttons. The options for sizes are as follows:
     *     <li><ul>
     *         small
     *         medium
     *         large
     *         extra-large
     *     </ul></li>
     *     The options for crusts are:
     *     <li><ul>
     *         thin crust
     *         hand tossed
     *         pan
     *     </ul></li>
     *     The options for toppings are:
     *     <li><ul>
     *         pepperoni
     *         sausage
     *         ham
     *         extra cheese
     *         green pepper
     *         pineapple
     *         onion
     *         tomato
     *         mushroom
     *     </ul></li>
     *     If no size nor crust type is selected, it alerts user that their selection was invalid. Also
     *     note that the first topping is free but any additional topping will incur additional charges.
     * </p>
     */
    // adds pizza to order
    @FXML
    private void addPizza() {
        if (size.getValue() == null|| type.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops. Invalid Entry.");
            alert.setHeaderText("Both 'Size' and 'Crust Type' must be selected.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
        else {
            double toppingCost = 0;
            String stringToppingCost = "";
            Pizza pizza = new Pizza (size.getValue().toString(), type.getValue().toString());
            if(size.getValue().toString().equals("Small +$4.00")){
                pizza.incrementItemPrice(4);
                toppingCost = 0.50;
                stringToppingCost = java.lang.String.format("%.2f", toppingCost);
            }
            if(size.getValue().toString().equals("Medium +$6.00")){
                pizza.incrementItemPrice(6);
                toppingCost = 0.75;
                stringToppingCost = java.lang.String.format("%.2f", toppingCost);
            }
            if(size.getValue().toString().equals("Large +$8.00")){
                pizza.incrementItemPrice(8);
                toppingCost = 1.00;
                stringToppingCost = java.lang.String.format("%.2f", toppingCost);
            }
            if(size.getValue().toString().equals("Extra Large +$10.00")){
                pizza.incrementItemPrice(10);
                toppingCost = 1.25;
                stringToppingCost = java.lang.String.format("%.2f", toppingCost);
            }
            if (pepperoni.isSelected()) {
                if (pizza.getToppings().size() >= 1){
                    pizza.addTopping("Pepperoni +$" + stringToppingCost);
                }else {
                    pizza.addTopping("Pepperoni");
                }
            }
            if (sausage.isSelected()){
                if (pizza.getToppings().size() >= 1){
                    pizza.addTopping("Sausage +$" + stringToppingCost);
                } else {
                    pizza.addTopping("Sausage");
                }
            }
            if (ham.isSelected()){
                if (pizza.getToppings().size() >= 1){
                    pizza.addTopping("Ham +$" + stringToppingCost);
                } else {
                    pizza.addTopping("Ham");
                }
            }
            if (extraCheese.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Extra Cheese +$" + stringToppingCost);
                } else {
                    pizza.addTopping("Extra Cheese");
                }
            }
            if (greenPeppers.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Green Peppers +$" + stringToppingCost);
                } else {
                    pizza.addTopping("Green Peppers");
                }
            }
            if (pineapple.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Pineapple +$" + stringToppingCost);
                } else {
                    pizza.addTopping("Pineapple");
                }
            }
            if (onion.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Onion +$" + stringToppingCost);
                } else {
                    pizza.addTopping("Onion");
                }
            }
            if (tomato.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Tomato +$" + stringToppingCost);
                } else {
                    pizza.addTopping("Tomato");
                }
            }
            if (mushrooms.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Mushrooms +$" + stringToppingCost);
                } else {
                    pizza.addTopping("Mushrooms");
                }
            }
            double toppingPrice = 0.0;
            if (pizza.getToppings().size() > 1){
                toppingPrice = toppingCost * (pizza.getToppings().size() - 1);
            }
            pizza.incrementItemPrice(toppingPrice);
            pizza.setItemStringPrice(pizza.getItemPrice());
            orderTable.getItems().add(pizza);
            toppingCost = 0;
        }
        resetTotals();
    }

    /**
     * If delivery is selected, sets the delivery fee to 2.50
     *  else keeps the delivery fee section to 0.0
     * @param e
     */
    @FXML
    private void setIsDelivery(ActionEvent e){
        if (delivery.isSelected()){
            fee.setText("$"+ java.lang.String.format("%.2f", 2.5));
            resetTotals();
        } else {
            fee.setText("$"+ java.lang.String.format("%.2f", 0.0));
            resetTotals();
        }
    }

    /**
     * If action prompted, will do the following:
     * <li><ol>
     *     remove all items from the orderTable
     *     resetTotal
     *     (if delivery was previously selected) will set pickup to be true instead and set the delivery fee to 0.0
     * </ol></li>
     * @param e
     */
    @FXML
    private void removeItem(ActionEvent e){
        orderTable.getItems().removeAll(orderTable.getSelectionModel().getSelectedItem());
        resetTotals();
        if (total.getText() == "$2.50" && delivery.isSelected() ){
            fee.setText("$0.00");
            pickup.setSelected(true);
        }
        order.setTotal(calculateTotal());
    }

    /**
     * When prompted, sets the text of the following to have 2 decimal places and a preceding $ sign:
     * <li><ul>
     *     subtotal
     *     tax (going according to current Atlanta food tax percentage)
     *     total (subtotal + tax)
     * </ul></li>
     * @param e
     */
    @FXML
    private void setTotals(ActionEvent e){
        subtotal.setText("$"+ java.lang.String.format("%.2f", calculateSubtotal()));
        tax.setText("$"+ java.lang.String.format("%.2f", calculateTax()));
        total.setText("$"+ java.lang.String.format("%.2f", calculateTotal()));
    }
    /**
     * When called, resets the text of the following to have 2 decimal places and a preceding $ sign:
     * <li><ul>
     *     subtotal
     *     tax (going according to current Atlanta food tax percentage)
     *     total (subtotal + tax)
     * </ul></li>
     */
    private void resetTotals(){
        double mySubtotal =calculateSubtotal();
        subtotal.setText("$"+ java.lang.String.format("%.2f", mySubtotal));
        tax.setText("$"+ java.lang.String.format("%.2f", calculateTax()));
        total.setText("$"+ java.lang.String.format("%.2f", calculateTotal()));
        order.setTotal(calculateTotal());
    }

    /**
     * When called, will calculate the current subtotal of the order by adding
     * the price of all MenuItems currently selected and (if delivery is/was selected)
     * will add an additional 2.50
     * @return subtotal
     */
    private double calculateSubtotal(){
        double mySubtotal = 0.0;
        for (MenuItem item : menuItems) {
            mySubtotal += item.getItemPrice();
        }
        if(delivery.isSelected()){
            return mySubtotal += 2.5;
        } else {
            return mySubtotal;
        }
    }

    /**
     * When called, will calculate the food tax amount based on the current subtotal. Note: this uses
     * Atlanta's current food tax, 8.9%
     * @return tax
     */
    private double calculateTax (){
        return (calculateSubtotal() * .089);
    }

    /**
     * When called, will calculate the total by adding the current subtotal and tax.
     * @return total
     */
    private double calculateTotal(){
        return calculateSubtotal() + calculateTax();
    }

    @FXML
    private void showToppingPrices(ActionEvent event) throws IOException {
        try {
            viewToppingPrices.setDisable(true);
            Parent root = FXMLLoader.load(getClass().getResource("toppingPrices.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Topping Prices");
            stage.setScene(new Scene(root, 375, 200));
            stage.showAndWait();
            viewToppingPrices.setDisable(false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane p1;
    @FXML
    private AnchorPane p2;
    @FXML
    private AnchorPane p3;
    @FXML
    private AnchorPane tablePane;
    @FXML
    private JFXButton removeButton;
    @FXML
    private AnchorPane deliveryPane;
    @FXML
    private JFXButton submitOrder;
    @FXML
    private JFXButton proceedButton;
    @FXML
    private JFXButton returnButton;
    @FXML
    private Text orderText;
    @FXML
    private Line orderLine;
    @FXML
    private VBox buttonBox;


    /**
     * Changes GUI to 'Order Confirmation Prompt' page
     * @param e
     */
    @FXML
    private void orderSummary(ActionEvent e){
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        tablePane.setLayoutX(280);
        buttonBox.setLayoutX(15);

        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
        removeButton.setVisible(false);
        submitOrder.setVisible(false);
        deliveryPane.setVisible(false);

        proceedButton.setVisible(true);
        returnButton.setVisible(true);
        orderText.setVisible(true);
        orderLine.setVisible(true);

    }

    /**
     * Changes GUI back to 'Checkout/Pizza Builder' page
     * @param e
     */
    @FXML
    private void returnToOrder(ActionEvent e){
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        tablePane.setLayoutX(529);
        buttonBox.setLayoutX(200);

        p1.setVisible(true);
        p2.setVisible(true);
        p3.setVisible(true);
        removeButton.setVisible(true);
        submitOrder.setVisible(true);
        deliveryPane.setVisible(true);

        proceedButton.setVisible(false);
        returnButton.setVisible(false);
        orderText.setVisible(false);
        orderLine.setVisible(false);
    }

    /**
     * Changes GUI to 'Order Placed'
     * @param e
     * @throws IOException
     */
    //proceeds to order confirmation
    @FXML
    private void confirmOrder(ActionEvent e) throws IOException {
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("confirmation.fxml")));
        s.setScene(scene);
        s.show();
    }

}
