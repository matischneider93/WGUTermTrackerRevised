package com.mschneider.wgutermtracker.ui.activities.assessment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Course;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;

public class AssessmentDetailActivity extends AppCompatActivity {
    private TextView courseTitleDetailText;
    private TextView assessmentTypeDetailText;
    private TextView assessmentDueDateDetailText;

    private TextView assessmentNotesDetailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        courseTitleDetailText = findViewById(R.id.courseTitleText);
        assessmentTypeDetailText = findViewById(R.id.assessmentTypeDetailText);
        assessmentDueDateDetailText = findViewById(R.id.assessmentDueDateDetailText);
        assessmentNotesDetailText = findViewById(R.id.assessmentNotesDetailText);
        Button assessmentsBackButton = findViewById(R.id.backAssessmentButton);
        Button emailAssessmentNotesButton = findViewById(R.id.emailAssessmentNotesButton);

        Intent intent = getIntent();
        String courseId = intent.getStringExtra("course_id");
        String assessmentType = intent.getStringExtra("assessment_type");
        String assessmentDueDate = intent.getStringExtra("due_date");
        String assessmentNotes = intent.getStringExtra("notes");
        courseTitleDetailText.setText(courseId);
        assessmentTypeDetailText.setText(assessmentType);
        assessmentDueDateDetailText.setText(assessmentDueDate);
        assessmentNotesDetailText.setText(assessmentNotes);

        emailAssessmentNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailto = "mailto:useremail@gmail.com" +
                        "?cc=" +
                        "&subject=" + Uri.encode("Assessment Notes") +
                        "&body=" + Uri.encode(assessmentNotes);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Error opening email app", Toast.LENGTH_SHORT).show();
                }
            }
        });

        assessmentsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AssessmentsActivity.class);
                startActivity(intent);
            }
        });



    }
}
