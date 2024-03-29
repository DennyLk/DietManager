package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Food;

public class HomeUI implements UI {
    private Stage stage;
    private Scene scene;
    Button addButton;
    TextField foodInput;
    Button addRecipeButton;
    TextField recipeInput;
    TextField recepieNameInput;
    Button log;
    Button setRecipe;

    private static ComboBox<String> foodsBox = new ComboBox<>();
    private static ComboBox<String> recipeBox = new ComboBox<>();
    private static ComboBox<Integer> quantityBox = new ComboBox<>();

    public HomeUI(Stage stage) {
        this.stage = stage;
    }

    public Button getLog() {
        return log;
    }

    public Button getAddRecipeButton() {
        return addRecipeButton;
    }

    public TextField getRecipeInput() {
        return recipeInput;
    }

    public TextField getFoodInput() {
        return foodInput;
    }

    public Button getAddButton() {
        return addButton;
    }

    public static ComboBox<String> getFoodsBox() {
        return foodsBox;
    }

    public static ComboBox<String> getRecipeBox() {
        return recipeBox;
    }

    public static void setRecipeBox(ComboBox<String> recipeBox) {
        HomeUI.recipeBox = recipeBox;
    }

    public static ComboBox<Integer> getQuantityBox() {
        return quantityBox;
    }

    public static void setQuantityBox(ComboBox<Integer> quantityBox) {
        HomeUI.quantityBox = quantityBox;
    }

    @Override
    public void display() {
        stage.setTitle("Home");

        VBox root = new VBox(8);

        Button info = new Button("Check your daily intake");
        info.setOnAction(e -> openInfoUI());

        Button weight = new Button("Check your weight");
        weight.setOnAction(e -> openWeightUI());

        Label foodlb = new Label("Foods:");
        foodsBox.setEditable(false);

        log = new Button("Log");

        foodInput = new TextField();
        addButton = new Button("Add Food");
        VBox inputLayout = new VBox();
        inputLayout.getChildren().addAll(foodInput, addButton);

        setRecipe = new Button("Add Food To Recipe");

        setRecipe.setOnAction(e -> {
            String selected = recipeBox.getValue();
            int quantity = quantityBox.getValue();
            String name = recepieNameInput.getText().trim();

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

        Label recipeLabel = new Label("Recipe:");
        recipeInput = new TextField();
        recipeInput.setEditable(false);

        Label lblName = new Label("Add recipe name");
        recepieNameInput = new TextField();

        addRecipeButton = new Button("Add Recipe");

        VBox recipeInputLayout = new VBox();
        recipeInputLayout.getChildren().addAll(recipeLabel, recipeInput, addRecipeButton);

        root.getChildren().addAll(info, weight, foodlb, foodsBox, log, inputLayout, lblName, recepieNameInput,
                recipeBox, quantityBox, setRecipe, recipeInputLayout);

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

    public static void setFoods(ArrayList<Food> list, ComboBox<String> box) {
        ObservableList<String> foodNames = FXCollections.observableArrayList();
        for (Food food : list) {
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

}
