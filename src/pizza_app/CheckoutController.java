package pizza_app;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private JFXCheckBox bellPeppers;

    @FXML
    private JFXCheckBox pineapple;

    @FXML
    private JFXCheckBox meatLovers;

    @FXML
    private JFXCheckBox veggieLovers;

    @FXML
    private JFXCheckBox mushrooms;

    @FXML
    private JFXRadioButton delivery;

    @FXML
    private JFXRadioButton pickup;

    //this is a list to contain the items in the order
    private ObservableList<MenuItem> menuItems = getItemList();

    //Create order
    Order order = new Order();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Pizza size dropdown
        final ArrayList<String> PIZZASIZES = new ArrayList<String>();
        ObservableList<String> pizzaSizes = FXCollections.observableList(PIZZASIZES);
        pizzaSizes.add("Small +$10.00");
        pizzaSizes.add("Medium +$15.00");
        pizzaSizes.add("Large +20.00");
        size.setItems(pizzaSizes);

        //crust type dropdown
        final ArrayList<String> CRUSTTYPE = new ArrayList<String>();
        ObservableList<String> crustType = FXCollections.observableList(CRUSTTYPE);
        crustType.add("Thin Crust");
        crustType.add("Deep Dish +$5.00");
        type.setItems(crustType);

        //set pickup by default
        pickup.setSelected(true);

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
/*    public void addMenuItem() {
        if (size.getValue().toString().length() == 0 || type.getValue().toString().length() == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops. Invalid Entry.");
            alert.setHeaderText("Both 'Price' and 'Item' must be filled in.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
        else {
            MenuItem item = new MenuItem (itemName.getValue().toString(), Double.parseDouble(price.getValue().toString()));
            orderTable.getItems().add(order);
        }
    }*/

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
            if(size.getValue().toString().equals("Small +$10.00")){
                pizza.incrementItemPrice(10);
            }
            if(size.getValue().toString().equals("Medium +$15.00")){
                pizza.incrementItemPrice(15);
            }
            if(size.getValue().toString().equals("Large +$20.00")){
                pizza.incrementItemPrice(20);
            }
            if(type.getValue().toString().equals("Deep Dish +$5.00")){
                pizza.incrementItemPrice(5);
            }
            if (pepperoni.isSelected()) {
                System.out.println(pepperoni.isSelected());
                pizza.incrementItemPrice(2.5);
                pizza.addTopping("Pepperoni");
            }
            if (sausage.isSelected()){
                pizza.incrementItemPrice(2.5);
                pizza.addTopping("Sausage");
            }
            if (ham.isSelected()){
                pizza.incrementItemPrice(2.5);
                pizza.addTopping("Ham");
            }
            if (extraCheese.isSelected()){
                pizza.incrementItemPrice(2.5);
                pizza.addTopping("Extra Cheese");
            }
            if (bellPeppers.isSelected()){
                pizza.incrementItemPrice(2.5);
                pizza.addTopping("Bell Peppers");
            }
            if (pineapple.isSelected()){
                pizza.incrementItemPrice(2.5);
                pizza.addTopping("Pineapple");
            }
            if (meatLovers.isSelected()){
                pizza.incrementItemPrice(4.0);
                pizza.addTopping("Meat Lovers");
            }
            if (veggieLovers.isSelected()){
                pizza.incrementItemPrice(6.0);
                pizza.addTopping("Veggie Lovers");
            }
            if (mushrooms.isSelected()){
                pizza.incrementItemPrice(2.5);
                pizza.addTopping("Mushrooms");
            }
            pizza.setItemStringPrice(pizza.getItemPrice());
            orderTable.getItems().add(pizza);
            order.setMenuItems(menuItems);
            order.setTotal(calculateTotal());
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
