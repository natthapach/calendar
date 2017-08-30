package controllers;

import models.EventNote;
import models.Schedule;

public class MainController {

    private Schedule schedule;
    private DatabaseManager dbManager;

    public void start(){
        this.dbManager = JSONManager.getInstance();
        this.schedule = dbManager.loadData();
//        schedule.addEvent(new EventNote("event2", "Hi Jim", new Date(1000), new Date(2000)));

//        dbManager.writeData(schedule);


    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void addEventNote(EventNote eventNote){
        schedule.addEvent(eventNote);
        System.out.println("add on controller");
        System.out.println("schedule.getEvents() = " + schedule.getEvents());
    }

    public void save(){
        dbManager.writeData(this.schedule);
    }
}
