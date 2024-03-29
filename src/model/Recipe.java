package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Recipe extends Food {
    String recipeParts;

    public Recipe(String name, Double caloies, Double proteins, Double carbs, Double fats) {
        super(name, caloies, proteins, carbs, fats);
    }

    public Recipe() {
        super();
    }

    public Recipe(String name, String recipeParts) {
        setName(name);
        this.recipeParts = recipeParts;
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

    public void addRecipe() {
        try (FileWriter fw = new FileWriter("./assets/foods.csv", true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCsv());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toCsv() {
        String text = "r," + this.name + "," + this.recipeParts +"\n";
        return text;
    }

}
