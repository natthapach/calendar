package models;

import java.util.Date;

public class EventNote implements Comparable<EventNote>{

    private int id;
    private String topic;
    private String detail;
    private Date startTime;
    private Date stopTime;
    private String frequency;

    public EventNote(int id, String topic, String detail, Date startTime, Date stopTime, String frequency) {
        this.id = id;
        this.topic = topic;
        this.detail = detail;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.frequency = frequency;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetail() {
        return detail;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public int getId() {
        return id;
    }

    public String getFrequency() {
        return frequency;
    }

    @Override
    public String toString(){
        return "Event Note (" + topic + ", " + detail + ", " + startTime + ", " + stopTime + ")";
    }

    public int compareTo(EventNote o) {
        return this.startTime.compareTo(o.startTime);
    }
}
