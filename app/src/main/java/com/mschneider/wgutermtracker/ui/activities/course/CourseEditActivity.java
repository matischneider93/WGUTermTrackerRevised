package com.mschneider.wgutermtracker.ui.activities.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;

public class CourseEditActivity extends AppCompatActivity {
    private EditText courseIdEditText;
    private EditText courseTermIdEditText;
    private EditText courseTitleEditText;
    private EditText courseStatusEditText;
    private EditText courseStartDateEditText;
    private EditText courseEndDateEditText;
    private EditText courseMentorNameEditText;
    private EditText courseMentorPhoneEditText;
    private EditText courseMentorEmailEditText;
    private EditText courseNotesEditText;

    private Button courseEditButton; // Edit button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);
        courseTitleEditText = findViewById(R.id.courseTitleEditText);
        courseIdEditText = findViewById(R.id.courseIdEditText);
        courseTermIdEditText = findViewById(R.id.courseTermIdEditText);
        courseStatusEditText = findViewById(R.id.courseStatusEditText);
        courseStartDateEditText = findViewById(R.id.courseStartDateEditText);
        courseEndDateEditText = findViewById(R.id.courseEndDateEditText);
        courseMentorNameEditText = findViewById(R.id.courseMentorNameEditText);
        courseMentorPhoneEditText = findViewById(R.id.courseMentorPhoneEditText);
        courseMentorEmailEditText = findViewById(R.id.courseMentorEmailEditText);
        courseNotesEditText = findViewById(R.id.courseNotesEditText);

        courseEditButton = findViewById(R.id.courseEditButton);

        Intent intent = getIntent();
        String termId = intent.getStringExtra("termId");
        String courseId = intent.getStringExtra("courseId");
        String title = intent.getStringExtra("title");
        String status = intent.getStringExtra("status");
        String startDate = intent.getStringExtra("start_date");
        String endDate = intent.getStringExtra("end_date");
        String mentorName = intent.getStringExtra("mentor_name");
        String mentorPhone = intent.getStringExtra("mentor_phone");
        String mentorEmail = intent.getStringExtra("mentor_email");
        String notes = intent.getStringExtra("notes");


        courseTermIdEditText.setHint(termId);
        courseIdEditText.setHint(courseId);
        courseTitleEditText.setHint(title);
        courseStatusEditText.setHint(status);
        courseStartDateEditText.setHint(startDate);
        courseEndDateEditText.setHint(endDate);
        courseMentorNameEditText.setHint(mentorName);
        courseMentorPhoneEditText.setHint(mentorPhone);
        courseMentorEmailEditText.setHint(mentorEmail);
        courseNotesEditText.setHint(notes);



        courseEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (courseIdEditText.getText().toString().isEmpty()){ courseIdEditText.setText(courseId); }
                if (courseTermIdEditText.getText().toString().isEmpty()){ courseTermIdEditText.setText(termId); }
                if (courseTitleEditText.getText().toString().isEmpty()){ courseTitleEditText.setText(title); }
                if (courseTermIdEditText.getText().toString().isEmpty()){ courseTermIdEditText.setText(termId); }
                if (courseStartDateEditText.getText().toString().isEmpty()){ courseStartDateEditText.setText(startDate); }
                if (courseStatusEditText.getText().toString().isEmpty()){ courseStatusEditText.setText(status); }
                if (courseEndDateEditText.getText().toString().isEmpty()){ courseEndDateEditText.setText(endDate); }
                if (courseMentorNameEditText.getText().toString().isEmpty()){ courseMentorNameEditText.setText(mentorName); }
                if (courseMentorPhoneEditText.getText().toString().isEmpty()){ courseMentorPhoneEditText.setText(mentorPhone); }
                if (courseMentorEmailEditText.getText().toString().isEmpty()){ courseMentorEmailEditText.setText(mentorEmail); }
                if (courseNotesEditText.getText().toString().isEmpty()){ courseMentorEmailEditText.setText(notes); }



                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                startActivity(intent);
            }
        });
    }
}
