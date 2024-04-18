package view;

import java.text.DecimalFormat;
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
    static TextField exerciseMinutes;

    Button log;
    Button logExercise;

    static DatePicker logCertainDate;
    static DatePicker logExerciseDate;

    static TextField certainDateField;
    static TextField exerciseDateField;

    Button weight;
    Button addFood;
    Button addRecipe;
    Button addExercise;
    Button addWeight;
    Button dailyCals;
    Button info;
    Button dailyButton;
    TextField dailyLogCorrectForm;
    DatePicker datePicker;
    Button deleteLogButton;

    static TextArea dailyLog;

    private static ComboBox<String> foodsBox = new ComboBox<>();
    private static ComboBox<String> exercisesBox = new ComboBox<>();
    private static ComboBox<String> recipeBox = new ComboBox<>();
    private static ComboBox<Integer> quantityBox = new ComboBox<>();
    private static ComboBox<String> logBox = new ComboBox<>();

    public HomeUI(Stage stage) {
        this.stage = stage;
    }

    public Button getDeleteLogButton() {
        return deleteLogButton;
    }

    public static ComboBox<String> getLogBox() {
        return logBox;
    }

    public static TextField getExerciseMinutes() {
        return exerciseMinutes;
    }

    public static TextField getExerciseDateField() {
        return exerciseDateField;
    }

    public static DatePicker getLogExerciseDate() {
        return logExerciseDate;
    }

    public Button getLogExercise() {
        return logExercise;
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

    public Button getDailyCals() {
        return dailyCals;
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

        info = new Button("Full Log History");

        weight = new Button("Full Weight History");

        addFood = new Button("Create Food");
        addRecipe = new Button("Create Recipe");
        addExercise = new Button("Create Exercise");
        addWeight = new Button("Log Weight");
        dailyCals = new Button("Add Calorie Goal");

        HBox otherUi = new HBox();
        otherUi.setSpacing(10);
        otherUi.setAlignment(Pos.CENTER);
        otherUi.getChildren().addAll(info, weight);

        HBox addUi = new HBox();
        addUi.setSpacing(10);
        addUi.setAlignment(Pos.CENTER);
        addUi.getChildren().addAll(addFood, addRecipe, addExercise, addWeight, dailyCals);

        Label foodlb = new Label("Foods:");
        HBox hBox1 = new HBox();
        foodsBox.setEditable(false);
        logCertainDate = new DatePicker();

        log = new Button("Log Food");

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
        logExerciseDate = new DatePicker();

        logExercise = new Button("Log Exercise");
        exerciseDateField = new TextField();

        handleDatePicker(logExerciseDate, exerciseDateField);

        hBox2.getChildren().addAll(exercisesBox, exerciseMinutes, logExerciseDate, logExercise);
        hBox2.setSpacing(10);
        hBox2.setPadding(new Insets(0, 0, 10, 0));

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

        HBox deleteLog = new HBox();
        Label deleteLabel = new Label("Delete a log:");
        deleteLogButton = new Button("Delete log");
        deleteLog.setSpacing(10);
        deleteLog.setAlignment(Pos.CENTER);
        deleteLog.getChildren().addAll(deleteLabel, logBox, deleteLogButton);

        hBox.getChildren().addAll(datePicker, dailyButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        dailyCalories.getChildren().addAll(hBox, dailyLog);
        dailyCalories.setAlignment(Pos.CENTER);
        dailyLogCorrectForm = new TextField();

        handleDatePicker(datePicker, dailyLogCorrectForm);

        root.getChildren().addAll(otherUi, addUi, foodlb, hBox1, exerciselb, hBox2, dailyLogLayout, dailyCalories, deleteLog);

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

    public static <E> void setExercises(ArrayList<E> list, ComboBox<String> box){
        ObservableList<String> exercises = FXCollections.observableArrayList();
        for (E exe : list) {
            exercises.addAll(exe.toString());
        }
        box.setItems(exercises);
    }

    public static <E> void setLogs(ArrayList<E> list, ComboBox<String> box){
        ObservableList<String> logs = FXCollections.observableArrayList();
        for (E log : list) {
            logs.addAll(log.toString());
        }
        box.setItems(logs);
    }

    public static <E> void addDailyLog(ArrayList<E> array, TextArea textArea, Double consumed, Double burned, Double weight, Double goal, Double net, Double margin) {
        textArea.clear();
        DecimalFormat dFormat = new DecimalFormat("#.##");
        if (array.size() != 0) {
            for (int i = 0; i < array.size(); i++) {
                textArea.appendText(array.get(i).toString() + "\n");
            }
            textArea.appendText("-------------------------------------- \n");
            textArea.appendText("Calorie goal: " + dFormat.format(goal) + "\n");
            textArea.appendText("Total calories consumed: " + dFormat.format(consumed) + "\n");
            textArea.appendText("Total calories burned: " + dFormat.format(burned) + "\n");
            textArea.appendText("Your weight: " + dFormat.format(weight) + "\n");
            textArea.appendText("-------------------------------------- \n");
            textArea.appendText("Net calories for chosen date: " + dFormat.format(net) + "\n");
            textArea.appendText(dFormat.format(margin) + " calories for reaching calorie goal for chosen date\n");
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
