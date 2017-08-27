package controllers;

import models.EventNote;
import models.Schedule;

import java.util.Date;

public class MainController {

    public void start(){
        DatabaseManager dbManager = JSONManager.getInstance();
        Schedule schedule = dbManager.loadData();
        schedule.addEvent(new EventNote("event2", "Hi Jim", new Date(1000), new Date(2000)));

        dbManager.writeData(schedule);
    }
}
