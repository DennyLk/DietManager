package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Exercise {
    private static ArrayList<Exercise> exercises = new ArrayList<>();

    private String name;
    private double caloriesPerHour;
    public static final String PATH = "./assets/exercise.csv";

    public Exercise(){

    }

    public Exercise(String name, Double caloriesPerHour){
        this.name = name;
        this.caloriesPerHour = caloriesPerHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaloriesPerHour() {
        return caloriesPerHour;
    }

    public void setCaloriesPerHour(double caloriesPerHour) {
        this.caloriesPerHour = caloriesPerHour;
    }


    public static ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public static void setExercises(ArrayList<Exercise> exercises) {
        Exercise.exercises = exercises;
    }

    public static ArrayList<Exercise> readExercises(){
        exercises.clear();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
            String line = "";
            while((line = reader.readLine()) != null){
                String data[] = line.split(",");
                if(data[0].equals("e")){
                    exercises.add(new Exercise(data[1], Double.parseDouble(data[2])));
                }
                else{
                    System.out.println("Wrong format for exercise!");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        return exercises;
    }

    public void addExercise(Exercise exercise){
        if(!checkNameUnique(exercise.name)){
            Alert alert = new Alert(AlertType.INFORMATION, "Exercise with same name already exists!");
            alert.showAndWait();
            return;
        }
         try (FileWriter fw = new FileWriter(PATH, true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCsv());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkNameUnique(String name){
        for (int i = 0; i < exercises.size(); i++) {
            if(exercises.get(i).name.toLowerCase().equals(name.toLowerCase().trim())){
                return false;
            }
        }
        return true;
    }

    public String toCsv(){
        String entry = "e," + this.name + "," + this.caloriesPerHour + "\n";
        return entry;
    }

    public String toString(){
        return this.getName();
    }
}
