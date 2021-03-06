package common;

import common.models.EventNote;
import common.models.Schedule;

import java.util.Date;

/**
 * database manager manage request about data on database
 */
public interface DatabaseManager {
    /**
     * load data from database to schedule
     * @return  schedule contain events from database
     */
    Schedule loadData();
    /**
     * update existing data record
     * @param oldEvent  old data
     * @param newEvent  new data
     * @return  true when updating is complete
     */
    boolean update(EventNote oldEvent, EventNote newEvent);
    /**
     * add new data record
     * @param event new data
     * @return  true when adding is complete
     */
    boolean add(EventNote event);
    /**
     * delete existing data
     * @param event deleted data
     * @return true when removing is complete
     */
    boolean delete(EventNote event);

    /**
     * get specific event
     * @param topic
     * @param startTime
     * @param frequency
     * @return event that match topic, startTime and frequency
     */
    EventNote getEventNote(String topic, Date startTime, String frequency);
}

