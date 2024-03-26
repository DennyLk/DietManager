package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Food {

    private static ArrayList<Food> foods = new ArrayList<>();

    private String name;
    private String calories;
    private String proteins;
    private String carbs;
    private String fats;

    public Food() {
    }

    public Food(String name, String caloies, String proteins, String carbs, String fats) {
        this.name = name;
        this.calories = caloies;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public static ArrayList<Food> getFoods() {
        BasicFood f = new BasicFood();
        f.getBasicFood();
        Recipe r = new Recipe();
        r.getRecipe();
        return foods;
    }

    public static ArrayList<Food> getFoodsList() {
        return foods;
    }

    public static void setFoods(ArrayList<Food> foods) {
        Food.foods = foods;
    }

    public static void addFood(String foodName) {
        try (FileWriter fw = new FileWriter("dietmanager801g2/assets/foods.csv", true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("b," + foodName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String text = String.format("%s: %s calories, %.2f proteins, %.2f carbs, %.2f fats",
                name, calories,
                Double.parseDouble(proteins), Double.parseDouble(carbs),
                Double.parseDouble(fats));
        return text;
    }
}
