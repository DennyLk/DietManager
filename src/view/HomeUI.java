package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DietModel;
import model.Food;

public class HomeUI implements UI {
    private Stage stage;
    private Scene scene;
    private static ComboBox<String> foodsBox = new ComboBox<>();
    private static ComboBox<String> recipeBox = new ComboBox<>();

    public HomeUI(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void display() {
        stage.setTitle("Home");

        VBox root = new VBox(8);

        Button info = new Button("Check your daily intake");
        info.setOnAction(e -> openInfoUI());

        Button weight = new Button("Check your weight");
        weight.setOnAction(e -> openWeightUI());

        Label foodlb = new Label("Basic Foods:");
        foodsBox.setEditable(false);

        Label recipeLb = new Label("Recipe Foods:");
        recipeBox.setEditable(false);

        Button intake = new Button("Intake Food");
        intake.setOnAction(e -> openIntakeUI());

        Button log = new Button("Log");
        log.setOnAction(e -> logSelectedFood());

        root.getChildren().addAll(info, weight, foodlb, foodsBox, recipeLb, recipeBox, intake, log);

        scene = new Scene(root, 750, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void openInfoUI() {
        UI infoUI = UIFactory.createUI("Info", stage);
        infoUI.display();
    }

    public void openWeightUI() {
        UI infoUI = UIFactory.createUI("Weight", stage);
        infoUI.display();
    }

    public static void setFoods(ArrayList<Food> list) {
        ObservableList<String> foodNames = FXCollections.observableArrayList();
        for (Food food : list) {
            foodNames.addAll(food.toString());
        }
        foodsBox.setItems(foodNames);
    }

    public static void setRecipes(ArrayList<Food> list) {
        ObservableList<String> recipeNames = FXCollections.observableArrayList();
        for (Food food : list) {
            recipeNames.addAll(food.toString());
        }
        recipeBox.setItems(recipeNames);
    }

    public void openIntakeUI() {
        UI intakeUI = UIFactory.createUI("Intake", stage);
        intakeUI.display();
    }

    private void logSelectedFood() {
        String selectedFood = foodsBox.getSelectionModel().getSelectedItem();
        String selectedRecipe = recipeBox.getSelectionModel().getSelectedItem();
        String itemToLog = (selectedFood != null) ? selectedFood : selectedRecipe;

        if (itemToLog != null && !itemToLog.trim().isEmpty()) {
            DietModel.logSelectedFood(itemToLog);
        }
    }

}
