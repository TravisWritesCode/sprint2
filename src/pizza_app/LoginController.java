package pizza_app;

import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXPasswordField;

import java.io.*;

public class LoginController {

    @FXML
    private Pane root;

    //fields on login page
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXPasswordField passwordField;
    //fields on sign up page
    @FXML
    private JFXTextField newPhone;
    @FXML
    private JFXPasswordField newPassword;
    @FXML
    private JFXTextField newFName;
    @FXML
    private JFXTextField newLName;
    @FXML
    private JFXPasswordField confPassword;


    //checks database for user account
    private boolean verifyLogin(String phoneNum, String pWord) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("./customerRecord.csv"));
        String row;
        String[] record;
        int lineCount = 0;
        while ((row= csvReader.readLine()) != null){
            if(lineCount>0) {
                record = row.split(",");
                if(record[0].equals(phoneNum)){
                    if(record[3].equals(pWord)) {
                        record = new String[]{};
                        return true;
                    }
                    else{
                        record = new String[]{};
                        return false;
                    }
                }

            }
            lineCount++;
        }
        record = new String[]{};
        return false;
    }
    //adds new user account to database
    private boolean addRecord(String phoneNum, String fName, String lName, String password) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("./customerRecord.csv"));
        String row;
        String[] record;
        int lineCount = 0;
        while ((row= csvReader.readLine()) != null){
            if(lineCount>0) {
                record = row.split(",");
                if(record[0].equals(phoneNum)){
                    return false;
                }

            }
            lineCount++;
        }
        FileWriter csvWriter = new FileWriter("./customerRecord.csv", true);
        csvWriter.append("\n");
        csvWriter.append(phoneNum);
        csvWriter.append(",");
        csvWriter.append(fName);
        csvWriter.append(",");
        csvWriter.append(lName);
        csvWriter.append(",");
        csvWriter.append(password);
        csvWriter.flush();
        csvWriter.close();
        return true;
    }

    @FXML //performs login actions
    private void login(ActionEvent e) throws Exception{
        try {
            if (phoneField.getText().equals("") || passwordField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("All fields must be filled!");
                alert.setContentText("Please try again.");
                alert.showAndWait();
            } else if (verifyLogin(phoneField.getText(), passwordField.getText())) {
                Stage stage = (Stage) root.getScene().getWindow();
                Parent checkOut = FXMLLoader.load(getClass().getResource("check_out_page.fxml"));
                Scene scene = new Scene(checkOut);
                scene.getStylesheets().add("styles.css");
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Invalid Phone# or Password!");
                alert.setContentText("Please try again.");
                alert.showAndWait();
            }
        } catch (FileNotFoundException f){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("customerRecords file is missing!");
            alert.setContentText("Please refer to the README file.");
            alert.showAndWait();
        }
    }

    //validates inputs for names
    private boolean validateAlphabets(String text){
        String[] words = text.split(" ");
        for (String word : words) {
            if (!word.matches("([a-z]||[A-Z])+")) {
                return false;
            }
        }
        return true;
    }
    //validates inputs for phone numbers
    private boolean validatePhoneNumber(String text){
        if(!text.matches("\\d{10}")){
            return false;
        }
        return true;
    }

    @FXML //performs sign up actions
    private void createAccount(ActionEvent e) throws Exception{
        try {
            if (newFName.getText().equals("") || newLName.getText().equals("") || newPhone.getText().equals("")
                    || newPassword.getText().equals("") || confPassword.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account Creation Failed");
                alert.setHeaderText("All fields must be filled!");
                alert.setContentText("Please try again.");
                alert.showAndWait();
            } else if (!validateAlphabets(newFName.getText()) || !validateAlphabets(newLName.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account Creation Failed");
                alert.setHeaderText("Only alphabetic characters allowed for names!");
                alert.setContentText("Please make sure you are entering only letters and try again.");
                alert.showAndWait();
            } else if (!validatePhoneNumber(newPhone.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account Creation Failed");
                alert.setHeaderText("Phone number must be 10 digits!");
                alert.setContentText("Please make sure there are no spaces or alphabetic characters and try again.");
                alert.showAndWait();
            } else {
                if (newPassword.getText().equals(confPassword.getText())) {
                    if (addRecord(newPhone.getText(), newFName.getText(), newLName.getText(), newPassword.getText())) {
                        Stage stage = (Stage) root.getScene().getWindow();
                        Parent checkOut = FXMLLoader.load(getClass().getResource("check_out_page.fxml"));
                        Scene scene = new Scene(checkOut);
                        scene.getStylesheets().add("styles.css");
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Account Creation Failed");
                        alert.setHeaderText("Account already exists!");
                        alert.setContentText("Please login.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Account Creation Failed");
                    alert.setHeaderText("Passwords don't match");
                    alert.setContentText("Please try again.");
                    alert.showAndWait();
                }
            }
        } catch (FileNotFoundException f){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("customerRecords file is missing!");
            alert.setContentText("Please refer to the README file.");
            alert.showAndWait();
        }
    }

    @FXML //goes to signup
    private void changeToSignup(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        s.setScene(new Scene(root));
        s.show();
    }

    @FXML //goes to login
    private void changeToLogin(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        s.setScene(new Scene(root));
        s.show();
    }

    @FXML //returns to home page
    private void changeToHome(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        s.setScene(new Scene(root));
        s.show();
    }

}

