package com.mschneider.wgutermtracker.ui.activities.assessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;

public class AssessmentEditActivity extends AppCompatActivity {
    private EditText assessmentTypeEditText;

    private EditText assessmentDueDateEditText;
    private EditText assessmentNotesEditText;


    private Button assessmentEditButton; // Edit button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_edit);
        assessmentTypeEditText = findViewById(R.id.assessmentTypeEditText);
        assessmentDueDateEditText = findViewById(R.id.assessmentDueDateEditText);
        assessmentNotesEditText = findViewById(R.id.assessmentNotesEditText);

        assessmentEditButton = findViewById(R.id.editAssessmentButton);

        Intent intent = getIntent();

        String assessmentType = intent.getStringExtra("assessment_type");
        String dueDate = intent.getStringExtra("due_date");
        String notes = intent.getStringExtra("notes");

        assessmentTypeEditText.setHint(assessmentType);
        assessmentDueDateEditText.setHint(dueDate);
        assessmentNotesEditText.setHint(notes);



        assessmentEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (assessmentTypeEditText.getText().toString().isEmpty()){ assessmentTypeEditText.setText(assessmentType); }
                if (assessmentDueDateEditText.getText().toString().isEmpty()){ assessmentDueDateEditText.setText(dueDate); }
                if (assessmentNotesEditText.getText().toString().isEmpty()){ assessmentNotesEditText.setText(notes); }

                Intent intent = new Intent(getApplicationContext(), AssessmentsActivity.class);
                startActivity(intent);
            }
        });
    }
}
