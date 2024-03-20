package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Log implements Loader{

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

    public static void getData(){
        String path = "src/model/assets/log.csv";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                if(data[0].equals("f")){
                    //Log format --> f,foodName,calories,date
                    fLog.add(new Log(data[1], data[2], data[3]));
                }
                else if(data[0].equals("w")){
                    //Log format --> w,weight,date
                    wLog.add(new Log(data[1], data[2]));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void setData(String data){

    }
}
