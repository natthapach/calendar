package models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    @SerializedName("data") private List<EventNote> events;

//    public Schedule(List<EventNote> events) {
//        this.events = events;
//    }

    public void addEvent(EventNote event){
        events.add(event);
    }

    public List<EventNote> getEvents(){
        return events;
    }
    public ArrayList<EventNote> getEventsByYear(final int year){
       return selectEvents(new EventSelector() {
           public boolean isSelected(EventNote e) {
               return year == e.getStartTime().getYear();
           }
       });
    }
    public ArrayList<EventNote> getEventsByMonth(final int year, final int month) {
        return selectEvents(new EventSelector() {
            public boolean isSelected(EventNote e) {
                return year == e.getStartTime().getYear() &&
                        month == e.getStartTime().getMonth();
            }
        });
    }
    public ArrayList<EventNote> getEventsByDate(final int year, final int month, final int date) {
        return selectEvents(new EventSelector() {
            public boolean isSelected(EventNote e) {
                return year == e.getStartTime().getYear() &&
                        month == e.getStartTime().getMonth() &&
                        date == e.getStartTime().getDate();
            }
        });
    }

    private ArrayList<EventNote> selectEvents(EventSelector selector){
        ArrayList<EventNote> events = new ArrayList<EventNote>();

        for(EventNote e: this.events)
            if(selector.isSelected(e))
                events.add(e);

        return events;
    }
    private interface EventSelector {
        boolean isSelected(EventNote e);
    }

    @Override
    public String toString(){
        return "Schedule : " + events;
    }
}
