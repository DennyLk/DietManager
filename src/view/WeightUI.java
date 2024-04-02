package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Log;

public class WeightUI implements UI {
    private Stage stage;
    private Scene scene;
    Button backBtn;

    private static TextArea weightBox = new TextArea();

    public WeightUI(Stage stage) {
        this.stage = stage;
    }

    public Button getBackBtn() {
        return backBtn;
    }

    @Override
    public void display() {
        stage.setTitle("Weight");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15, 20, 15, 20));

        Label titleLbl = new Label("Weight Log");
        titleLbl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label weightLbl = new Label("Weight History:");
        weightBox.setEditable(false);

        backBtn = new Button("Back");
        backBtn.setOnAction(e -> backBtn());

        // Adding a label above the back button for additional information or spacing
        Label spacerLabel = new Label(); // Acts as a spacer
        spacerLabel.setPrefHeight(20);

        root.getChildren().addAll(titleLbl, weightLbl, weightBox, spacerLabel, backBtn);

        scene = new Scene(root, 750, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void setWeight(ArrayList<Log> list) {
        if (list.size() == 0) {
            weightBox.setText("No Weight Logged");
        } else {
            for (Log log : list) {
                weightBox.appendText(log.toString() + "\n");
            }
        }
    }

    public void backBtn() {
        UI homeUI = UIFactory.createUI("Home", stage);
        homeUI.display();
    }
}
