package controllers;


import org.junit.Before;
import org.junit.Test;

class SQLiteManagerTest {
    private SQLiteManager manager;

    @Before
    public void setup(){
        manager = SQLiteManager.getInstance();
    }

    @Test
    public void testLoad(){
        manager.loadData();
    }

}