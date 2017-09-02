package controllers;
import models.EventNote;
import models.Schedule;

import java.awt.*;

public interface CoreController {
    Schedule getSchedule();
    boolean addEvent(EventNote event);
    boolean editEvent(EventNote oldEvent, EventNote newEvent);
    boolean deleteEvent(EventNote event);
    void start();
}
