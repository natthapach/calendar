package controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.EventNote;
import models.Schedule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class JSONManager implements DatabaseManager {
    private static JSONManager instance;
    private final String DB_URL = "/database.json";
    private Gson gson;

    public static JSONManager getInstance(){
        if(instance == null)
            instance = new JSONManager();
        return instance;
    }

    private JSONManager() {
        gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH.mm").create();
    }

    public Schedule loadData() {
        JSONParser parser = new JSONParser();
        Schedule schedule = null;
        BufferedReader reader = null;
        try {
            File f = new File(this.getClass().getResource(DB_URL).toURI());
            reader = new BufferedReader(new FileReader(f));
            Object obj = parser.parse(reader);
            schedule = gson.fromJson(obj.toString(), Schedule.class);
            System.out.println("schedule = " + schedule);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return schedule;
    }

    public void writeData(Schedule schedule) {
        System.out.println("on write");
        System.out.println("schedule = " + schedule);
        BufferedWriter writer = null;
        try {
            File f = new File(this.getClass().getResource(DB_URL).toURI());
            writer = new BufferedWriter(new FileWriter(f));
            gson.toJson(schedule, writer);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
