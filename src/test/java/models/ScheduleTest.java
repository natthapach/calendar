package models;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    private Schedule schedule;

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("before each");

        ArrayList<EventNote> events = new ArrayList<>();
        for (int i=0; i<10; i++){
            Date sdate = new Date(1000 + i*100);
            Date edate = new Date(1000 + i*101);
            events.add(new EventNote(i, "event "+i, "detail", sdate, edate, "daily"));
        }
        schedule = new Schedule(events);
        Field eventsField = schedule.getClass().getDeclaredField("events");
        eventsField.setAccessible(true);

        eventsField.set(schedule, events);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testAddOneEvent(){
        Date date = new Date();
        EventNote eventNote = new EventNote("new event", "detail", date, date);
        schedule.addEvent(eventNote);

        assertEquals(11, schedule.getAllEvents().size());
    }

    @Test
    void testAddTwoEvents(){
        Date date = new Date();
        EventNote eventNote1 = new EventNote("new event 1", "detail", date, date);
        EventNote eventNote2 = new EventNote("new event 2", "detail", date, date);

        schedule.addEvent(eventNote1);
        schedule.addEvent(eventNote2);

        assertEquals(12, schedule.getAllEvents().size());
    }

    @Test
    void testDeleteOneExistEvent(){
        EventNote eventNote = schedule.getAllEvents().get(0);
        schedule.delete(eventNote);

        assertEquals(9, schedule.getAllEvents().size());
    }

    @Test
    void testDeleteOneNonExistEvent(){
        EventNote eventNote = new EventNote("event 1", "detail", new Date(), new Date());
        schedule.delete(eventNote);

        assertEquals(10, schedule.getAllEvents().size());
    }

    @Test
    void testUpdateEvent(){
        EventNote eventNoteTest = new EventNote("event test", "detail test", new Date(0, 0, 0), new Date(1, 1, 1));
        EventNote eventNote = schedule.getAllEvents().get(0);

        schedule.update(eventNote, eventNoteTest);

        assertEquals(eventNoteTest.getTopic(), schedule.getAllEvents().get(0).getTopic());
        assertEquals(eventNoteTest.getDetail(), schedule.getAllEvents().get(0).getDetail());
        assertEquals(eventNoteTest.getStartTime(), schedule.getAllEvents().get(0).getStartTime());
        assertEquals(eventNoteTest.getStopTime(), schedule.getAllEvents().get(0).getStopTime());
    }

}