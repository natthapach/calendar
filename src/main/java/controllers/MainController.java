package controllers;

import models.EventNote;
import models.Schedule;

public class MainController implements CoreController{

    private Schedule schedule;
    private DatabaseManager dbManager;

    /**
     * start the controller
     * prepare connection to model
     */
    public void start(){
        this.dbManager = SQLiteManager.getInstance();
        this.schedule = dbManager.loadData();
    }

    public Schedule getSchedule() {
        return schedule;
    }


    @Override
    public boolean editEvent(EventNote oldEvent, EventNote newEvent) {
        boolean result = dbManager.update(oldEvent, newEvent);
        if(result)
            schedule.update(oldEvent, newEvent);
        return result;
    }

    @Override
    public boolean deleteEvent(EventNote event) {
        boolean result = dbManager.delete(event);
        System.out.println("result = " + result);
        if(result)
            schedule.delete(event);
        return result;
    }

    public boolean addEvent(EventNote eventNote){
        boolean result = dbManager.add(eventNote);
        if(result)
            schedule.addEvent(eventNote);

        return result;
    }

}
