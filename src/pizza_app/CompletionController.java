package pizza_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * <p>
 *     This class serves to change the view to the final pages of the ordering/checkout process.
 * </p>
 */
public class CompletionController{


    /**
     * Returns GUI to 'Checkout/Pizza Builder' page by closing the pop-up
     * @param e
     */
    //closes popup confirmation window
    @FXML
    private void returnToOrder(ActionEvent e){
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        s.close();
    }

    /**
     * Changes GUI to 'Order Placed' page by closing the pop-up window
     * @param e
     * @throws IOException
     */
    //closes popup confirmation window and proceeds to order confirmation
    @FXML
    private void confirmOrder(ActionEvent e) throws IOException {
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        s.close();
        Stage s2 = (Stage)(s.getOwner().getScene().getWindow()); //gets original windows
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("confirmation.fxml")));
        s2.setScene(scene);
        s2.show();
    }

    /**
     * Changes GUI window to Home
     * @param e
     * @throws IOException
     */
    //returns to home page
    @FXML
    private void returnToHome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        s.setScene(new Scene(root));
        s.show();
    }
}
