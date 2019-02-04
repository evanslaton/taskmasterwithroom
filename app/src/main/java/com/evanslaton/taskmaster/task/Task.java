package com.evanslaton.taskmaster.task;

import com.evanslaton.taskmaster.project.Project;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// Creates the relationship between a task and project
// http://androidkt.com/database-relationships/
@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    protected long id;
    protected long projectId; // Id of the project the task belongs to
    protected String title;
    protected String state;

    // Constructor for room
    public Task() {}

    // Constructor
    @Ignore
    public Task(String title, long projectId) {
        this.title = title;
        this.projectId = projectId;
        this.state = "Available";
    }

    // Getters
    // Gets the task id
    public long getId() {
        return this.id;
    }

    // Gets the id of the project the task belongs to
    public long getProjectId() {
        return this.projectId;
    }

    // Gets the task title
    public String getTitle() {
        return this.title;
    }

    // Gets the task state
    public String getState() {
        return this.state;
    }

    // Setters
    // Sets the task id
    public void setId(long id) { this.id = id; }

    // Sets the id of the project the task belongs to (for room DO NOT USE)
    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    // Sets the task title
    public void setTitle(String title) {
        this.title = title;
    }

    // Sets the task state
    public void setState(String state) {
        this.state = state;
    }
}
