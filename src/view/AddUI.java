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

public class AddUI implements UI {
    private Stage stage;
    private Scene scene;
    Button backBtn;

    public AddUI(Stage stage) {
        this.stage = stage;
    }

    public Button getBackBtn() {
        return backBtn;
    }

    @Override
    public void display() {
        stage.setTitle("Add");

        VBox root = new VBox(10);

        backBtn = new Button("Back");

        root.getChildren().addAll(backBtn);

        scene = new Scene(root, 750, 600);
        stage.setScene(scene);
        stage.show();
    }
}
