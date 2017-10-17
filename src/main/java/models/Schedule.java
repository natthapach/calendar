package models;

import java.util.*;

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
        this(new ArrayList<>());
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
//            System.out.println("event.getFrequency() = " + event.getFrequency());
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

    /**
     * delete event from schedule
     * @param event
     */
    public void delete(EventNote event){
        String freq = event.getFrequency();
        eventsMap.get(freq).remove(event);
        allEvents.remove(event);
    }

    /**
     * replace oldEvent with newEvent
     * @param oldEvent
     * @param newEvent
     */
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

    /**
     *
     * @param date Date to get events
     * @return events relate date
     */
    public List<EventNote> getEvents(Date date){
        System.out.println("getEvents date = " + date);
        List<EventNote> events = new ArrayList<>();
        events.addAll(getEventsFormOnce(date));
        events.addAll(getEventsFormDaily(date));
        events.addAll(getEventsFormWeekly(date));
        events.addAll(getEventsFormMonthly(date));
        return events;
    }
    private List<EventNote> getEventsFormOnce(Date date){
        List<EventNote> events = new ArrayList<>();
        for (EventNote eventNote : onceEvents){
            Date eDate = eventNote.getStartTime();
            if (eDate.getDate() == date.getDate() && eDate.getMonth() == date.getMonth() && eDate.getYear() == date.getYear())
                events.add(eventNote);
            System.out.println("eDate = " + eDate);
        }
        return events;
    }
    private List<EventNote> getEventsFormDaily(Date date){
        return dailyEvents;
    }
    private List<EventNote> getEventsFormWeekly(Date date){
        List<EventNote> events = new ArrayList<>();
        for (EventNote eventNote : weeklyEvents){
            Date eDate = eventNote.getStartTime();
            if (date.getDay() == eDate.getDay())
                events.add(eventNote);
        }
        return events;
    }
    private List<EventNote> getEventsFormMonthly(Date date){
        List<EventNote> events = new ArrayList<>();
        for (EventNote event : monthlyEvents){
            Date eDate = event.getStartTime();
            if (date.getDate() == eDate.getDate())
                events.add(event);
        }
        return events;
    }
    @Override
    public String toString(){
        return "Schedule : ";
    }

}
