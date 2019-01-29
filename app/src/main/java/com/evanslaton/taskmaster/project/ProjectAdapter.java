package com.evanslaton.taskmaster.project;

import androidx.recyclerview.widget.RecyclerView;
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
    private final View.OnClickListener projectOnClickListener = new ProjectOnClickListener();

    // Provides a reference to the views for each Project
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView projectTitle;
        public TextView tasks;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            projectTitle = v.findViewById(R.id.projectTitle);
        }
    }

    // Constructor
    public ProjectAdapter(List<Project> projects) {
        this.projects = projects;
    }

    // Updates the projects list
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
        v.setOnClickListener(projectOnClickListener); // Adds an onClick listener

        // set the view's size, margins, padding and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replaces the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get elements from projects and replaces the contents of the view with the project title
        holder.projectTitle.setText(projects.get(position).title);
    }

    // Returns the size of projects (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return projects.size();
    }
}
