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
    private final String DEFAULT_URL = "schedule.db";
    private final SimpleDateFormat formatter;
    private String url;
    private Map<String, String> frequencyReverseMap;

    public static SQLiteManager getInstance(){
        if (instance == null)
            instance = new SQLiteManager();
        return instance;
    }

    private SQLiteManager(){
        formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm");
        url = DEFAULT_URL;

        frequencyReverseMap = new HashMap<>();
        frequencyReverseMap.put(Schedule.ONCE, "O");
        frequencyReverseMap.put(Schedule.DAILY, "D");
        frequencyReverseMap.put(Schedule.WEEKLY, "W");
        frequencyReverseMap.put(Schedule.MONTHLY, "M");
    }


    @Override
    public Schedule loadData() {

        try {
            Connection conn = prepareConnection();

            if(conn != null){
                System.out.println("Connected to database ...");

                String query = "select events.id, events.topic, events.detail, events.start_time, events.end_time, event_frequency.name as frequency " +
                                "from events " +
                                "join event_frequency " +
                                "on events.frequency = event_frequency.id";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                List<EventNote> events = new ArrayList<>();

                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String topic = resultSet.getString(2);
                    String detail = resultSet.getString(3);
                    Date startTime = formatter.parse(resultSet.getString(4));
                    Date stopTime = formatter.parse(resultSet.getString(5));
                    String frequency = resultSet.getString(6);

                    EventNote eventNote = new EventNote(id, topic, detail, startTime, stopTime, frequency);
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
                int id = oldEvent.getId();

                String topic = newEvent.getTopic().replace("\'","\''");
                String startTime = formatter.format(newEvent.getStartTime()).replace("\'","\''");
                String detail = newEvent.getDetail().replace("\'","\''");
                String endTime = formatter.format(newEvent.getStopTime()).replace("\'","\''");
                String frequency = frequencyReverseMap.get(newEvent.getFrequency());

                String sql = String.format("update events " +
                                            "set topic=\'%s\', detail=\'%s\', start_time=\'%s\', end_time=\'%s\', frequency=\'%s\' " +
                                            "where id=%d",
                                            topic, detail, startTime, endTime, frequency, id);
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
                String frequency = frequencyReverseMap.get(event.getFrequency());
                String sql = String.format("insert into events (topic, detail, start_time, end_time, frequency)" +
                                             "values (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\')",
                                            topic, detail, startTime, endTime, frequency);
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
                int id = event.getId();

                String sql = String.format("delete from events " +
                                            "where id = %d",
                                            id);
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

    @Override
    public EventNote getEventNote(String topic, Date startTime, String frequency) {
        try {
            String start_time = formatter.format(startTime);
            topic = topic.replace("\'","\''");
            String freq = frequencyReverseMap.get(frequency);

            Connection connection = prepareConnection();
            String sql = String.format("select events.id, events.topic, events.detail, events.start_time, events.end_time, event_frequency.name as frequency " +
                            "from events " +
                            "join event_frequency " +
                            "on events.frequency = event_frequency.id " +
                            "where topic=\'%s\' and start_time=\'%s\' and events.frequency=\'%s\'",
                    topic, start_time, freq);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()){
                int id = resultSet.getInt(1);
                String topicReal = resultSet.getString(2);
                String detailReal = resultSet.getString(3);
                Date startTimeReal = formatter.parse(resultSet.getString(4));
                Date stopTimeReal = formatter.parse(resultSet.getString(5));
                String frequencyReal = resultSet.getString(6);
                connection.close();
                return new EventNote(id, topicReal, detailReal, startTimeReal, stopTimeReal, frequencyReal);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * initialize SQLite connection
     * @return connection to DB
     */
    private Connection prepareConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:" + url;
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
