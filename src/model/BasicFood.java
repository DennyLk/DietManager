package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BasicFood extends Food {

    public BasicFood() {
    }

    public BasicFood(String name, Double caloies, Double proteins, Double carbs, Double fats) {
        super(name, caloies, proteins, carbs, fats);
    }

        public void addBasicFood() {
        try (FileWriter fw = new FileWriter("./assets/foods.csv", true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCsv());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toCsv() {
        String text = "b," + name + "," + calories + "," + proteins + "," + carbs + "," + fats + "\n";
        return text;
    }
    

    
}
