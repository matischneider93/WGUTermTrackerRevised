package com.mschneider.wgutermtracker.ui.activities.assessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;

public class AssessmentDetailActivity extends AppCompatActivity {
    private TextView appointmentTitleDetailText;
    private TextView assessmentTypeDetailText;
    private TextView assessmentDueDateDetailText;

    private TextView assessmentNotesDetailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        assessmentTypeDetailText = findViewById(R.id.assessmentTypeDetailText);
        assessmentDueDateDetailText = findViewById(R.id.assessmentDueDateDetailText);
        assessmentNotesDetailText = findViewById(R.id.assessmentNotesDetailText);

        Button assessmentsBackButton = findViewById(R.id.backAssessmentButton);

        Intent intent = getIntent();

        String assessmentType = intent.getStringExtra("assessment_type");
        String assessmentDueDate = intent.getStringExtra("due_date");
        String assessmentNotes = intent.getStringExtra("notes");



        assessmentTypeDetailText.setText(assessmentType);
        assessmentDueDateDetailText.setText(assessmentDueDate);
        assessmentNotesDetailText.setText(assessmentNotes);

        assessmentsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AssessmentsActivity.class);
                startActivity(intent);
            }
        });



    }
}
