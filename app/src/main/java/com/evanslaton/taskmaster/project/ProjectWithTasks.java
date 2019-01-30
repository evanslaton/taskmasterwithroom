package com.evanslaton.taskmaster.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.evanslaton.taskmaster.R;
import com.evanslaton.taskmaster.task.Task;
import com.evanslaton.taskmaster.task.TaskAdapter;
import com.evanslaton.taskmaster.task.TaskDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProjectWithTasks extends AppCompatActivity {
    // Variable passed to the activity
    protected long projectId;
    protected String projectTitle;

    // Database variables
    protected TaskDatabase taskDatabase;
    protected List<Task> tasks = new ArrayList<>();

    // Recycler View variables
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_with_tasks);

        // Saves the recycler view
        recyclerView = (RecyclerView) findViewById(R.id.tasks);

        // 'Creates' the database
        taskDatabase = Room.databaseBuilder(getApplicationContext(),
                TaskDatabase.class, "taskDatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        // Gets project id and title from the intent that directed the user to this activity
        // https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
        projectId = Long.parseLong(getIntent().getStringExtra("PROJECT_ID"));
        projectTitle = getIntent().getStringExtra("PROJECT_TITLE");

        // Inserts the project's title at the top of the activity8
        TextView projectLabel = findViewById(R.id.projectWithTaskLabel);
        projectLabel.setText(projectTitle);

        // Gets all the project's tasks from the database
        tasks = taskDatabase.taskDao().getByProjectId(projectId);

        // FOR TESTING ONLY
        if (tasks.size() == 0) {
            tasks.add(new Task("Task1", 1));
            tasks.add(new Task("Task2", 2));
            tasks.add(new Task("Task3", 3));
        }

        // Creates a layout manager and assigns it to the recycler view
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Specifies which adapter the recycler view should use
        adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);
    }

    // Adds a new task to the Task Database
    public void createTask(View v) {
        TextView taskTextView = findViewById(R.id.createTaskTitle);
        String taskTitle = taskTextView.getText().toString();
        taskDatabase.taskDao().insertTask(new Task(taskTitle, this.projectId));
        taskTextView.setText(""); // Empties the input field

        // Updates the recycler view
        tasks = taskDatabase.taskDao().getAll();
        adapter.setTasks(tasks);

        // https://stackoverflow.com/questions/13593069/androidhide-keyboard-after-button-click/13593232 (second answer)
        // Hides the keyboard
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception exception) {
            // Do nothing
        }
    }
}
