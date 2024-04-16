package model;

import java.time.LocalDate;
import java.util.ArrayList;

import view.WeightUI;

public class DietModel {

    private ArrayList<Food> foods = new ArrayList<>();
    private ArrayList<Log> fLog = new ArrayList<>();
    private ArrayList<Log> wLog = new ArrayList<>();
    private ArrayList<Log> eLog = new ArrayList<>();
    private ArrayList<Exercise> exercises = new ArrayList<>();


    public DietModel() {

    }

    public ArrayList<Log> getELog() {
        return eLog;
    }

    public void setELog(ArrayList<Log> eLog) {
        this.eLog = eLog;
    }

    public ArrayList<Exercise> getAllExercises(){
        return this.exercises  = Exercise.readExercises();
    }

    public ArrayList<Food> getFoods() {
        getAllFoods();
        return foods;
    }

    public Double getDailyCalories(){
        return Log.getDailyCalories();
    }

    public Double getDailyGoal(){
        return Log.getGoal();
    }

    public Double getDailyWeight(){
        return Log.getTodaysWeight();
    }

    public Double getDailyBurned(){
        return Log.getBurned();
    }

    public Double getDailyNet(){
        return Log.getNet();
    }

    public Double getDailyMargin(){
        return Log.getCalorieMargin();
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<Exercise> getExLog() {
        getAllExercises();
        return exercises ;
    }

    public void setExLog(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
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
        eLog = Log.getExLog();
    }

    public ArrayList<Log> loadAllLogs(){
        ArrayList<Log> array = new ArrayList<>();
        array.addAll(fLog);
        array.addAll(eLog);
        return array;
    }

    public static void updateLog(){
        Log.getData();
    }

    public static void addDailyLog(String date){
        Log.dailyLog(date);
    }

    public void deleteLog(String log){
        Log.delete(log);
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

    public static void logSelectedExercise(String exerciseName, double minutes, String date){
        if(date != null){
            Log log = new Log("e", exerciseName, minutes, date);
            log.log();
        }
        else{
            Log log = new Log("e", exerciseName, minutes, LocalDate.now().toString());
            log.log();
        }
    }

    public static void logSelectedExercise(String exerciseName, double minutes){
        logSelectedExercise(exerciseName, minutes, LocalDate.now().toString());
    }

    public static void logSelectedFood(String foodName){
        logSelectedFood(foodName, null);
    }

    public static void logSelectedWeight(String weight, String date) {
        if(date != null){
            Log log = new Log(weight, date);
            log.setLogType("w");
            log.log();
        }
        else{
            Log log = new Log(weight, LocalDate.now().toString());
            log.setLogType("w");
            log.log();
        }
    }


    public static void logSelectedWeight(String weight){
        logSelectedWeight(weight, null);
    }

    public static void logCalorieGoal(String calories, String date) {
        if(date != null){
            Log log = new Log(calories, date);
            log.setLogType("c");
            log.log();
        }
        else{
            Log log = new Log(calories, LocalDate.now().toString());
            log.setLogType("c");
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

    public static void addExercise(String name, double caloriesPerHour){
        Exercise ex = new Exercise(name, caloriesPerHour);
        ex.addExercise(ex);
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

    

    // public Double caloriesBurnedPerHour(Exercise exercise, double time, LocalDate date){
    //     double calories = exercise.getCaloriesPerHour();
    //     if(date == null){
    //         Log weight = Log.getwLog().get(Log.getwLog().size() - 1);
            
    //     }
        
    //     return null;
    // }

    
}
