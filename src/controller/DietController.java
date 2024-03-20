package controller;

import java.util.ArrayList;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.DietModel;
import model.Food;
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
        UI ui = UIFactory.createUI("Home",primaryStage);
        ui.display();
        model.getAllFoods();
        model.getAllLogs();
        InfoUI.setLog(Log.getfLog());
        WeightUI.setWeight(Log.getwLog());
        HomeUI.setFoods(Food.getbFoods());
        HomeUI.setRecipes(Food.getrFoods());

    }

}
