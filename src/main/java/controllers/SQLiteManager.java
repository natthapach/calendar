package controllers;

import models.EventNote;
import models.Schedule;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by 708 on 9/1/2017.
 */
public class SQLiteManager implements DatabaseManager {

    private static SQLiteManager instance;

    public static SQLiteManager getInstance(){
        if (instance == null)
            instance = new SQLiteManager();
        return instance;
    }

    private SQLiteManager(){

    }

    @Override
    public Schedule loadData() {

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:schedule.db";
            Connection conn = DriverManager.getConnection(dbURL);

            if(conn != null){
                System.out.println("Connected to database ...");

                String query = "select * from events";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                List<EventNote> events = new ArrayList<>();

                while(resultSet.next()){
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm");

                    String topic = resultSet.getString(1);
                    String detail = resultSet.getString(2);
                    Date startTime = formatter.parse(resultSet.getString(3));
                    Date stopTime = formatter.parse(resultSet.getString(4));

                    EventNote eventNote = new EventNote(topic, detail, startTime, stopTime);
                    events.add(eventNote);

                    System.out.println("eventNote = " + eventNote);
                }

                conn.close();


                return new Schedule(events);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeData(Schedule schedule) {
    }
}
