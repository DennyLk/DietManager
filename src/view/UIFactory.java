package view;

import javafx.stage.Stage;

public class UIFactory {

    public static UI createUI(String uiType, Stage stage) {
        switch (uiType) {
            case "Home":
                return new HomeUI(stage);
            case "Info":
                return new InfoUI(stage);
            case "Weight":
                return new WeightUI(stage);
            case "Add":
                return new AddUI(stage);
            default:
                throw new IllegalArgumentException("Unknown UI type: " + uiType);
        }
    }
}
