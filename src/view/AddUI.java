package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddUI implements UI {
    private Stage stage;
    private Scene scene;
    Button backBtn;
    static TextField calorieGoal;
    static DatePicker calorieGoalDate;
    Button logCalorieGoal;

    static TextField weight;
    static DatePicker weightDate;
    Button logWeight;

    Button addExercise;
    TextField exerciesNameField;
    TextField caloriesPerHourField;
    Label exerciseLabel;

    TextField basicFoodName, basicFoodCalories, basicFoodProteins, basicFoodCarbs, basicFoodFats;
    Button addButton;

    Button addRecipeButton;
    static TextField recipeInput;
    TextField recipeNameInput;

    Button setRecipe;

    private static ComboBox<String> recipeBox = new ComboBox<>();
    private static ComboBox<Integer> quantityBox = new ComboBox<>();

    public AddUI(Stage stage) {
        this.stage = stage;
    }

    public Button getAddExercise() {
        return addExercise;
    }

    public TextField getExerciesNameField() {
        return exerciesNameField;
    }

    public TextField getCaloriesPerHourField() {
        return caloriesPerHourField;
    }

    public static TextField getWeight() {
        return weight;
    }

    public static DatePicker getWeightDate() {
        return weightDate;
    }

    public Button getLogWeight() {
        return logWeight;
    }

    public static TextField getCalorieGoal() {
        return calorieGoal;
    }

    public static DatePicker getCalorieGoalDate() {
        return calorieGoalDate;
    }

    public Button getLogCalorieGoal() {
        return logCalorieGoal;
    }

    public Button getBackBtn() {
        return backBtn;
    }

    public Button getAddBasicFoodButton() {
        return addButton;
    }

    public Button getAddRecipeButton() {
        return addRecipeButton;
    }

    public static TextField getRecipeInput() {
        return recipeInput;
    }

    public Button getAddButton() {
        return addButton;
    }

    public static ComboBox<String> getRecipeBox() {
        return recipeBox;
    }

    public static void setRecipeBox(ComboBox<String> recipeBox) {
        AddUI.recipeBox = recipeBox;
    }

    public static ComboBox<Integer> getQuantityBox() {
        return quantityBox;
    }

    public static void setQuantityBox(ComboBox<Integer> quantityBox) {
        AddUI.quantityBox = quantityBox;
    }

    @Override
    public void display() {
        stage.setTitle("Add");

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        HBox newHbox = new HBox();
        Label lbl = new Label("Add Daily Calorie Goal");
        calorieGoal = new TextField();
        calorieGoal.setPromptText("Calories");
        calorieGoal.setMaxWidth(300);
        calorieGoalDate = new DatePicker();
        logCalorieGoal = new Button("Add Daily Calorie Goal");
        newHbox.setSpacing(10);
        newHbox.setAlignment(Pos.CENTER);
        newHbox.getChildren().addAll(lbl, calorieGoal, calorieGoalDate, logCalorieGoal);

        HBox weightHBox = new HBox();
        Label weightLbl = new Label("Log your weight");
        weight = new TextField();
        weight.setPromptText("Weight");
        weight.setMaxWidth(300);
        weightDate = new DatePicker();
        logWeight = new Button("Log Weight");
        weightHBox.setSpacing(10);
        weightHBox.setAlignment(Pos.CENTER);
        weightHBox.getChildren().addAll(weightLbl, weight, weightDate, logWeight);

        HBox exeBox = new HBox();
        exeBox.setSpacing(10);
        exerciseLabel = new Label("Add Exercise");
        exerciesNameField = new TextField();
        exerciesNameField.setPromptText("Exercise Name");
        caloriesPerHourField = new TextField();
        caloriesPerHourField.setPromptText("Calories per Hour per kg");
        addExercise = new Button("Add Exercise");
        exeBox.setAlignment(Pos.CENTER);
        exeBox.getChildren().addAll(exerciseLabel, exerciesNameField, caloriesPerHourField, addExercise);

        Label foodName = new Label("Add Food");
        basicFoodName = new TextField();
        basicFoodName.setMaxWidth(200);
        basicFoodName.setPromptText("Food Name");

        basicFoodCalories = new TextField();
        basicFoodCalories.setMaxWidth(100);
        basicFoodCalories.setPromptText("Calories");

        basicFoodProteins = new TextField();
        basicFoodProteins.setMaxWidth(100);
        basicFoodProteins.setPromptText("Proteins");

        basicFoodCarbs = new TextField();
        basicFoodCarbs.setMaxWidth(100);
        basicFoodCarbs.setPromptText("Carbs");

        basicFoodFats = new TextField();
        basicFoodFats.setMaxWidth(100);
        basicFoodFats.setPromptText("Fats");

        addButton = new Button("Add Food");
        HBox inputLayout = new HBox();
        inputLayout.getChildren().addAll(foodName, basicFoodName, basicFoodCalories,
                basicFoodProteins, basicFoodCarbs, basicFoodFats, addButton);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.setSpacing(10);

        HBox recipeLabels = new HBox();
        Label lblName = new Label("Add recipe name:");
        lblName.setPadding(new Insets(0, 20, 0, 0));
        Label recipeBoxlbl = new Label("Select Food");
        recipeBoxlbl.setPadding(new Insets(0, 390, 0, 0));
        Label quantityBoxlbl = new Label("Select Quantity");
        recipeLabels.setSpacing(10);
        recipeLabels.setAlignment(Pos.CENTER);
        recipeLabels.getChildren().addAll(lblName, recipeBoxlbl, quantityBoxlbl);

        HBox recipeBoxes = new HBox();
        recipeNameInput = new TextField();
        recipeNameInput.setMaxWidth(300);
        quantityBox.setMinWidth(100);
        recipeBoxes.setSpacing(10);
        recipeBoxes.setAlignment(Pos.CENTER);
        recipeBoxes.getChildren().addAll(recipeNameInput, recipeBox, quantityBox);

        setRecipe = new Button("Add Food To Recipe");

        HBox recipeButton = new HBox();
        setRecipe.setMinWidth(350);
        recipeButton.setAlignment(Pos.CENTER);
        recipeButton.setPadding(new Insets(20, 0, 20, 0));
        recipeButton.getChildren().addAll(setRecipe);

        setRecipe.setOnAction(e -> {
            String selected = recipeBox.getValue();
            int quantity = quantityBox.getValue();
            String name = recipeNameInput.getText().trim();

            if (selected != null && quantity != 0) {
                String[] parts = selected.split(":");
                String recipeName = parts[0].trim();

                String current = recipeInput.getText();
                String[] data = current.split(",");

                StringBuilder currentRecipe = new StringBuilder();
                for (int i = 1; i < data.length; i++) {
                    currentRecipe.append(data[i] + ",");
                }

                if (currentRecipe.length() > 0) {
                    currentRecipe.deleteCharAt(currentRecipe.length() - 1);
                }

                String currentRecipeStr = currentRecipe.toString();
                if (!currentRecipeStr.isEmpty()) {
                    recipeInput.setText(name + "," + currentRecipeStr + "," + recipeName + "," + quantity);
                } else {
                    recipeInput.setText(name + "," + recipeName + "," + quantity);
                }
            }
        });


        VBox recipe = new VBox();
        Label recipeLabel = new Label("Recipe:");
        recipeInput = new TextField();
        recipeInput.setEditable(false);
        recipeInput.setMinWidth(300);

        HBox recipeLabelLayout = new HBox();
        recipeLabelLayout.setAlignment(Pos.CENTER);
        recipeLabelLayout.setPadding(new Insets(20, 0, 0, 0));
        recipeLabelLayout.getChildren().addAll(recipeLabel);

        HBox recipeInputLayout = new HBox();
        addRecipeButton = new Button("Add Recipe");
        recipeInput.setMaxWidth(600);
        recipeInputLayout.setSpacing(10);
        recipeInputLayout.setPadding(new Insets(0, 0, 20, 0));
        recipeInputLayout.getChildren().addAll(recipeInput, addRecipeButton);
        recipeInputLayout.setAlignment(Pos.CENTER);

        backBtn = new Button("Back");

        recipe.getChildren().addAll(recipeLabels,
        recipeBoxes, recipeButton, recipeLabelLayout, recipeInputLayout);

        root.getChildren().addAll(newHbox,
                weightHBox, exeBox, inputLayout, recipe,
                backBtn);

        root.setSpacing(50);

        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static <E> void setFoods(ArrayList<E> list, ComboBox<String> box) {
        ObservableList<String> foodNames = FXCollections.observableArrayList();
        for (E food : list) {
            foodNames.addAll(food.toString());
        }
        box.setItems(foodNames);
    }

    public static void setQuantity(int n, ComboBox<Integer> box) {
        ObservableList<Integer> number = FXCollections.observableArrayList();
        for (int i = 1; i <= n; i++) {
            number.addAll(i);
        }
        box.setItems(number);
    }

    public String addBasicFood() {
        String name = basicFoodName.getText();
        double calories = Double.parseDouble(basicFoodCalories.getText());
        double proteins = Double.parseDouble(basicFoodProteins.getText());
        double carbs = Double.parseDouble(basicFoodCarbs.getText());
        double fats = Double.parseDouble(basicFoodFats.getText());

        return String.format("%s,%f,%f,%f,%f", name, calories, proteins, carbs, fats);
    }
}