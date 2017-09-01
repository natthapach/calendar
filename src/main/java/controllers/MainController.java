package controllers;

import models.EventNote;
import models.Schedule;

import java.util.ArrayList;

public class MainController {

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

    public void addEventNote(EventNote eventNote){
        schedule.addEvent(eventNote);
        System.out.println("add on controller");
        System.out.println("schedule.getEvents() = " + schedule.getEvents());
    }

    public void save(){
        dbManager.writeData(this.schedule);
    }

}
