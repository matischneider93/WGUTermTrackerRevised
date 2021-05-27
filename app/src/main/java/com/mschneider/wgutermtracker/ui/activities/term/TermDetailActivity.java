package com.mschneider.wgutermtracker.ui.activities.term;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Course;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;
import com.mschneider.wgutermtracker.ui.adapters.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

public class TermDetailActivity extends AppCompatActivity {
    private TextView termTitleDetailText;
    private TextView termStartDateDetailText;
    private TextView termEndDateDetailText;
    private List<Course> coursesList = new ArrayList<Course>();
    private RecyclerView ascCoursesRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        termTitleDetailText = findViewById(R.id.termTitleDetailText);
        termStartDateDetailText = findViewById(R.id.termStartDateDetailText);
        termEndDateDetailText = findViewById(R.id.termEndDateDetailText);
        ascCoursesRecyclerView = findViewById(R.id.coursesRecyclerView);


        Button termBackButton = findViewById(R.id.backTermButton);
        ascCoursesRecyclerView = findViewById(R.id.associatedCoursesRecyclerView);


        List<Course> courses = MainActivity.getAppDatabase().courseDao().getAllCourses();
        if (courses.isEmpty()){
            Log.d("Check", "Has No Courses");
            ascCoursesRecyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            ascCoursesRecyclerView.setLayoutManager(layoutManager);
            ascCoursesRecyclerView.setAdapter(new CourseAdapter(coursesList));
        } else {
            Log.d("Check", "Has Courses");
            for (Course course : courses){ coursesList.add(course); }
            ascCoursesRecyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            ascCoursesRecyclerView.setLayoutManager(layoutManager);
            ascCoursesRecyclerView.setAdapter(new CourseAdapter(coursesList));
        }

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String startDate = intent.getStringExtra("start_date");
        String endDate = intent.getStringExtra("end_date");
        String termId = intent.getStringExtra("termId");


        termTitleDetailText.setText(title);
        termStartDateDetailText.setText(startDate);
        termEndDateDetailText.setText(endDate);

        termBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TermsActivity.class);
                startActivity(intent);
            }
        });

    }
}
