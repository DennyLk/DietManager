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

    public void run(Stage primaryStage, String uiType) {
        UI ui = UIFactory.createUI(uiType, primaryStage);
        ui.display();
        model.getAllFoods();
        model.getAllLogs();
        WeightUI.setWeight(model.getwLog());

        if (ui instanceof HomeUI) {
            HomeUI.setFoods(model.getFoods(), HomeUI.getFoodsBox());
            HomeUI.setFoods(model.getFoods(), HomeUI.getRecipeBox());
            HomeUI.setQuantity(10, HomeUI.getQuantityBox());
            ((HomeUI) ui).getAddButton().setOnAction(e -> DietModel.addFood(((HomeUI) ui).getFoodInput().getText()));
            ((HomeUI) ui).getAddRecipeButton()
                    .setOnAction(e -> {
                        DietModel.addRecipe(((HomeUI) ui).getRecipeInput().getText());
                        HomeUI.setFoods(model.getFoods(), HomeUI.getFoodsBox());
                        HomeUI.setFoods(model.getFoods(), HomeUI.getRecipeBox());
                    });
            ((HomeUI) ui).getDailyButton()
                    .setOnAction(e -> {
                        DietModel.addDailyLog(((HomeUI) ui).getDailyLogCorrectForm().getText());
                        HomeUI.addDailyLog(model.getDailyLog(), HomeUI.getDailyLog(), model.getDailyCalories());
                    });
                        
            ((HomeUI) ui).getAddBasicFoodButton()
                    .setOnAction(e -> {
                        DietModel.addFood(((HomeUI) ui).addBasicFood());
                        HomeUI.setFoods(model.getFoods(), HomeUI.getFoodsBox());
                        HomeUI.setFoods(model.getFoods(), HomeUI.getRecipeBox());
                    });
            ((HomeUI) ui).getLog().setOnAction(
                    e -> DietModel.logSelectedFood(HomeUI.getFoodsBox().getSelectionModel().getSelectedItem()));

            ((HomeUI) ui).getInfo().setOnAction(e -> run(primaryStage, "Info"));
        } else if (ui instanceof InfoUI) {
            Log.getData();
            InfoUI.setLog(model.getfLog());

            ((InfoUI) ui).getBack().setOnAction(e -> run(primaryStage, "Home"));
        }
    }
}
