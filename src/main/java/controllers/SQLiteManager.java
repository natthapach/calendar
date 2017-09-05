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
    private final SimpleDateFormat formatter;

    public static SQLiteManager getInstance(){
        if (instance == null)
            instance = new SQLiteManager();
        return instance;
    }

    private SQLiteManager(){
        formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm");
    }


    @Override
    public Schedule loadData() {

        try {
            Connection conn = prepareConnection();

            if(conn != null){
                System.out.println("Connected to database ...");

                String query = "select * from events";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                List<EventNote> events = new ArrayList<>();

                while(resultSet.next()){

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
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(EventNote oldEvent, EventNote newEvent) {
        try {
            Connection conn = prepareConnection();

            if(conn != null){
                String oldTopic = oldEvent.getTopic().replace("\'","\''");
                String oldStartTime = formatter.format(oldEvent.getStartTime()).replace("\'","\''");

                String topic = newEvent.getTopic().replace("\'","\''");
                String startTime = formatter.format(newEvent.getStartTime()).replace("\'","\''");
                String detail = newEvent.getDetail().replace("\'","\''");
                String endTime = formatter.format(newEvent.getStopTime()).replace("\'","\''");

                String sql = String.format("update events " +
                                            "set topic=\'%s\', detail=\'%s\', start_time=\'%s\', end_time=\'%s\' " +
                                            "where topic=\'%s\' and start_time=\'%s\'",
                                            topic, detail, startTime, endTime, oldTopic, oldStartTime);
                Statement statement = conn.createStatement();
                int resultSet = statement.executeUpdate(sql);

                System.out.println("resultSet = " + resultSet);

                conn.close();

                return true;
            }
        } catch (SQLException e) {
            System.err.println("Add data failure " + e.getMessage());
            System.err.println("Error code " + e.getErrorCode());
        }

        return false;
    }

    @Override
    public boolean add(EventNote event) {
        try {
            Connection conn = prepareConnection();

            if(conn != null){
                String topic = event.getTopic().replace("\'","\''");
                String detail = event.getDetail().replace("\'","\''");
                String startTime = formatter.format(event.getStartTime()).replace("\'","\''");
                String endTime = formatter.format(event.getStopTime()).replace("\'","\''");

                String sql = String.format("insert into events " +
                                             "values (\'%s\', \'%s\', \'%s\', \'%s\')",
                                            topic, detail, startTime, endTime);
                Statement statement = conn.createStatement();
                int resultSet = statement.executeUpdate(sql);
                System.out.println("statement = " + statement);

                conn.close();

                return true;
            }
        } catch (SQLException e) {
            System.err.println("Add data failure " + e.getMessage());
            System.err.println("Error code " + e.getErrorCode());
        }

        return false;
    }

    @Override
    public boolean delete(EventNote event) {
        try {
            Connection conn = prepareConnection();

            if(conn != null){
                String topic = event.getTopic().replace("\'","\''");
                String startTime = formatter.format(event.getStartTime()).replace("\'","\''");

                String sql = String.format("delete from events " +
                                            "where topic = \'%s\' and start_time = \'%s\'",
                                            topic, startTime);
                Statement statement = conn.createStatement();
                int resultSet = statement.executeUpdate(sql);

                System.out.println("resultSet = " + resultSet);

                conn.close();

                return true;
            }
        } catch (SQLException e) {
            System.err.println("Delete data failure " + e.getMessage());
            System.err.println("Error code " + e.getErrorCode());
        }

        return false;
    }

    /**
     * initialize SQLite connection
     * @return connection to DB
     */
    private Connection prepareConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:schedule.db";
            Connection conn = DriverManager.getConnection(dbURL);

            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("connection Fail cannot find database");
        }

        return null;
    }

}
