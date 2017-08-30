package models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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

    /**
     * filter events from target year
     * @param year  target year
     * @return list of events in target year
     */
    public ArrayList<EventNote> getEventsByYear(final int year){
       return selectEvents(new EventSelector() {
           public boolean isSelected(EventNote e) {
               return year == e.getStartTime().getYear();
           }
       });
    }

    /**
     * filter events from target month
     * @param year year of month
     * @param month
     * @return
     */
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
