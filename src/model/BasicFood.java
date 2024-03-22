package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BasicFood extends Food {

    public BasicFood() {
    }

    public BasicFood(String name, String caloies, String proteins, String carbs, String fats) {
        super(name, caloies, proteins, carbs, fats);
    }


        public void getBasicFood() {
        String path = "src/model/assets/foods.csv";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals("b")) {
                    super.getFoodsList().add(new BasicFood(data[1], data[2], data[3], data[4], data[5]));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
}
