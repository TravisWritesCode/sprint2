package pizza_app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * This is the main class which includes the overriden start function and the main function which will launch the application
 */
public class Main extends Application {
    /**
     * Ovveriden start method which assigns the title and root for our application to be
     * the home.fxml file
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("Mom & Pop's Pizza");
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches application with above specifications when this class is ran
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
