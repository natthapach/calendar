package controllers;

import models.EventNote;
import models.Schedule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class SQLiteManagerTest {
    private SQLiteManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new SQLiteManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLoad(){
        Schedule schedule = manager.loadData();
        assertNotNull(schedule);
    }

    @Test
    public void testAddNormalEvent(){
//        EventNote eventNote = new EventNote("topic", "detail", new Date(), new Date());

    }

}