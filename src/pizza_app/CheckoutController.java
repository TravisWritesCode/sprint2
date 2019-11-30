package pizza_app;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.String;
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
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, String> itemColumn;

    @FXML
    private TableColumn<Order, String> priceColumn;

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
    private JFXTextField fees;

    @FXML
    private JFXTextField tax;

    @FXML
    private JFXTextField total;

    private ObservableList<Order> orders = getOrderList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetTotals();
        subtotal.setEditable(false);
        fees.setEditable(false);
        tax.setEditable(false);
        total.setEditable(false);
        itemColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("item"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("stringPrice"));
        orderTable.setItems(orders);
    }

    ObservableList<Order> getOrderList(){
        ObservableList<Order> orders = FXCollections.observableArrayList();
        orders.add(new Order("Lg Pepperoni", 20.00));
        orders.add(new Order("Sm Extra Cheese", 15.00));
        orders.add(new Order("Md Veggie Lovers", 18.00));
        orders.add(new Order("Lg Veggie Lovers",  22.00));
        orders.add(new Order("Md Extra Bacon", 18.00));
        return orders;
    }



    public void addItem() {
        if (item.getValue().toString().length() == 0 || price.getValue().toString().length() == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops. Invalid Entry.");
            alert.setHeaderText("Both 'Price' and 'Item' must be filled in.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
        else {
            Order order = new Order(item.getValue().toString(), Double.parseDouble(price.getValue().toString()));
            orderTable.getItems().add(order);
        }
    }

    @FXML
    private void removeItem(ActionEvent e){
        orderTable.getItems().removeAll(orderTable.getSelectionModel().getSelectedItem());
        resetTotals();
    }

    @FXML
    private void setTotals(ActionEvent e){
        double runningTotal = calculateSubtotal(orders);
        subtotal.setText("$"+ java.lang.String.format("%.2f", runningTotal));
        tax.setText("$"+ java.lang.String.format("%.2f", calculateTax()));
        fees.setText("$2.50");
        total.setText("$"+ java.lang.String.format("%.2f", calculateTotal()));
    }

    public void resetTotals(){
        double runningTotal = calculateSubtotal(orders);
        subtotal.setText("$"+ java.lang.String.format("%.2f", runningTotal));
        tax.setText("$"+ java.lang.String.format("%.2f", calculateTax()));
        fees.setText("$2.50");
        total.setText("$"+ java.lang.String.format("%.2f", calculateTotal()));
    }

    private double calculateSubtotal(ObservableList<Order> orders){
        double subtotal = 0;
        for (Order order : orders) {
            subtotal += order.getPrice();
        }
        return subtotal;
    }

    private double calculateTax(){
        return (calculateSubtotal(orders) * .089);
    }

    private double calculateTotal(){
        return calculateSubtotal(orders) + calculateTax() + 2.5;
    }








}
