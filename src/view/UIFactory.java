package view;

import javafx.stage.Stage;

public class UIFactory {

    public static UI createUI(String uiType, Stage stage) {
        switch (uiType) {
            case "Home":
                return new HomeUI(stage);
            case "Info":
                return new InfoUI(stage);
            case "Intake":
                return new IntakeUI(stage);
            case "Weight":
                return new WeightUI(stage);
            default:
                throw new IllegalArgumentException("Unknown UI type: " + uiType);
        }
    }
}

