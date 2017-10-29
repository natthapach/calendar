package client.controllers;
import common.models.EventNote;
import common.models.Schedule;

/**
 * core controller manage requests from view
 * and data in model
 */
public interface CoreController {
    /**
     *
     * @return schedule that contain events
     */
    Schedule getSchedule();
    /**
     * manage adding request
     * @param event new data
     * @return true when adding is complete
     */
    boolean addEvent(EventNote event);

    /**
     * manage editing request
     * @param oldEvent old data
     * @param newEvent new data
     * @return true when editing is complete
     */
    boolean editEvent(EventNote oldEvent, EventNote newEvent);

    /**
     * namge deleting request
     * @param event deleted data
     * @return true when deleting is complete
     */
    boolean deleteEvent(EventNote event);

    /**
     * controller start to working
     */
    void start();
}
