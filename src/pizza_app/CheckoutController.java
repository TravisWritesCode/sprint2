package pizza_app;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {

    @FXML
    private TableView<MenuItem> orderTable;

    @FXML
    private TableColumn<MenuItem, String> itemColumn;

    @FXML
    private TableColumn<MenuItem, String> priceColumn;

    @FXML
    private ChoiceBox item;

    @FXML
    private ChoiceBox price;

    @FXML
    private TableColumn<String, String> summaryDescriptionColumn;

    @FXML
    private TableColumn<String, String> totalColumn;

    @FXML
    private JFXButton submitOrder;

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

    //this is a list to contain the items in the order
    private ObservableList<MenuItem> menuItems = getItemList();

    //Create order
    Order order = new Order();

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

    // this method returns all of the items in the order
    ObservableList<MenuItem> getItemList(){
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
        return menuItems;
    }

    // adds item to order
    public void addSide(ActionEvent e) {
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

    // adds item to order
    public void addDrink(ActionEvent e) {
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
                drink = new MenuItem ("Medium " + flavors.getValue().toString());
                drink.setItemPrice(1);
                drink.setItemStringPrice(drink.getItemPrice());
                orderTable.getItems().add(drink);
            }
        }
        resetTotals();
    }

    // adds pizza to order
    public void addPizza() {
        if (size.getValue() == null|| type.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops. Invalid Entry.");
            alert.setHeaderText("Both 'Size' and 'Crust Type' must be selected.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
        else {
            Pizza pizza = new Pizza (size.getValue().toString(), type.getValue().toString());
            System.out.println("Pizza Type: " + size.getValue().toString() + " " +  type.getValue().toString());
            System.out.println(size.getValue().toString());
            if(size.getValue().toString().equals("Small +$4.00")){
                pizza.incrementItemPrice(4);
            }
            if(size.getValue().toString().equals("Medium +$6.00")){
                pizza.incrementItemPrice(6);
            }
            if(size.getValue().toString().equals("Large +$8.00")){
                pizza.incrementItemPrice(8);
            }
            if(size.getValue().toString().equals("Extra Large +$10.00")){
                pizza.incrementItemPrice(10);
            }
            if (pepperoni.isSelected()) {
                if (pizza.getToppings().size() >= 1){
                    pizza.addTopping("Pepperoni +$0.25");
                } else {
                    pizza.addTopping("Pepperoni");
                }
            }
            if (sausage.isSelected()){
                if (pizza.getToppings().size() >= 1){
                    pizza.addTopping("Sausage +$0.25");
                } else {
                    pizza.addTopping("Sausage");
                }
            }
            if (ham.isSelected()){
                if (pizza.getToppings().size() >= 1){
                    pizza.addTopping("Ham +$0.25");
                } else {
                    pizza.addTopping("Ham");
                }
            }
            if (extraCheese.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Extra Cheese +$0.25");
                } else {
                    pizza.addTopping("Extra Cheese");
                }
            }
            if (greenPeppers.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Green Peppers +$0.25");
                } else {
                    pizza.addTopping("Green Peppers");
                }
            }
            if (pineapple.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Pineapple +$0.25");
                } else {
                    pizza.addTopping("Pineapple");
                }
            }
            if (onion.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Onion +$0.25");
                } else {
                    pizza.addTopping("Onion");
                }
            }
            if (tomato.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Tomato +$0.25");
                } else {
                    pizza.addTopping("Tomato");
                }
            }
            if (mushrooms.isSelected()){
                if ( pizza.getToppings().size() >= 1){
                    pizza.addTopping("Mushrooms +$0.25");
                } else {
                    pizza.addTopping("Mushrooms");
                }
            }
            double toppingPrice = 0.0;
            if ( pizza.getToppings().size() > 1){
                toppingPrice = .25 * pizza.getToppings().size();
            }
            pizza.incrementItemPrice(toppingPrice);
            pizza.setItemStringPrice(pizza.getItemPrice());
            orderTable.getItems().add(pizza);
        }
        resetTotals();
    }

    public void setIsDelivery(ActionEvent e){
        if (delivery.isSelected()){
            fee.setText("$"+ java.lang.String.format("%.2f", 2.5));
            resetTotals();
        } else {
            fee.setText("$"+ java.lang.String.format("%.2f", 0.0));
            resetTotals();
        }
    }

    @FXML
    private void removeItem(ActionEvent e){
        orderTable.getItems().removeAll(orderTable.getSelectionModel().getSelectedItem());
        resetTotals();
        System.out.println(total.getText());
        if (total.getText() == "$2.50" && delivery.isSelected() ){
            fee.setText("$0.00");
            pickup.setSelected(true);
        }
        order.setTotal(calculateTotal());
    }

    @FXML
    private void setTotals(ActionEvent e){
        subtotal.setText("$"+ java.lang.String.format("%.2f", calculateSubtotal()));
        tax.setText("$"+ java.lang.String.format("%.2f", calculateTax()));
        total.setText("$"+ java.lang.String.format("%.2f", calculateTotal()));
    }

    public void resetTotals(){
        double mySubtotal =calculateSubtotal();
        subtotal.setText("$"+ java.lang.String.format("%.2f", mySubtotal));
        tax.setText("$"+ java.lang.String.format("%.2f", calculateTax()));
        total.setText("$"+ java.lang.String.format("%.2f", calculateTotal()));
        order.setTotal(calculateTotal());
    }

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

    private double calculateTax (){
        return (calculateSubtotal() * .089);
    }

    private double calculateTotal(){
         return calculateSubtotal() + calculateTax();
    }


}
