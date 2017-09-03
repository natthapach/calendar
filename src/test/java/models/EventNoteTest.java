package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventNoteTest {
    private Date startTime;
    private Date stopTime;
    private String topic;
    private String detail;
    private EventNote event;

    @Before
    public void setUp() throws Exception {
        this.startTime = new Date(1000);
        this.stopTime = new Date(1200);
        this.topic = "event test";
        this.detail = "detail test";
        this.event = new EventNote(topic, detail, startTime, stopTime);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTopic(){
        assertEquals(this.topic, this.event.getTopic());
    }


    @Test
    public void testGetDetail(){
        assertEquals(detail, event.getDetail());
    }

    @Test
    public void testGetStartTime(){
        assertEquals(startTime, event.getStartTime());
    }

    @Test
    public void testGetStopTime(){
        assertEquals(stopTime, event.getStopTime());
    }

}