package controller;

import javafx.stage.Stage;
import view.UI;
import view.UIFactory;

public class DietController {
    
    public static void run(Stage primaryStage) {
        UI ui = UIFactory.createUI("Home",primaryStage);
        ui.display();
    }
}
