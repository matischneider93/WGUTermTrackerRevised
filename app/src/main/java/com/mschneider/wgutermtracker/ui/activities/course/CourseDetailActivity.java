package com.mschneider.wgutermtracker.ui.activities.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Assessment;
import com.mschneider.wgutermtracker.models.Course;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;
import com.mschneider.wgutermtracker.ui.adapters.AssessmentAdapter;
import com.mschneider.wgutermtracker.ui.adapters.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {
    private TextView courseIdDetailText;
    private TextView courseTermIdDetailText;
    private TextView courseTitleDetailText;
    private TextView courseStartDateDetailText;
    private TextView courseEndDateDetailText;
    private TextView courseStatusDetailText;
    private TextView courseMentorNameDetailText;
    private TextView courseMentorPhoneDetailText;
    private TextView courseMentorEmailDetailText;
    private TextView courseNotesDetailText;
    private List<Assessment> assessmentsList = new ArrayList<Assessment>();
    private RecyclerView ascAssessmentRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        courseTermIdDetailText = findViewById(R.id.courseTermIdDetailText);
        courseTitleDetailText = findViewById(R.id.courseTitleDetailText);
        courseStartDateDetailText = findViewById(R.id.courseStartDateDetailText);
        courseEndDateDetailText = findViewById(R.id.courseEndDateDetailText);
        courseStatusDetailText = findViewById(R.id.courseStatusDetailText);
        courseMentorPhoneDetailText = findViewById(R.id.mentorPhoneDetailText);
        courseMentorNameDetailText = findViewById(R.id.mentorNameDetailText);
        courseMentorEmailDetailText = findViewById(R.id.mentorEmailDetailText);
        courseNotesDetailText = findViewById(R.id.courseNotesDetailText);
        ascAssessmentRecyclerView = findViewById(R.id.ascAssessmentRecyclerView);
        Button coursesBackButton = findViewById(R.id.coursesBackButton);




        List<Assessment> assessments = MainActivity.getAppDatabase().assessmentDao().getAllAssessments();
        for (Assessment assessment : assessments){ assessmentsList.add(assessment); }


        ascAssessmentRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        ascAssessmentRecyclerView.setLayoutManager(layoutManager);
        ascAssessmentRecyclerView.setAdapter(new AssessmentAdapter(assessmentsList));

        Intent intent = getIntent();
        String courseId = intent.getStringExtra("courseId");
        String termId = intent.getStringExtra("termId");
        String title = intent.getStringExtra("title");
        String startDate = intent.getStringExtra("start_date");
        String endDate = intent.getStringExtra("end_date");
        String status = intent.getStringExtra("status");
        String mentorPhone = intent.getStringExtra("mentor_phone");
        String mentorName = intent.getStringExtra("mentor_name");
        String mentorEmail = intent.getStringExtra("mentor_email");
        String notes = intent.getStringExtra("notes");

        courseTermIdDetailText.setText(termId);
        courseTitleDetailText.setText(title);
        courseStartDateDetailText.setText(startDate);
        courseEndDateDetailText.setText(endDate);
        courseStatusDetailText.setText(status);
        courseMentorPhoneDetailText.setText(mentorPhone);
        courseMentorNameDetailText.setText(mentorName);
        courseMentorEmailDetailText.setText(mentorEmail);
        courseNotesDetailText.setText(notes);

        coursesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                startActivity(intent);
            }
        });



    }
}
