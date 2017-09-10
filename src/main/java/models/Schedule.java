package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    public static final String ONCE = "once";
    public static final String DAILY = "daily";
    public static final String WEEKLY = "weekly";
    public static final String MONTHLY = "monthly";
    private List<EventNote> allEvents;
    private List<EventNote> onceEvents;
    private List<EventNote> dailyEvents;
    private List<EventNote> weeklyEvents;
    private List<EventNote> monthlyEvents;
    private Map<String, List<EventNote>> eventsMap;

    public Schedule() {
    }

    public Schedule(List<EventNote> allEvents) {
        this.allEvents = allEvents;
        onceEvents = new ArrayList<>();
        dailyEvents = new ArrayList<>();
        weeklyEvents = new ArrayList<>();
        monthlyEvents = new ArrayList<>();

        eventsMap = new HashMap<>();
        eventsMap.put(ONCE, onceEvents);
        eventsMap.put(DAILY, dailyEvents);
        eventsMap.put(WEEKLY, weeklyEvents);
        eventsMap.put(MONTHLY, monthlyEvents);

        classifiedAllEvents();
    }

    private void classifiedAllEvents(){
        for(EventNote event : allEvents){
            System.out.println("event.getFrequency() = " + event.getFrequency());
            String freq = event.getFrequency();
            eventsMap.get(freq).add(event);
        }
    }

    /**
     * add new event to events
     * @param event new event
     */
    public void addEvent(EventNote event){
        String freq = event.getFrequency();
        eventsMap.get(freq).add(event);
        allEvents.add(event);
    }

    public void delete(EventNote event){
        String freq = event.getFrequency();
        eventsMap.get(freq).remove(event);
        allEvents.remove(event);
    }

    public void update(EventNote oldEvent, EventNote newEvent){
        String oldFreq = oldEvent.getFrequency();
        String newFreq = newEvent.getFrequency();
        eventsMap.get(oldFreq).remove(oldEvent);
        eventsMap.get(newFreq).add(newEvent);
        allEvents.remove(oldEvent);
        allEvents.add(newEvent);
    }

    /**
     *
     * @return list of all event note
     */
    public List<EventNote> getAllEvents(){
        return allEvents;
    }

    @Override
    public String toString(){
        return "Schedule : ";
    }

}
