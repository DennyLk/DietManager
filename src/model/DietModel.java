package model;

import java.util.ArrayList;

public class DietModel {

    private ArrayList<Food> foods = new ArrayList<>();
    private ArrayList<Log> fLog = new ArrayList<>();
    private ArrayList<Log> wLog = new ArrayList<>();

    public DietModel() {

    }

    public ArrayList<Food> getFoods() {
        return foods;
    }


    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
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
        this.foods = Food.getFoods();    
    }

    public void getAllLogs() {
        Log.getData();
        fLog = Log.getfLog();
        wLog = Log.getwLog();
    }

    public static void logSelectedFood(String foodName) {
        Log.logFood(foodName);
    }

    public static void addFood(String foodName) {
        Food.addFood(foodName);
    }
}
