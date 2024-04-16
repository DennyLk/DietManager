package controller;

import java.time.LocalDate;

import javafx.stage.Stage;
import model.DietModel;
import model.Log;
import view.AddUI;
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
            HomeUI.setExercises(model.getExLog(), HomeUI.getExercisesBox());
            DietModel.updateLog();
            DietModel.addDailyLog(LocalDate.now().toString());
            HomeUI.setLogs(model.loadAllLogs(), HomeUI.getLogBox());
            HomeUI.addDailyLog(model.getDailyLog(), HomeUI.getDailyLog(), model.getDailyCalories(), model.getDailyBurned(), model.getDailyWeight(), model.getDailyGoal(), model.getDailyNet(), model.getDailyMargin());
            ((HomeUI) ui).getDeleteLogButton()
                    .setOnAction(e -> {
                        model.deleteLog(HomeUI.getLogBox().getSelectionModel().getSelectedItem());

                        DietModel.updateLog();
                        DietModel.addDailyLog(((HomeUI) ui).getDailyLogCorrectForm().getText());
                        HomeUI.addDailyLog(model.getDailyLog(), HomeUI.getDailyLog(), model.getDailyCalories(), model.getDailyBurned(), model.getDailyWeight(), model.getDailyGoal(), model.getDailyNet(), model.getDailyMargin());
                        HomeUI.setLogs(model.loadAllLogs(), HomeUI.getLogBox());
                    });
            ((HomeUI) ui).getDailyButton()
                    .setOnAction(e -> {
                        DietModel.updateLog();
                        DietModel.addDailyLog(((HomeUI) ui).getDailyLogCorrectForm().getText());
                        HomeUI.addDailyLog(model.getDailyLog(), HomeUI.getDailyLog(), model.getDailyCalories(), model.getDailyBurned(), model.getDailyWeight(), model.getDailyGoal(), model.getDailyNet(), model.getDailyMargin());
                        HomeUI.setLogs(model.loadAllLogs(), HomeUI.getLogBox());
                    });
            ((HomeUI) ui).getLogExercise()
                    .setOnAction(e -> {
                        if(HomeUI.getLogExerciseDate().getValue() == null){
                            DietModel.logSelectedExercise(HomeUI.getExercisesBox().getSelectionModel().getSelectedItem(), Double.parseDouble(HomeUI.getExerciseMinutes().getText()));
                        }
                        else{
                            DietModel.logSelectedExercise(HomeUI.getExercisesBox().getSelectionModel().getSelectedItem(), Double.parseDouble(HomeUI.getExerciseMinutes().getText()), HomeUI.getExerciseDateField().getText());
                        }
                        DietModel.updateLog();
                        DietModel.addDailyLog(((HomeUI) ui).getDailyLogCorrectForm().getText());
                        HomeUI.addDailyLog(model.getDailyLog(), HomeUI.getDailyLog(), model.getDailyCalories(), model.getDailyBurned(), model.getDailyWeight(), model.getDailyGoal(), model.getDailyNet(), model.getDailyMargin());
                        HomeUI.setLogs(model.loadAllLogs(), HomeUI.getLogBox());
                    });  
            ((HomeUI) ui).getLog().setOnAction(
                    e -> {
                        if (HomeUI.getLogCertainDate().getValue() == null) {
                            DietModel.logSelectedFood(HomeUI.getFoodsBox().getSelectionModel().getSelectedItem());
                        } else {
                            DietModel.logSelectedFood(HomeUI.getFoodsBox().getSelectionModel().getSelectedItem(),
                                    HomeUI.getCertainDateField().getText());
                        }

                        DietModel.updateLog();
                        DietModel.addDailyLog(((HomeUI) ui).getDailyLogCorrectForm().getText());
                        HomeUI.addDailyLog(model.getDailyLog(), HomeUI.getDailyLog(), model.getDailyCalories(), model.getDailyBurned(), model.getDailyWeight(), model.getDailyGoal(), model.getDailyNet(), model.getDailyMargin());
                    });

            ((HomeUI) ui).getInfo().setOnAction(e -> run(primaryStage, "Info"));
            ((HomeUI) ui).getWeight().setOnAction(e -> run(primaryStage, "Weight"));
            ((HomeUI) ui).getAddFood().setOnAction(e -> run(primaryStage, "Add"));
            ((HomeUI) ui).getAddRecipe().setOnAction(e -> run(primaryStage, "Add"));
            ((HomeUI) ui).getAddExercise().setOnAction(e -> run(primaryStage, "Add"));
            ((HomeUI) ui).getAddWeight().setOnAction(e -> run(primaryStage, "Add"));
        } else if (ui instanceof InfoUI) {
            Log.getData();
            InfoUI.setLog(model.getfLog());

            ((InfoUI) ui).getBack().setOnAction(e -> run(primaryStage, "Home"));
        } else if (ui instanceof WeightUI) {
            Log.getData();
            WeightUI.setWeight(model.getwLog());
            ((WeightUI) ui).getBackBtn().setOnAction(e -> run(primaryStage, "Home"));
        } else if (ui instanceof AddUI) {
            AddUI.setFoods(model.getFoods(), AddUI.getRecipeBox());
            AddUI.setQuantity(10, AddUI.getQuantityBox());

            ((AddUI) ui).getBackBtn().setOnAction(e -> run(primaryStage, "Home"));
            ((AddUI) ui).getLogCalorieGoal().setOnAction(e -> {
                if (AddUI.getCalorieGoalDate().getValue() != null) {
                    DietModel.logCalorieGoal(AddUI.getCalorieGoal().getText(),
                            AddUI.getCalorieGoalDate().getValue().toString());
                } else {
                    DietModel.logCalorieGoal(AddUI.getCalorieGoal().getText());
                }
            });
            ((AddUI) ui).getAddExercise().setOnAction(e -> DietModel.addExercise(((AddUI) ui).getExerciesNameField().getText(),Double.parseDouble(((AddUI) ui).getCaloriesPerHourField().getText())));
            ((AddUI) ui).getLogWeight().setOnAction(e -> {
                if (AddUI.getWeightDate().getValue() != null) {
                    DietModel.logSelectedWeight(AddUI.getWeight().getText(),
                            AddUI.getWeightDate().getValue().toString());
                } else {
                    DietModel.logSelectedWeight(AddUI.getWeight().getText());
                }
            });

            ((AddUI) ui).getAddBasicFoodButton()
                    .setOnAction(e -> {
                        DietModel.addFood(((AddUI) ui).addBasicFood());
                        AddUI.setFoods(model.getFoods(), AddUI.getRecipeBox());
                    });

            ((AddUI) ui).getAddRecipeButton()
                    .setOnAction(e -> {
                        DietModel.addRecipe(AddUI.getRecipeInput().getText());
                        AddUI.getRecipeInput().setText("");
                        AddUI.setFoods(model.getFoods(), AddUI.getRecipeBox());
                    });
        }
    }
}
