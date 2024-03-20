package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Food implements Loader {

    private static ArrayList<Food> bFoods = new ArrayList<>();
    private static ArrayList<Food> rFoods = new ArrayList<>();

    private String name;
    private String calories;
    private String proteins;
    private String carbs;
    private String fats;

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

    public static ArrayList<Food> getbFoods() {
        return bFoods;
    }

    public static void setbFoods(ArrayList<Food> bFoods) {
        Food.bFoods = bFoods;
    }

    public static ArrayList<Food> getrFoods() {
        return rFoods;
    }

    public static void setrFoods(ArrayList<Food> rFoods) {
        Food.rFoods = rFoods;
    }

    public static void getData() {
        String path = "src/model/assets/foods.csv";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals("b")) {
                    bFoods.add(new Food(data[1], data[2], data[3], data[4], data[5]));
                } else if (data[0].equals("r")) {
                    rFoods.add(new Food(data[1], data[2], data[3], data[4], data[5]));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void setData(String food) {

    }

    @Override
    public String toString() {
        String text = String.format("%s: %s calories, %s proteins, %s carbs, %s fats", name, calories, proteins, carbs,
                fats);
        return text;
    }
}
