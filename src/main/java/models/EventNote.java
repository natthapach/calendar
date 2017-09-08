package models;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class EventNote implements Comparable<EventNote>{

    private String topic;
    private String detail;
    private Date startTime;
    private Date stopTime;

    public EventNote(String topic, String detail, Date startTime, Date stopTime) {
        this.topic = topic;
        this.detail = detail;
        this.startTime = startTime;
        this.stopTime = stopTime;
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



    @Override
    public String toString(){
        return "Event Note (" + topic + ", " + detail + ", " + startTime + ", " + stopTime + ")";
    }

    public int compareTo(EventNote o) {
        return this.startTime.compareTo(o.startTime);
    }
}
