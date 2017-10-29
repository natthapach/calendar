package common.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventNoteTest {
    private Date startTime;
    private Date stopTime;
    private String topic;
    private String detail;
    private EventNote event;

    @BeforeEach
    public void setUp() throws Exception {
        this.startTime = new Date(1000);
        this.stopTime = new Date(1200);
        this.topic = "event test";
        this.detail = "detail test";
        this.event = new EventNote(0,topic, detail, startTime, stopTime,"daily");
    }

    @AfterEach
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