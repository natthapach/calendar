package controllers;

import javafx.application.Application;
import javafx.collections.ObservableList;
import models.EventNote;
import models.Schedule;
import views.MainApplication;
import views.MainView;

import java.util.Date;

public class MainController {

    private Schedule schedule;

    public void start(){
        DatabaseManager dbManager = JSONManager.getInstance();
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

    public boolean save(){
        return true;
    }
}
