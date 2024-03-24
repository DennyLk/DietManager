package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

public class Log {

    private static ArrayList<Log> fLog = new ArrayList<>();
    private static ArrayList<Log> wLog = new ArrayList<>();

    private String foodName, calories, date, weight;

    public Log(String foodName, String calories, String date) {
        this.foodName = foodName;
        this.calories = calories;
        this.date = date;
    }

    public Log(String weight, String date) {
        this.weight = weight;
        this.date = date;
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
        String path = "src/model/assets/log.csv";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
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
    

    public void setData(String data) {

    }

    public static void logFood(String foodName) {
        String logEntry = "f," + foodName + ","+ LocalDate.now();
        
        try (FileWriter fw = new FileWriter("src/model/assets/log.csv", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(logEntry + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String text = String.format("%s: - Date: %s", foodName, date);
        return text;
    }
}
