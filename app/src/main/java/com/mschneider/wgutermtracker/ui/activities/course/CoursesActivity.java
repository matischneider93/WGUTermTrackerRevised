package com.mschneider.wgutermtracker.ui.activities.course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.database.AppDatabase;
import com.mschneider.wgutermtracker.models.Course;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;
import com.mschneider.wgutermtracker.ui.adapters.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CoursesActivity extends AppCompatActivity implements CourseAdapter.ViewHolder.OnCourseClickListener {
    private RecyclerView coursesRecyclerView;
    private List<Course> coursesList = new ArrayList<>();
    private Button courseAddButton;
    private Button courseEditButton;
    private Button courseDetailButton;
    private Button courseDeleteButton;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        AppDatabase appDatabase = MainActivity.getAppDatabase();
        List<Course> courses = appDatabase.courseDao().getAllCourses();
        for (Course course : courses){ coursesList.add(course); }

        coursesRecyclerView = findViewById(R.id.coursesRecyclerView);
        coursesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        coursesRecyclerView.setLayoutManager(layoutManager);
        coursesRecyclerView.setAdapter(new CourseAdapter(coursesList, this));

        // Button Connections
        courseAddButton = findViewById(R.id.courseAddButton);
        courseEditButton = findViewById(R.id.courseUpdateButton);
        courseDetailButton = findViewById(R.id.courseDetailButton);
        courseDeleteButton = findViewById(R.id.courseDeleteButton);

        courseAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CourseAddActivity.class);
                startActivity(intent);
            }
        });
        courseDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getAppDatabase().courseDao().deleteById(selectedPosition);
                coursesList.remove(selectedPosition);
                coursesRecyclerView.getAdapter().notifyDataSetChanged();

            }
        });

        courseEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CourseEditActivity.class);
                intent.putExtra("courseId", courses.get(selectedPosition).getCourseId());
                intent.putExtra("termId", courses.get(selectedPosition).getTermId());
                intent.putExtra("title", courses.get(selectedPosition).getTitle());
                intent.putExtra("status", courses.get(selectedPosition).getStatus());
                intent.putExtra("start_date", courses.get(selectedPosition).getStartDate());
                intent.putExtra("end_date", courses.get(selectedPosition).getEndDate());
                intent.putExtra("mentor_name", courses.get(selectedPosition).getMentorName());
                intent.putExtra("mentor_phone", courses.get(selectedPosition).getMentorPhone());
                intent.putExtra("mentor_email", courses.get(selectedPosition).getMentor_email());
                startActivity(intent);
            }
        });

        courseDetailButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CourseDetailActivity.class);
                intent.putExtra("courseId", courses.get(selectedPosition).getCourseId());
                intent.putExtra("termId", courses.get(selectedPosition).getTermId());
                intent.putExtra("title", courses.get(selectedPosition).getTitle());
                intent.putExtra("status", courses.get(selectedPosition).getStatus());
                intent.putExtra("start_date", courses.get(selectedPosition).getStartDate());
                intent.putExtra("end_date", courses.get(selectedPosition).getEndDate());
                intent.putExtra("mentor_name", courses.get(selectedPosition).getMentorName());
                intent.putExtra("mentor_phone", courses.get(selectedPosition).getMentorPhone());
                intent.putExtra("mentor_email", courses.get(selectedPosition).getMentor_email());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCourseClick(int position) {
        selectedPosition = position;
    }
}