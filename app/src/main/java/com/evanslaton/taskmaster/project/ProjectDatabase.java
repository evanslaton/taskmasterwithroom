package com.evanslaton.taskmaster.project;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Project.class}, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {
    abstract public ProjectDao projectDao();
}
