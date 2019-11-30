package pizza_app;

import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXPasswordField;

public class Controller {

    @FXML
    private Pane root;

    @FXML
    private JFXTextField phoneNumber;

    @FXML
    private JFXPasswordField password;

    public void Login(ActionEvent event) throws Exception{
       /* if(phoneNumber.getText().equals("111-111-1111") && password.getText().equals("password") ){*/
        if (true){
            System.out.println("Login Successful!");
            Stage stage = (Stage) root.getScene().getWindow();
            Parent checkOut = FXMLLoader.load(getClass().getResource("check_out_page.fxml"));
            Scene scene = new Scene(checkOut);
            scene.getStylesheets().add("styles.css");
            stage.setScene(scene);
            stage.show();
        }
        else {
            System.out.println("Login Failed");
        }
    }
}
