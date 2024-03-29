package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Recipe extends Food {

    public Recipe(String name, Double caloies, Double proteins, Double carbs, Double fats) {
        super(name, caloies, proteins, carbs, fats);
    }

    public Recipe() {
        super();
    }


    public void calculateRecipeInfo(String bascisFood, double amount, ArrayList<Food> allFoods) {
        for (int i = 0; i < allFoods.size(); i++) {
            if (bascisFood.equals(allFoods.get(i).getName())) {
                Food f = allFoods.get(i);
                super.calories += (f.getCalories() * amount);
                super.proteins += (f.getProteins() * amount);
                super.carbs += (f.getCarbs() * amount);
                super.fats += (f.getFats() * amount);
                return;
            }
        }
    }

    public static void addRecipe(String recipeName) {
        try (FileWriter fw = new FileWriter("./assets/foods.csv", true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("r," + recipeName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toCsv() {
        String text = String.format("r, %s: %s calories, %.2f proteins, %.2f carbs, %.2f fats",
                name, calories,
                proteins, carbs,
                fats);
        return text;
    }

}
