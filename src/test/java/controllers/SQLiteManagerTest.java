package controllers;

import models.EventNote;
import models.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Date;


class SQLiteManagerTest {
    private SQLiteManager manager;

    @BeforeEach
    void setUp() throws Exception {
        manager = SQLiteManager.getInstance();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testLoad(){
        Schedule schedule = manager.loadData();
        Assertions.assertNotNull(schedule);
    }

    @Test
    void testAddNormalEvent(){
//        EventNote eventNote = new EventNote("topic", "detail", new Date(), new Date());

    }

}