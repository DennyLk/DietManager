package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Food {

    protected String name;
    protected Double calories = 0.0;
    protected Double proteins = 0.0;
    protected Double carbs = 0.0;
    protected Double fats = 0.0;

    public Food() {
    }

    public Food(String name, Double calories, Double proteins, Double carbs, Double fats) {
        this.name = name;
        this.calories = calories;
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

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public static ArrayList<Food> loadFoods() {
        ArrayList<Food> allFoods = new ArrayList<>();
        String path = "./assets/foods.csv";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals("b")) {
                    allFoods.add(new BasicFood(data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5])));
                } else {
                    Recipe r = new Recipe();
                    r.setName(data[1]);
                    for (int i = 0; i < data.length; i++) {
                        if (i > 1 && (i % 2 == 0)) {
                            r.calculateRecipeInfo(data[i], Double.parseDouble(data[i + 1]), allFoods);
                        }
                    }
                    allFoods.add(r);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return allFoods;
    }

    public static void addFood(String foodName) {
        try (FileWriter fw = new FileWriter("./assets/foods.csv", true);
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
                proteins, carbs,
                fats);
        return text;
    }
}
