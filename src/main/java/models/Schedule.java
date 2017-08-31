package models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Schedule {

    @SerializedName("data") private List<EventNote> events;

    /**
     * add new event to events
     * @param event new event
     */
    public void addEvent(EventNote event){
        events.add(event);
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
