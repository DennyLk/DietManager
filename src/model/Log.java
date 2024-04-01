package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Log {

    private static ArrayList<Log> fLog = new ArrayList<>();
    private static ArrayList<Log> wLog = new ArrayList<>();
    private static ArrayList<Log> dailyLog = new ArrayList<>();
    private static double dailyCalories;

    public static final String PATH = "./assets/log.csv";

    private String foodName, nutritions, date, weight;

    public Log(String foodName, String nutritions, String date) {
        this.foodName = foodName;
        this.nutritions = nutritions;
        this.date = date;
    }

    public Log(String weight, String date) {
        this.weight = weight;
        this.date = date;
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
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(":");
                if (data[0].equals("f")) {
                    // Assuming the format is: f,foodName,calories,date
                    fLog.add(new Log(data[1], data[2], data[3]));
                } else if (data[0].equals("w")) {
                    // Assuming the format is: w,weight,date
                    wLog.add(new Log(data[1], data[2]));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void dailyLog(String date){
        dailyCalories = 0.0;
        dailyLog.clear();
        ArrayList<Log> food = getfLog();
        for (int i = 0; i < food.size(); i++) {
            if(food.get(i).date.equals(date)){
                dailyLog.add(food.get(i));
                String[] data = food.get(i).nutritions.split(" ");
                dailyCalories += Double.parseDouble(data[1]);
            }
        }
    }

    public void logFood() {
       
        try (FileWriter fw = new FileWriter("./assets/log.csv", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCsv());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toCsv() {
        String logEntry = "f:" + foodName + ":" + nutritions + ":" + date + "\n";
        return logEntry;
    }

    @Override
    public String toString() {
        String text = date + ":  f:  " + foodName + ":  " + nutritions;
        return text;
    }
}
