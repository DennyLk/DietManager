package model;

import java.util.ArrayList;

public class DietModel {

    private ArrayList<Food> bFoods = new ArrayList<>();
    private ArrayList<Food> rFoods = new ArrayList<>();
    private ArrayList<Log> fLog = new ArrayList<>();
    private ArrayList<Log> wLog = new ArrayList<>();

    public DietModel() {

    }

    public ArrayList<Food> getbFoods() {
        return bFoods;
    }

    public void setbFoods(ArrayList<Food> bFoods) {
        this.bFoods = bFoods;
    }

    public ArrayList<Food> getrFoods() {
        return rFoods;
    }

    public void setrFoods(ArrayList<Food> rFoods) {
        this.rFoods = rFoods;
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

    // method for getting the Food class
    public Food getFood() {
        Food f = null;
        return f;
    }

    public void getAllFoods() {
        Food.getData();
        bFoods = Food.getbFoods();
        rFoods = Food.getrFoods();
    }

    // method for getting the Log Class
    public Log getLog() {
        return null;
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
