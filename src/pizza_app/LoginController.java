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

/**
 * <p>
 *     Precondition: for this class' functions to work properly, you
 *     will need to have the provided customerRecord.csv file in the same folder as the .jar file for the application.
 *     This class imports the javafx.fxml.FXMLLoader so does not need to implement the Initializable interface.
 *     The type of FXML varibales that are used from that library are as follows:
 *     <li><ul>
 *         Pane
 *         JFXTextField
 *         JFXPasswordField
 *     </ul></li>
 *     This class also includes all methods necessary for login functionalities, which include:
 *     <li><ul>
 *         verifying login credentials
 *         adding new user accounts
 *         logging in
 *         verifying phone number validity 
 *         verifying alphabetic validity
 *         adjusting screen accordingly(i.e. login, new account, home)
 *     </ul></li>
 * </p>
 */
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

    /**
     * Validates the input for names to be only alphabetical (w/ characters commonly included in names as well)
     * @param text
     * @return boolean(false if non-alphabetic)
     */
    //validates inputs for names
    private boolean validateAlphabets(String text){
        String[] words = text.split(" ");
        for (String word : words) {
            if (!word.matches("([a-z]|[A-Z]|'|`|-|\\.)+")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates the input for passwords to be only alphanumeric, and a specific length
     * @param text
     * @return boolean(false if non-alphanumeric or outside of length requirements)
     */
    //validates inputs for passwords
    private boolean validatePassword(String text){
        if(!text.matches("([a-z]|[A-Z]|\\d){5,30}")){
            return false;
        }
        return true;
    }

    /**
     * Validates phone number is numeric and correct length
     * @param text
     * @return boolean (false if is not correct length nor is numeric)
     */
    //validates inputs for phone numbers
    private boolean validatePhoneNumber(String text){
        if(!text.matches("\\d{10}")){
            return false;
        }
        return true;
    }

    /**
     * When called, parses through the customerRecord.csv local file and checks if there is an instance 
     * where the passed in phone number and password are listed
     * @param phoneNum
     * @param pWord
     * @return boolean (false if no account found, true if account found)
     * @throws IOException
     */
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

    /**
     * When called, creates a new user account with the inputted parameters and saves it the the customerRecord.csv local file
     * @param phoneNum
     * @param fName
     * @param lName
     * @param password
     * @return boolean(false if new user's phone number already exists in the csv file)
     * @throws IOException
     */
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

    /**
     * <p>When actionEvent occurs, this utilizes multiple if conditionals to allow user to login by inputting the following:
     * <li><ul>
     *     phone number
     *     password
     * </ul></li>
     * If either inputted values are invalid, corresponding error messages will appear and prompt the user to correct their mistake
     *  </p>
     * @param e
     * @throws Exception
     */
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
                Stage checkoutStage = (Stage) root.getScene().getWindow();
                Parent checkOut = FXMLLoader.load(getClass().getResource("check_out_page.fxml"));
                Scene scene = new Scene(checkOut);
                scene.getStylesheets().add("styles.css");
                checkoutStage.setScene(scene);
                checkoutStage.show();
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


    /**
     * <p>When actionEvent occurs, this utilizes multiple if conditionals to allow user to create a new account by inputting the following:
     * <li><ul>
     *     phone number
     *     First Name
     *     Last Name
     *     password
     * </ul></li>
     * If either inputted values are invalid, corresponding error messages will appear and prompt the user to correct their mistake
     *  </p>
     * @param e
     * @throws Exception
     */
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
                    if(!validatePassword(newPassword.getText())){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Account Creation Failed");
                        alert.setHeaderText("Invalid Password!");
                        alert.setContentText("Please make sure you are only using alphanumeric characters and try again. Passwords must be between 5 and 30 characters in length.");
                        alert.showAndWait();
                    }
                    else if(addRecord(newPhone.getText(), newFName.getText(), newLName.getText(), newPassword.getText())) {
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

    /**
     * Changes GUI window to SignUp 
     * @param e
     * @throws Exception
     */
    @FXML //goes to signup
    private void changeToSignup(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        s.setScene(new Scene(root));
        s.show();
    }

    /**
     * Changes GUI window to Login
     * @param e
     * @throws Exception
     */
    @FXML //goes to login
    private void changeToLogin(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        s.setScene(new Scene(root));
        s.show();
    }

    /**
     * Changes GUI window to Home
     * @param e
     * @throws Exception
     */
    @FXML //returns to home page
    private void changeToHome(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage s = (Stage) ( (Node) e.getSource()).getScene().getWindow();
        s.setScene(new Scene(root));
        s.show();
    }

}

