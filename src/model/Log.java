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
    private static double dailyCalories;

    public static final String PATH = "./assets/log.csv";

    private String logType, foodName, nutritions, date, weight, calories;

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
            }
            bufferedReader.close();

            Collections.sort(fLog, new Comparator<Log>() {
                @Override
                public int compare(Log o1, Log o2) {
                    return o1.date.compareTo(o2.date);
                }
            });

            Collections.sort(wLog, new Comparator<Log>() {
                @Override
                public int compare(Log o1, Log o2) {
                    return o1.date.compareTo(o2.date);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void dailyLog(String date) {
        if (date.isEmpty()) {
            date = LocalDate.now().toString();
        }
        dailyCalories = 0.0;
        dailyLog.clear();
        ArrayList<Log> food = getfLog();
        for (int i = 0; i < food.size(); i++) {
            if (food.get(i).date.equals(date)) {
                dailyLog.add(food.get(i));
                String[] data = food.get(i).nutritions.split(" ");
                dailyCalories += Double.parseDouble(data[1]);
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
        return logEntry;
    }

    @Override
    public String toString() {
        String text = date + ":  f:  " + foodName + ":  " + nutritions;
        return text;
    }

    public String weightFormat() {
        String text = "Weight on " + date + ": " + weight + "kg";
        return text;
    }
}
