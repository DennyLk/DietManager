package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    public AddUI(Stage stage) {
        this.stage = stage;
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

    @Override
    public void display() {
        stage.setTitle("Add");

        VBox root = new VBox(10);
        Label lbl = new Label("Add Daily Calorie Goal");
        calorieGoal = new TextField();
        calorieGoal.setMaxWidth(200);
        calorieGoalDate = new DatePicker();
        logCalorieGoal = new Button("Add Daily Calorie Goal");

        Label weightLbl = new Label("Log your weight");
        weight = new TextField();
        weight.setMaxWidth(100);
        weightDate = new DatePicker();
        logWeight = new Button("Log Weight");

        backBtn = new Button("Back");

        root.getChildren().addAll(lbl, calorieGoal, calorieGoalDate, logCalorieGoal,
                weightLbl, weight, weightDate, logWeight,
                backBtn);

        scene = new Scene(root, 750, 600);
        stage.setScene(scene);
        stage.show();
    }
}