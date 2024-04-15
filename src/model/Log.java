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

    private static double dailyCalories;

    public static final String PATH = "./assets/log.csv";
    
    private String logType, foodName, nutritions, date, weight, calories;
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
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String date = data[0] + "-" + data[1] + "-" + data[2];
                if (data[3].equals("f")) {
                    String nutritions = data[5] + "," + data[6] + "," + data[7] + "," + data[8];
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
            }
            bufferedReader.close();

            // Collections.sort(fLog, new Comparator<Log>() {
            //     @Override
            //     public int compare(Log o1, Log o2) {
            //         return o1.date.compareTo(o2.date);
            //     }
            // });

            // Collections.sort(wLog, new Comparator<Log>() {
            //     @Override
            //     public int compare(Log o1, Log o2) {
            //         return o1.date.compareTo(o2.date);
            //     }
            // });

            // Collections.sort(exLog, new Comparator<Log>() {
            //     @Override
            //     public int compare(Log o1, Log o2) {
            //         return o1.date.compareTo(o2.date);
            //     }
            // });
            sort(exLog);
            sort(wLog);
            sort(fLog);
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

    public static void dailyLog(String date) {
        if (date.isEmpty()) {
            date = LocalDate.now().toString();
        }
        dailyCalories = 0.0;
        dailyLog.clear();
        ArrayList<Log> food = getfLog();
        ArrayList<Log> exercises = getExLog();
        for (int i = 0; i < food.size(); i++) {
            if (food.get(i).date.equals(date)) {
                dailyLog.add(food.get(i));
                String[] data = food.get(i).nutritions.split(" ");
                dailyCalories += Double.parseDouble(data[1]);
            }
            
        }
        for (int i = 0; i < exercises.size(); i++) {
            if(exercises.get(i).date.equals(date)){
                dailyLog.add(exercises.get(i));
            }
        }
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
            logEntry = parsedDate + "," + logType + "," + foodName + "," + nutritions + "\n";
        } else if (logType == "c") {
            logEntry = parsedDate + "," + logType + "," + calories + "\n";
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
            text = date + ":  f:  " + foodName + ":  " + nutritions;
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
