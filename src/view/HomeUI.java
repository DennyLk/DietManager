package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeUI implements UI {
    private Stage stage;
    private Scene scene;
    Button addButton;
    TextField foodInput;
    Button addRecipeButton;
    TextField basicFoodName, basicFoodCalories, basicFoodProteins, basicFoodCarbs, basicFoodFats;
    TextField recipeInput;
    TextField recipeNameInput;
    Button log;
    static DatePicker logCertainDate;
    static TextField certainDateField;
    Button weight;
    Button add;

    Button setRecipe;
    Button info;
    Button dailyButton;
    TextField dailyLogCorrectForm;
    DatePicker datePicker;
    static TextArea dailyLog;

    private static ComboBox<String> foodsBox = new ComboBox<>();
    private static ComboBox<String> recipeBox = new ComboBox<>();
    private static ComboBox<Integer> quantityBox = new ComboBox<>();

    public HomeUI(Stage stage) {
        this.stage = stage;
    }

    public static TextField getCertainDateField() {
        return certainDateField;
    }

    public Button getWeight() {
        return weight;
    }

    public Button getAdd() {
        return add;
    }

    public static DatePicker getLogCertainDate() {
        return logCertainDate;
    }

    public static TextArea getDailyLog() {
        return dailyLog;
    }

    public TextField getDailyLogCorrectForm() {
        return dailyLogCorrectForm;
    }

    public Button getDailyButton() {
        return dailyButton;
    }

    public Button getAddBasicFoodButton() {
        return addButton;
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

    public Button getInfo() {
        return info;
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
        root.setPadding(new Insets(20, 20, 20, 20));

        info = new Button("Check your daily intake");

        weight = new Button("Check your weight");
        add = new Button("Add Foods | Log Weight");

        HBox otherUi = new HBox();
        otherUi.setSpacing(10);
        otherUi.getChildren().addAll(info, weight, add);

        Label foodlb = new Label("Foods:");
        HBox hBox1 = new HBox();
        foodsBox.setEditable(false);
        logCertainDate = new DatePicker();

        log = new Button("Log");

        hBox1.getChildren().addAll(foodsBox, logCertainDate, log);
        hBox1.setSpacing(10);
        hBox1.setPadding(new Insets(0, 0, 10, 0));

        certainDateField = new TextField();

        handleDatePicker(logCertainDate, certainDateField);

        Label foodName = new Label("Enter Food Name");
        basicFoodName = new TextField();
        basicFoodName.setMaxWidth(400);

        Label foodCalories = new Label("Enter Food Calories");
        basicFoodCalories = new TextField();
        basicFoodCalories.setMaxWidth(400);

        Label foodProteins = new Label("Enter Food Proteins");
        basicFoodProteins = new TextField();
        basicFoodProteins.setMaxWidth(400);

        Label foodCarbs = new Label("Enter Food Carbs");
        basicFoodCarbs = new TextField();
        basicFoodCarbs.setMaxWidth(400);

        Label foodFats = new Label("Enter Food Fats");
        basicFoodFats = new TextField();
        basicFoodFats.setMaxWidth(400);

        addButton = new Button("Add Food");
        VBox inputLayout = new VBox();
        inputLayout.getChildren().addAll(foodName, basicFoodName, foodCalories, basicFoodCalories, foodProteins,
                basicFoodProteins, foodCarbs, basicFoodCarbs, foodFats, basicFoodFats, addButton);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.setSpacing(5);

        HBox recipeLabels = new HBox();
        recipeLabels.setPadding(new Insets(20, 0, 0, 0));
        recipeLabels.setSpacing(300);
        Label lblName = new Label("Add recipe name:");
        lblName.setPadding(new Insets(0, 20, 0, 0));
        Label recipeBoxlbl = new Label("Select Food");
        recipeBoxlbl.setPadding(new Insets(0, 390, 0, 0));
        Label quantityBoxlbl = new Label("Select Quantity");
        recipeLabels.getChildren().addAll(lblName, recipeBoxlbl, quantityBoxlbl);

        HBox recipeBoxes = new HBox();
        recipeNameInput = new TextField();
        recipeNameInput.setMaxWidth(600);
        quantityBox.setMinWidth(100);
        recipeBoxes.setSpacing(250);
        recipeBoxes.getChildren().addAll(recipeNameInput, recipeBox, quantityBox);

        setRecipe = new Button("Add Food To Recipe");

        HBox recipeButton = new HBox();
        setRecipe.setMinWidth(350);
        recipeButton.setAlignment(Pos.CENTER);
        recipeButton.setPadding(new Insets(10, 0, 0, 0));
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

        Label recipeLabel = new Label("Recipe:");
        recipeInput = new TextField();
        recipeInput.setEditable(false);

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

        Label dailyLogLabel = new Label("Daily Log:");
        HBox dailyLogLayout = new HBox();
        dailyLogLayout.setAlignment(Pos.CENTER);
        dailyLogLayout.getChildren().addAll(dailyLogLabel);

        VBox dailyCalories = new VBox();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 0, 20, 0));

        datePicker = new DatePicker();
        datePicker.setPromptText("Choose a Date");

        dailyLog = new TextArea();
        dailyButton = new Button("Create Daily Log");

        hBox.getChildren().addAll(datePicker, dailyButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        dailyCalories.getChildren().addAll(hBox, dailyLog);
        dailyCalories.setAlignment(Pos.CENTER);
        dailyLogCorrectForm = new TextField();

        handleDatePicker(datePicker, dailyLogCorrectForm);

        root.getChildren().addAll(otherUi, foodlb, hBox1, inputLayout, recipeLabels,
                recipeBoxes, recipeButton, recipeLabelLayout, recipeInputLayout, dailyLogLayout, dailyCalories);

        scene = new Scene(root, 1500, 900);
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

    public static <E> void addDailyLog(ArrayList<E> array, TextArea textArea, Double totalCalories) {
        textArea.clear();
        if (array.size() != 0) {
            for (int i = 0; i < array.size(); i++) {
                textArea.appendText(array.get(i).toString() + "\n");
            }
            textArea.appendText("-------------------------------------- \n");
            textArea.appendText("Total calories for chosen date: " + totalCalories);
        } else {
            textArea.appendText("No Inputs for selected Date");
        }
    }

    public static void handleDatePicker(DatePicker datePicker, TextField textField) {
        datePicker.setOnAction(e -> {
            if (datePicker.getValue() != null) {
                LocalDate selectedDate = datePicker.getValue();
                String format = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                textField.setText(format);
            }
        });
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
