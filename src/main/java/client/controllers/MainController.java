package client.controllers;

import common.DatabaseManager;
import common.models.EventNote;
import common.models.Schedule;

public class MainController implements CoreController{

    private Schedule schedule;
    private DatabaseManager dbManager;

    /**
     * start the controller
     * prepare connection to model
     */
    public void start(){
        this.schedule = dbManager.loadData();
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setDbManager(DatabaseManager dbManager){
        this.dbManager = dbManager;
    }
    @Override
    public boolean editEvent(EventNote oldEvent, EventNote newEvent) {
        boolean result = dbManager.update(oldEvent, newEvent);

        if(result) {
            EventNote newEventNote = dbManager.getEventNote(newEvent.getTopic(), newEvent.getStartTime(), newEvent.getFrequency());
            if (newEventNote != null)
                schedule.update(oldEvent, newEvent);
            else
                return false;
        }
        return result;
    }

    @Override
    public boolean deleteEvent(EventNote event) {
        boolean result = dbManager.delete(event);
        if(result)
            schedule.delete(event);
        return result;
    }

    public boolean addEvent(EventNote eventNote){
        boolean result = dbManager.add(eventNote);
        if(result) {
            EventNote newEventNote = dbManager.getEventNote(eventNote.getTopic(), eventNote.getStartTime(), eventNote.getFrequency());
            if (newEventNote != null)
                schedule.addEvent(newEventNote);
            else
                return false;
        }
        return result;
    }

}
