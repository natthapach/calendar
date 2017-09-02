package controllers;

import models.EventNote;
import models.Schedule;

import java.util.ArrayList;

public class MainController implements CoreController{

    private Schedule schedule;
    private DatabaseManager dbManager;

    public void start(){
//        this.dbManager = JSONManager.getInstance();
        this.dbManager = SQLiteManager.getInstance();
        this.schedule = dbManager.loadData();
//        this.schedule = new Schedule();
//        schedule.setEvents(new ArrayList<>());
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public boolean editEvent(EventNote oldEvent, EventNote newEvent) {

        return true;
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
