package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class DietModel {

    private ArrayList<Food> foods = new ArrayList<>();
    private ArrayList<Log> fLog = new ArrayList<>();
    private ArrayList<Log> wLog = new ArrayList<>();

    public DietModel() {

    }

    public ArrayList<Food> getFoods() {
        getAllFoods();
        return foods;
    }

    public Double getDailyCalories(){
        return Log.getDailyCalories();
    }


    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<Log> getDailyLog(){
        return Log.getDailyLog();
    }
    

    public ArrayList<Log> getfLog() {
        return fLog;
    }

    public void setfLog(ArrayList<Log> fLog) {
        this.fLog = fLog;
    }

    public ArrayList<Log> getwLog() {
        return wLog;
    }

    public void setwLog(ArrayList<Log> wLog) {
        this.wLog = wLog;
    }

    public void getAllFoods() {
        this.foods = Food.loadFoods();    
    }

    public void getAllLogs() {
        Log.getData();
        fLog = Log.getfLog();
        wLog = Log.getwLog();
    }

    public static void updateLog(){
        Log.getData();
    }

    public static void addDailyLog(String date){
        Log.dailyLog(date);
    }

    public static void logSelectedFood(String foodName, String date) {
        String[] data = foodName.split(":");
        if(date != null){
            Log log = new Log("f", data[0], data[1], date);
            log.log();
        }
        else{
            Log log = new Log("f", data[0], data[1], LocalDate.now().toString());
            log.log();
        }
    }

    public static void logSelectedFood(String foodName){
        logSelectedFood(foodName, null);
    }

    public static void logCalorieGoal(String calories, String date) {
        if(date != null){
            Log log = new Log("c", date, calories);
            log.log();
        }
        else{
            Log log = new Log("c", LocalDate.now().toString(), calories);
            log.log();
        }
    }

    public static void logCalorieGoal(String calories){
        logCalorieGoal(calories, null);
    }

    public static void addFood(String foodName) {
        String[] parts = foodName.split(",");

        BasicFood bf = new BasicFood(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
        bf.addBasicFood();
    }

    public static void addRecipe(String recipe) {
        String[] parts = recipe.split(",");
        String recipeParts = "";
        for (int i = 1; i < parts.length; i++) {
            if (i == parts.length-1) {
                recipeParts += parts[i];
            } else {
                recipeParts += parts[i] + ",";
            }
        }
        Recipe r = new Recipe(parts[0], recipeParts);
        r.addRecipe();
    }

    
}
