package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Log {

    private static ArrayList<Log> fLog = new ArrayList<>();
    private static ArrayList<Log> wLog = new ArrayList<>();
    private static ArrayList<Log> dailyLog = new ArrayList<>();
    private static ArrayList<Log> exLog = new ArrayList<>();
    private static ArrayList<Log> cLog = new ArrayList<>();
    private static ArrayList<Exercise> exercises = Exercise.getExercises();
    private static ArrayList<Food> foods = Food.loadFoods();

    private static double dailyCalories, goal, todaysWeight, burned, net, calorieMargin;

    public static final String PATH = "./assets/log.csv";
    
    private String logType, foodName;
    private static String nutritions;
    private String date;
    private String weight;
    private String calories;
    private String exerciseName;
    private double exerciseMinutes;

    public Log(String logType, String date, String calories) {
        this.logType = logType;
        this.date = date;
        this.calories = calories;
    }

    public Log(String logType, String foodName, String nutritions, String date) {
        this.logType = logType;
        this.foodName = foodName;
        this.nutritions = nutritions;
        this.date = date;
    }

    public Log(String weight, String date) {
        this.weight = weight;
        this.date = date;
    }

    public Log(String logType, String exerciseName, Double exerciseMinutes, String date){
        this.logType = logType;
        this.exerciseName = exerciseName;
        this.exerciseMinutes = exerciseMinutes;
        this.date = date;
    }

    public static double getCalorieMargin() {
        return calorieMargin;
    }

    public static double getGoal() {
        return goal;
    }

    public static double getTodaysWeight() {
        return todaysWeight;
    }

    public static double getBurned() {
        return burned;
    }

    public static double getNet() {
        return net;
    }

    public static ArrayList<Log> getExLog() {
        return exLog;
    }

    public static void setExLog(ArrayList<Log> exLog) {
        Log.exLog = exLog;
    }


    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public double getExerciseCalories() {
        return exerciseMinutes;
    }

    public void setExerciseCalories(double exerciseCalories) {
        this.exerciseMinutes = exerciseCalories;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getDate() {
        return date;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public static double getDailyCalories() {
        return dailyCalories;
    }

    public static ArrayList<Log> getDailyLog() {
        return dailyLog;
    }

    public static void setDailyLog(ArrayList<Log> dailyLog) {
        Log.dailyLog = dailyLog;
    }

    public static ArrayList<Log> getfLog() {
        return fLog;
    }

    public static void setfLog(ArrayList<Log> fLog) {
        Log.fLog = fLog;
    }

    public static ArrayList<Log> getwLog() {
        return wLog;
    }

    public static void setwLog(ArrayList<Log> wLog) {
        Log.wLog = wLog;
    }

    public static void getData() {
        fLog.clear();
        wLog.clear();
        exLog.clear();
        cLog.clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String date = data[0] + "-" + data[1] + "-" + data[2];
                if (data[3].equals("f")) {
                    for (int i = 0; i < foods.size(); i++) {
                        if (data[4].toLowerCase().trim().equals(foods.get(i).getName().toLowerCase().trim())) {
                            nutritions = foods.get(i).getCalories().toString();
                        }
                    }
                    fLog.add(new Log(data[3], data[4], nutritions, date));
                } else if (data[3].equals("w")) {
                    boolean isLogged = false;
                    for (Log log : wLog) {
                        if (log.getDate().equals(date)) {
                            log.setWeight(data[4]);
                            isLogged = true;
                            break;
                        }
                    }
                    if (!isLogged) {
                        wLog.add(new Log(data[4], date));
                    }
                }
                else if(data[3].equals("e")){
                    exLog.add(new Log(data[3], data[4], Double.parseDouble(data[5]), date));
                }
                else if(data[3].equals("c")){
                    Log log = new Log(data[4], date);
                    log.setLogType("c");
                    cLog.add(log);
                }
            }
            bufferedReader.close();

            sort(exLog);
            sort(wLog);
            sort(fLog);
            sort(cLog);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void sort(ArrayList<Log> array){
        Collections.sort(array, new Comparator<Log>() {
            @Override
            public int compare(Log o1, Log o2) {
                return o1.date.compareTo(o2.date);
            }
        });
    }

    public static Double checkForLatestDate(String date, ArrayList<Log> array, Double x){
        x = 0.0;
        for (int i = 0; i < array.size(); i++) {
            if(LocalDate.parse(array.get(i).date).isAfter(LocalDate.parse(date))){
                break;
            }
            x = Double.parseDouble(array.get(i).weight);
        }
        return x;
    }

    public static void dailyLog(String date) {
        if (date.isEmpty()) {
            date = LocalDate.now().toString();
        }
        todaysWeight = checkForLatestDate(date, wLog, todaysWeight);
        goal = checkForLatestDate(date, cLog, goal);

        if(goal == 0.0){
            goal = 2000.0;
        }
        if(todaysWeight == 0.0){
            todaysWeight = 68.0;
        }

        burned =  burningEquation(exLog, date);

        dailyCalories = 0.0;
        dailyLog.clear();
        ArrayList<Log> exercises = getExLog();

        for (int i = 0; i < fLog.size(); i++) {
            if (fLog.get(i).date.equals(date)) {
                dailyLog.add(fLog.get(i));
                for (int j = 0; j < foods.size(); j++) {
                    if (fLog.get(i).foodName.toLowerCase().trim().equals(foods.get(j).getName().toLowerCase().trim())) {
                        dailyCalories += foods.get(j).getCalories();
                    }
                }
            } 
        }
        for (int i = 0; i < exercises.size(); i++) {
            if(exercises.get(i).date.equals(date)){
                dailyLog.add(exercises.get(i));
            }
        }
        net = dailyCalories - burned;
        calorieMargin = goal - net;
    }

    public static Double burningEquation(ArrayList<Log> array, String date){
        double x = 0.0;
        double calories = 0.0;
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).date.equals(date)){
                for (int j = 0; j < exercises.size(); j++) {
                    if(array.get(i).exerciseName.toLowerCase().trim().equals(exercises.get(j).getName().toLowerCase().trim())){
                        calories = exercises.get(j).getCaloriesPerHour();
                        break;
                    }
                }
                x += calories * todaysWeight * (array.get(i).exerciseMinutes/60);
            }
        }
        return x;
    }

    public void log() {
        try (FileWriter fw = new FileWriter("./assets/log.csv", true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCsv());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toCsv() {
        String logEntry = "";
        String[] parsDateData = date.split("-");
        String parsedDate = parsDateData[0] + "," + parsDateData[1] + "," + parsDateData[2];
        if (logType == "f") {
            logEntry = parsedDate + "," + logType + "," + foodName + ",1 \n";
        } else if (logType == "c") {
            logEntry = parsedDate + "," + logType + "," + weight + "\n";
        } else if (logType == "w") {
            logEntry = parsedDate + "," + logType + "," + weight + "\n";
        }
        else if(logType == "e"){
            logEntry = parsedDate + "," + logType + "," + exerciseName + "," + exerciseMinutes + "\n";
        }
        return logEntry;
    }

    public static void delete(String log){
        for (int i = 0; i < fLog.size(); i++) {
            if(fLog.get(i).toString().equals(log)){
                fLog.remove(i);
                resetLog();
                break;
            }
        }
        for (int i = 0; i < exLog.size(); i++) {
            if(exLog.get(i).toString().equals(log)){
                exLog.remove(i);
                resetLog();
                return;
            }
        }
    }

    public static void resetLog(){
        deleteFromFile();
        BufferedWriter bWriter = null;
        try {
            bWriter = new BufferedWriter(new FileWriter(PATH, true));
            for (Log foodLog : fLog) {
                foodLog.setLogType("f");
                bWriter.write(foodLog.toCsv());
            }
            for (Log exerciseLog : exLog) {
                exerciseLog.setLogType("e");
                bWriter.write(exerciseLog.toCsv());
            }
            for (Log weightLog : wLog) {
                weightLog.setLogType("w");
                bWriter.write(weightLog.toCsv());
            }
            for (Log goalLog : cLog) {
                goalLog.setLogType("c");
                bWriter.write(goalLog.toCsv());
            }
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromFile(){
        boolean append = false;
        try {
            FileWriter fileWriter = new FileWriter(PATH, append);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String text = null;
        if(logType.equals("e")){
            text = date + ": " + exerciseName + " for " + exerciseMinutes + " minutes";
        }
        else if(logType.equals("f")){
            text = date + ":  f:  " + foodName + ":  " + nutritions + " calories";
        }
        else if(logType.equals("w")){
            text = date + ": weight: " + weight;
        }
        return text;
    }

    public String weightFormat() {
        String text = "Weight on " + date + ": " + weight + "kg";
        return text;
    }

    public String exerciseFormat(){
        String text = "Date: "+ date + ", Exercise :" + exerciseName + ", Exercise length " + exerciseMinutes;  
        return text;
    }
}
