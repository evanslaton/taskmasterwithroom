package com.evanslaton.taskmaster.project;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evanslaton.taskmaster.R;

import java.util.List;

// http://www.vogella.com/tutorials/AndroidRecyclerView/article.html
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<Project> projects;

    // https://stackoverflow.com/questions/24471109/recyclerview-onclick
//    private final View.OnClickListener projectOnClickListener = new ProjectOnClickListener();

    // Provides a reference to the views for each Project
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView projectId;
        public TextView projectTitle;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            projectId = v.findViewById(R.id.projectId);
            projectTitle = v.findViewById(R.id.projectTitle);
        }
    }

    // Constructor
    public ProjectAdapter(List<Project> projects) {
        this.projects = projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
        this.notifyDataSetChanged();
    }

    // Create a new view (invoked by the layout manager)
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.project_view, parent, false);

        // Adds an onClick listener
        // https://stackoverflow.com/questions/13485918/android-onclick-listener-in-a-separate-class
        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        TextView title = view.findViewById(R.id.projectTitle);
                        TextView id = view.findViewById(R.id.projectId);
                        Log.i("Project Title", id.getText().toString() + " " + title.getText().toString());
                        goToProject(view);
                    }
                });

        // set the view's size, margins, padding and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replaces the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Replaces the contents of the view with the project id and title
        holder.projectId.setText(String.valueOf(projects.get(position).getId()));
        holder.projectTitle.setText(projects.get(position).title);
    }

    // Returns the size of projects (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return projects.size();
    }

    // Takes the user to the ProjectWithTasks activity
    // https://stackoverflow.com/questions/4298225/how-can-i-start-an-activity-from-a-non-activity-class
    public void goToProject(View v) {
        Intent goToProjectWithTasksIntent = new Intent(v.getContext(), ProjectWithTasks.class);
        v.getContext().startActivity(goToProjectWithTasksIntent);
    }
}
