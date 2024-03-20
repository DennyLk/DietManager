package controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class DietRunner extends Application {


    //Main method
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        DietController.run(primaryStage);
    } 
}
