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

        ArrayList<EventNote> events = new ArrayList<>();
        for (int i=0; i<10; i++){
            Date sdate = new Date(1000 + i*100);
            Date edate = new Date(1000 + i*101);
            events.add(new EventNote(i, "event "+i, "detail", sdate, edate, Schedule.DAILY));
        }
        schedule = new Schedule(events);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testAddOneEvent(){
        Date date = new Date();
        EventNote eventNote = new EventNote(11,"new event", "detail", date, date, Schedule.DAILY);
        schedule.addEvent(eventNote);

        assertEquals(11, schedule.getAllEvents().size());
    }

    @Test
    void testAddTwoEvents(){
        Date date = new Date();
        EventNote eventNote1 = new EventNote(11, "new event 1", "detail", date, date, Schedule.DAILY);
        EventNote eventNote2 = new EventNote(12, "new event 2", "detail", date, date, Schedule.DAILY);

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
        EventNote eventNote = new EventNote(11,"event 1", "detail", new Date(), new Date(), Schedule.DAILY);
        schedule.delete(eventNote);

        assertEquals(10, schedule.getAllEvents().size());
    }

    @Test
    void testUpdateEvent(){
        EventNote eventNoteTest = new EventNote(0,"event test", "detail test", new Date(0, 0, 0), new Date(1, 1, 1), Schedule.DAILY);
        EventNote eventNote = schedule.getAllEvents().get(0);

        schedule.update(eventNote, eventNoteTest);

        assertNotEquals(-1, schedule.getAllEvents().indexOf(eventNoteTest));
    }

}