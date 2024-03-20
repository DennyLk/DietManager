package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IntakeUI implements UI{
    private Stage stage;
    private Scene scene;

    public IntakeUI(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void display() {
        stage.setTitle("Intake");

        VBox root = new VBox(8);

        Button back = new Button("Back");
        back.setOnAction(e -> backBtn());

        root.getChildren().addAll(back);
        
        scene = new Scene(root, 750, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void backBtn() {
        UI homeUI = UIFactory.createUI("Home", stage);
        homeUI.display();
    }
}
