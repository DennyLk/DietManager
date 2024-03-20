package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.DietModel;
import view.UIFactory;

public class DietRunner extends Application {


    //Main method
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        DietController controller = new DietController(new DietModel(), new UIFactory());
        controller.run(primaryStage);
    } 
}
