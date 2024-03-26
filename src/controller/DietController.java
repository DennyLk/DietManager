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

        if (ui instanceof HomeUI) {
            HomeUI.setFoods(model.getFoods(), HomeUI.getFoodsBox());
            HomeUI.setFoods(model.getFoods(), HomeUI.getRecipeBox());
            HomeUI.setQuantity(10, HomeUI.getQuantityBox());
            ((HomeUI) ui).getAddButton().setOnAction(e -> DietModel.addFood(((HomeUI) ui).getFoodInput().getText()));
            ((HomeUI) ui).getAddRecipeButton()
                    .setOnAction(e -> DietModel.addRecipe(((HomeUI) ui).getRecipeInput().getText()));
            ((HomeUI) ui).getLog().setOnAction(
                    e -> DietModel.logSelectedFood(HomeUI.getFoodsBox().getSelectionModel().getSelectedItem()));

        }
    }
}
