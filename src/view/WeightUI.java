package view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WeightUI implements UI{
    private Stage stage;
    private Scene scene;

    public WeightUI(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void display() {
        stage.setTitle("Weight");

        VBox root = new VBox(8);

        scene = new Scene(root, 750, 600);
        stage.setScene(scene);
        stage.show();
    }}
