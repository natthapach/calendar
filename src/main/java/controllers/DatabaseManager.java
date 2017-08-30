package controllers;

import models.Schedule;

public interface DatabaseManager {
    /**
     * load data from database to schedule
     * @return  schedule contain events from database
     */
    Schedule loadData();

    /**
     * write all events in schedule to database
     * @param schedule  schedule that contain events
     */
    void writeData(Schedule schedule);
}

