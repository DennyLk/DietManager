package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Recipe extends Food {
    private ArrayList<Food> allFoods = super.getFoodsList();

    private double calories;
    private double proteins;
    private double carbs;
    private double fats;

    public Recipe(String name, String caloies, String proteins, String carbs, String fats) {
        super(name, caloies, proteins, carbs, fats);
    }

    public Recipe() {
        super();
    }

    public void getRecipe() {
        String path = "dietmanager801g2/assets/foods.csv";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals("r")) {
                    for (int i = 0; i < data.length; i++) {
                        super.setName(data[1]);

                        if (i > 1 && (i % 2 == 0)) {
                            calculateRecipeInfo(data[i], Double.parseDouble(data[i + 1]));
                        }
                    }
                    super.getFoodsList().add(new Recipe(super.getName(), String.valueOf(calories),
                            String.valueOf(proteins), String.valueOf(carbs), String.valueOf(fats)));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void calculateRecipeInfo(String bascisFood, double amount) {
        for (int i = 0; i < allFoods.size(); i++) {
            if (bascisFood.equals(allFoods.get(i).getName())) {
                Food f = allFoods.get(i);
                this.calories += (Double.parseDouble(f.getCalories()) * amount);
                this.proteins += (Double.parseDouble(f.getProteins()) * amount);
                this.carbs += (Double.parseDouble(f.getCarbs()) * amount);
                this.fats += (Double.parseDouble(f.getFats()) * amount);
                return;
            }
        }
    }

    public static void addRecipe(String recipeName) {
        try (FileWriter fw = new FileWriter("src/model/assets/foods.csv", true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("r," + recipeName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
