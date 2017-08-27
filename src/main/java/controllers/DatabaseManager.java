package controllers;

import models.Schedule;

public interface DatabaseManager {
    Schedule loadData();
    void writeData(Schedule schedule);
}

