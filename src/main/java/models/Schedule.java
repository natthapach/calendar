package models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    @SerializedName("data") private List<EventNote> events;

    public Schedule() {
        this(new ArrayList<>());
    }

    public Schedule(List<EventNote> events) {
        this.events = events;
    }

    /**
     * add new event to events
     * @param event new event
     */
    public void addEvent(EventNote event){
        events.add(event);
    }

    public void delete(EventNote event){
        events.remove(event);
    }

    /**
     *
     * @return list of all event note
     */
    public List<EventNote> getEvents(){
        return events;
    }

    @Override
    public String toString(){
        return "Schedule : " + events;
    }

}
