package programming.projekt.trex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("MenuView.fxml"));
        primaryStage.setTitle("T-Rex Sidescroller Game");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
