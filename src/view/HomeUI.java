package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Food;

public class HomeUI implements UI {
    private Stage stage;
    private Scene scene;
    Button addButton;
    TextField foodInput;
    Button addRecipeButton;
    TextField basicFoodName, basicFoodCalories, basicFoodProteins, basicFoodCarbs, basicFoodFats;
    TextField recipeInput;
    TextField recepieNameInput;
    Button log;
    static DatePicker logCertainDate;
    static TextField certainDateField;

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

    public Button getAddBasicFoodButton(){
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

        info = new Button("Check your daily intake");


        Button weight = new Button("Check your weight");
        weight.setOnAction(e -> openWeightUI());

        Label foodlb = new Label("Foods:");
        HBox hBox1 = new HBox();
        foodsBox.setEditable(false);
        logCertainDate = new DatePicker();
        hBox1.getChildren().addAll(foodsBox, logCertainDate);
        hBox1.setSpacing(10);

        certainDateField = new TextField();
        log = new Button("Log");

        handleDatePicker(logCertainDate, certainDateField);

        // foodInput = new TextField();

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
        inputLayout.getChildren().addAll(foodName, basicFoodName, foodCalories, basicFoodCalories, foodProteins, basicFoodProteins, foodCarbs, basicFoodCarbs, foodFats, basicFoodFats, addButton);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.setSpacing(5);

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

        VBox dailyCalories = new VBox();
        HBox hBox = new HBox();

        datePicker = new DatePicker();
        dailyLog = new TextArea();
        dailyButton = new Button("Create Daily Log");

        hBox.getChildren().addAll(datePicker, dailyButton);
        hBox.setAlignment(Pos.CENTER);
        dailyCalories.getChildren().addAll(hBox, dailyLog);
        dailyCalories.setAlignment(Pos.CENTER);
        dailyLogCorrectForm = new TextField();

        handleDatePicker(datePicker, dailyLogCorrectForm);

        // datePicker.setOnAction(e -> {
        //     if(datePicker.getValue() != null){
        //         LocalDate selectedDate = datePicker.getValue();
        //         String format = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //         dailyLogCorrectForm.setText(format);
        //     } 
        // });

        root.getChildren().addAll(info, weight, foodlb, hBox1, log, inputLayout, lblName, recepieNameInput,
                recipeBox, quantityBox, setRecipe, recipeInputLayout, dailyCalories);

        scene = new Scene(root, 800, 1500);
        stage.setScene(scene);
        stage.show();
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

    public static <E> void addDailyLog(ArrayList<E> array, TextArea textArea, Double totalCalories){
        textArea.clear();
        if(array.size() != 0){
            for (int i = 0; i < array.size(); i++) {
                textArea.appendText(array.get(i).toString() + "\n");
            }
            textArea.appendText("-------------------------------------- \n");
            textArea.appendText("Total calories for chosen date: " + totalCalories);
        }
        else{
            textArea.appendText("No Inputs for selected Date");
        }   
    }

    public static void handleDatePicker(DatePicker datePicker, TextField textField){
        datePicker.setOnAction(e -> {
            if(datePicker.getValue() != null){
                LocalDate selectedDate = datePicker.getValue();
                String format = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                textField.setText(format);
            } 
        });
    }

    public String addBasicFood(){
        String name = basicFoodName.getText();
        double calories = Double.parseDouble(basicFoodCalories.getText());
        double proteins = Double.parseDouble(basicFoodProteins.getText());
        double carbs = Double.parseDouble(basicFoodCarbs.getText());
        double fats = Double.parseDouble(basicFoodFats.getText());

        return String.format("%s,%f,%f,%f,%f", name, calories, proteins, carbs, fats);
    }

}
