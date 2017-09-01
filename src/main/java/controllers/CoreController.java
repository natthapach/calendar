package controllers;
import models.EventNote;
import models.Schedule;

import java.awt.*;

public interface CoreController {
    Schedule getSchedule();
    void addEvent(EventNote event);
    void editEvent(EventNote oldEvent, EventNote newEvent);
    void deleteEvent(EventNote event);
    void start();
}
