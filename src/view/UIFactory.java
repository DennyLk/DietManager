package view;

public class UIFactory {

    public static UI createUI(String uiType) {
        switch (uiType) {
            case "Home":
                return new HomeUI();
            case "Info":
                return new InfoUI();
            case "Intake":
                return new IntakeUI();
            case "Weight":
                return new WeightUI();
            default:
                throw new IllegalArgumentException("Unknown UI type: " + uiType);
        }
    }
}

