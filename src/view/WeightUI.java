package view;

import java.util.ArrayList;

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
        private static TextArea weightBox = new TextArea();


    public WeightUI(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void display() {
        stage.setTitle("Weight");

        VBox root = new VBox(8);

        Label weightlb = new Label("Weight Log:");
        weightBox.setEditable(false);

        Button info = new Button("Back");
        info.setOnAction(e -> backBtn());

        root.getChildren().addAll(weightlb, weightBox, info);

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
