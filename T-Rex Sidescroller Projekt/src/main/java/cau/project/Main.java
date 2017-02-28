package main.java.cau.project;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.cau.project.menu.MenuView;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        new MenuView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
