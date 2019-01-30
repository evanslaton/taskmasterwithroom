package com.evanslaton.taskmaster.project;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Project {
    @PrimaryKey(autoGenerate = true)
    protected long id;
    protected String title;

    // Constructor for room
    public Project() {}

    // Constructor
    @Ignore
    public Project(String title) {
        this.title = title;
    }

    // Gets the project id
    public long getId() {
        return this.id;
    }

    // Gets the project title
    public String getTitle() {
        return this.title;
    }

    // Setters
    // Sets the project id
    public void setId(long id) { this.id = id; }

    // Sets the project title
    public void setTitle(String title) { this.title = title; }
}
