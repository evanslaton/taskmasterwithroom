package com.evanslaton.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.evanslaton.taskmaster.project.Project;
import com.evanslaton.taskmaster.project.ProjectAdapter;
import com.evanslaton.taskmaster.project.ProjectDatabase;

import java.util.ArrayList;
import java.util.List;

// From Google's Android docs and http://www.vogella.com/tutorials/AndroidRecyclerView/article.html
public class MainActivity extends AppCompatActivity {
    protected ProjectDatabase projectDatabase;
    protected List<Project> projects = new ArrayList<>();

    // Recycler View variables
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Saves the recycler view
        recyclerView = (RecyclerView) findViewById(R.id.projects);

        // 'Creates' the database
        projectDatabase = Room.databaseBuilder(getApplicationContext(),
                ProjectDatabase.class, "projectDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        // Gets all projects from the database
        projects = projectDatabase.projectDao().getAll();

        if (projects.size() == 0) {
            projects.add(new Project("DoTheNeedful"));
            projects.add(new Project("DoTheNeedful2"));
            projects.add(new Project("DoTheNeedful3"));
            projects.add(new Project("DoTheNeedful4"));
            projects.add(new Project("DoTheNeedful5"));
            projects.add(new Project("DoTheNeedful6"));
            projects.add(new Project("DoTheNeedful7"));
        }

        // Creates a layout manager and assigns it to the recycler view
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Specifies which adapter the recycler view should use
        adapter = new ProjectAdapter(projects);
        recyclerView.setAdapter(adapter);
    }
}
