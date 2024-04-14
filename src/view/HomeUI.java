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
    Button addRecipeButton;
    TextField exerciseMinutes;
    Button log;
    static DatePicker logCertainDate;
    static TextField certainDateField;
    Button weight;
    Button addFood;
    Button addRecipe;
    Button addExercise;
    Button addWeight;

    Button info;
    Button dailyButton;
    TextField dailyLogCorrectForm;
    DatePicker datePicker;
    static TextArea dailyLog;

    private static ComboBox<String> foodsBox = new ComboBox<>();
    private static ComboBox<String> exercisesBox = new ComboBox<>();
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

    public Button getAddFood() {
        return addFood;
    }

    public Button getAddRecipe() {
        return addRecipe;
    }

    public Button getAddExercise() {
        return addExercise;
    }

    public Button getAddWeight() {
        return addWeight;
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

    public static ComboBox<String> getExercisesBox() {
        return exercisesBox;
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

        info = new Button("Log history");

        weight = new Button("Weight history");

        addFood = new Button("Add Food");
        addRecipe = new Button("Add Recipe");
        addExercise = new Button("Add Exercise");
        addWeight = new Button("Add Weight");

        HBox otherUi = new HBox();
        otherUi.setSpacing(10);
        otherUi.setAlignment(Pos.CENTER);
        otherUi.getChildren().addAll(info, weight);

        HBox addUi = new HBox();
        addUi.setSpacing(10);
        addUi.setAlignment(Pos.CENTER);
        addUi.getChildren().addAll(addFood, addRecipe, addExercise, addWeight);

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

        Label exerciselb = new Label("Exercises:");
        HBox hBox2 = new HBox();
        exercisesBox.setEditable(false);
        exerciseMinutes = new TextField();
        exerciseMinutes.setPromptText("Minutes");
        logCertainDate = new DatePicker();

        log = new Button("Log");

        hBox2.getChildren().addAll(exercisesBox, exerciseMinutes, logCertainDate, log);
        hBox2.setSpacing(10);
        hBox2.setPadding(new Insets(0, 0, 10, 0));

        certainDateField = new TextField();

        handleDatePicker(logCertainDate, certainDateField);

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

        root.getChildren().addAll(otherUi, addUi, foodlb, hBox1, exerciselb, hBox2, dailyLogLayout, dailyCalories);

        scene = new Scene(root, 1000,600);
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
}
