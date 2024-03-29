package model;

public class BasicFood extends Food {

    public BasicFood() {
    }

    public BasicFood(String name, Double caloies, Double proteins, Double carbs, Double fats) {
        super(name, caloies, proteins, carbs, fats);
    }

    public String toCsv() {
        String text = String.format("b, %s: %s calories, %.2f proteins, %.2f carbs, %.2f fats",
                name, calories,
                proteins, carbs,
                fats);
        return text;
    }
    

    
}
