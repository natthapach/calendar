package controllers;

import models.EventNote;
import models.Schedule;

public interface DatabaseManager {
    /**
     * load data from database to schedule
     * @return  schedule contain events from database
     */
    Schedule loadData();
    boolean update(EventNote oldEvent, EventNote newEvent);
    boolean add(EventNote event);
    boolean delete(EventNote event);
}

