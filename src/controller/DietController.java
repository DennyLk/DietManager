package controller;

import javafx.stage.Stage;
import model.DietModel;
import model.Log;
import view.HomeUI;
import view.InfoUI;
import view.UI;
import view.UIFactory;
import view.WeightUI;

public class DietController {
    DietModel model;
    UIFactory view;

    public DietController(DietModel model, UIFactory view) {
        this.model = model;
        this.view = view;
    }
    
    public void run(Stage primaryStage) {
        Log.getData();
        UI ui = UIFactory.createUI("Home", primaryStage);
        ui.display();
        model.getAllFoods();
        model.getAllLogs();
        InfoUI.setLog(model.getfLog());
        WeightUI.setWeight(model.getwLog());
        HomeUI.setFoods(model.getFoods());
    }
}
