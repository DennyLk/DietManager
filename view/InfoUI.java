package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InfoUI extends Application{
    private Stage stage;
    private Scene scene;

    public void start(Stage stage) {
        stage.setTitle("Info");

        VBox root = new VBox(8);

        scene = new Scene(root, 750, 600);
        stage.setScene(scene);
        stage.show();
    }
}
