package view;

import java.util.ArrayList;

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

    public InfoUI(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void display() {
        stage.setTitle("Info");

        VBox root = new VBox(8);

        Label loglb = new Label("Daily Log:");
        logBox.setEditable(false);

        Button info = new Button("Back");
        info.setOnAction(e -> backBtn());

        root.getChildren().addAll(loglb, logBox, info);

        scene = new Scene(root, 750, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void setLog(ArrayList<Log> list) {
        if (list.size() == 0) {
            logBox.setText("No Logs for today");
        } else {
            for (Log log : list) {
                logBox.appendText(log.toString() + "\n");
            }
        }
    }

    public void backBtn() {
        UI homeUI = UIFactory.createUI("Home", stage);
        homeUI.display();
    }
}
