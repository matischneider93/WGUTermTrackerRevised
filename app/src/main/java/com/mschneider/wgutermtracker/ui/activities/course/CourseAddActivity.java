package com.mschneider.wgutermtracker.ui.activities.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Course;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;

public class CourseAddActivity extends AppCompatActivity {
    private EditText courseTitleEditText;
    private EditText courseStartDateEditText;
    private EditText courseEndDateEditText;
    private EditText courseStatusEditText;
    private EditText mentorPhoneEditText;
    private EditText mentorNameEditText;
    private EditText mentorEmailEditText;
    private Button addCourseButton; // Add button
    private Button coursesBackButton; // Add button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);
        courseTitleEditText = findViewById(R.id.courseTitleEditText);
        courseStartDateEditText = findViewById(R.id.courseStartDateEditText);
        courseEndDateEditText = findViewById(R.id.courseEndDateEditText);
        courseStatusEditText = findViewById(R.id.courseStatusEditText);
        mentorNameEditText = findViewById(R.id.mentorNameEditText);
        mentorPhoneEditText = findViewById(R.id.mentorPhoneEditText);
        mentorEmailEditText = findViewById(R.id.mentorEmailEditText);
        addCourseButton = findViewById(R.id.addCourseButton);
        coursesBackButton = findViewById(R.id.coursesBackButton); // leads to main patient screen




        // vaccinated status is boolean set default as true
        addCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                startActivity(intent);
            }
        });

        coursesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                startActivity(intent);
            }
        });

    }


}