package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ScheduleTest {
    private Schedule schedule;

    @Before
    public void setUp() throws Exception {
        Field eventsField = schedule.getClass().getDeclaredField("events");
        eventsField.setAccessible(true);

        ArrayList<EventNote> events = new ArrayList<>();
        for (int i=0; i<10; i++){
            Date sdate = new Date(1000 + i*100);
            Date edate = new Date(1000 + i*101);
            events.add(new EventNote("event "+i, "detail", sdate, edate));
        }
        schedule = new Schedule(events);

        eventsField.set(schedule, events);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddOneEvent(){
        Date date = new Date();
        EventNote eventNote = new EventNote("new event", "detail", date, date);
        schedule.addEvent(eventNote);

        assertEquals(11, schedule.getEvents().size());
    }

    @Test
    public void testAddTwoEvents(){
        Date date = new Date();
        EventNote eventNote1 = new EventNote("new event 1", "detail", date, date);
        EventNote eventNote2 = new EventNote("new event 2", "detail", date, date);

        schedule.addEvent(eventNote1);
        schedule.addEvent(eventNote2);

        assertEquals(12, schedule.getEvents().size());
    }


}