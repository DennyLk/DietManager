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

public class InfoUI implements UI {
    private Stage stage;
    private Scene scene;
    private static TextArea logBox = new TextArea();
    Button back;

    public InfoUI(Stage stage) {
        this.stage = stage;
    }

    public Button getBack() {
        return this.back;
    }

    @Override
    public void display() {
        stage.setTitle("Info");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15, 20, 15, 20));

        Label titleLbl = new Label("Information Log");
        titleLbl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label loglb = new Label("Daily Log:");
        logBox.setEditable(false);

        back = new Button("Back");

        // Adding a label above the back button for additional information or spacing
        Label spacerLabel = new Label(); // Acts as a spacer
        spacerLabel.setPrefHeight(20);

        root.getChildren().addAll(titleLbl, loglb, logBox, spacerLabel, back);

        scene = new Scene(root, 750, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void setLog(ArrayList<Log> list) {
        logBox.clear();
        if (list.size() == 0) {
            logBox.setText("No Logs for today");
        } else {
            for (Log log : list) {
                logBox.appendText(log.toString() + "\n");
            }
        }
    }
}
