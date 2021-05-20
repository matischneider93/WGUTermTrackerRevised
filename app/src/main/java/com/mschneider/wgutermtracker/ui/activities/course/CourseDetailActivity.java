package com.mschneider.wgutermtracker.ui.activities.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        courseIdDetailText = findViewById(R.id.courseIdDetailText);
        courseTermIdDetailText = findViewById(R.id.courseTermIdDetailText);
        courseTitleDetailText = findViewById(R.id.courseTitleDetailText);
        courseStartDateDetailText = findViewById(R.id.courseStartDateDetailText);
        courseEndDateDetailText = findViewById(R.id.courseEndDateDetailText);
        courseStatusDetailText = findViewById(R.id.courseStatusDetailText);
        courseMentorPhoneDetailText = findViewById(R.id.courseMentorPhoneDetailText);
        courseMentorNameDetailText = findViewById(R.id.patientInsuranceDetailText);
        courseMentorNameDetailText = findViewById(R.id.courseMentorNameDetailText);
        courseMentorEmailDetailText = findViewById(R.id.courseMentorEmailDetailText);
        courseNotesDetailText = findViewById(R.id.courseNotesDetailText);
        Button patientBackButton = findViewById(R.id.patientsBackButton);

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
        courseIdDetailText.setText(courseId);
        courseTitleDetailText.setText(title);
        courseStartDateDetailText.setText(startDate);
        courseEndDateDetailText.setText(endDate);
        courseStatusDetailText.setText(status);
        courseMentorPhoneDetailText.setText(mentorPhone);
        courseMentorNameDetailText.setText(mentorName);
        courseMentorEmailDetailText.setText(mentorEmail);
        courseNotesDetailText.setText(notes);

        patientBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                startActivity(intent);
            }
        });



    }
}
