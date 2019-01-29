package com.evanslaton.taskmaster.project;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.evanslaton.taskmaster.R;

// https://stackoverflow.com/questions/13485918/android-onclick-listener-in-a-separate-class
// https://stackoverflow.com/questions/24471109/recyclerview-onclick
public class ProjectOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(final View view) {
        TextView title = view.findViewById(R.id.projectTitle);
        Log.i("Project Title", title.getText().toString());
    }
}
