package com.evanslaton.taskmaster.task;

import com.evanslaton.taskmaster.project.Project;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;

// https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
// http://androidkt.com/database-relationships/
//@Entity(foreignKeys = @ForeignKey(entity = Project.class,
//        parentColumns = "id",
//        childColumns = "projectId",
//        onDelete = CASCADE))

public class Task {
    @PrimaryKey(autoGenerate = true)
    protected long id;
    protected long projectId;
    protected String title;
    protected String state;

    public Task(String title) {
        this.title = title;
        this.state = "Available";
    }
}
