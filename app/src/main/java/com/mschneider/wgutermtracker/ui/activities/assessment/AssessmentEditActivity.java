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
    private EditText assessmentCourseIdEditText;

    private Button assessmentEditButton; // Edit button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_edit);
        assessmentTypeEditText = findViewById(R.id.assessmentTypeEditText);
        assessmentDueDateEditText = findViewById(R.id.assessmentDueDateEditText);
        assessmentNotesEditText = findViewById(R.id.appointmentDateEditText);
        assessmentCourseIdEditText = findViewById(R.id.appointmentTimeEditText);
        assessmentEditButton = findViewById(R.id.editAssessmentButton);

        Intent intent = getIntent();
        String appointmentTitle = intent.getStringExtra("appointment_title");
        String assessmentType = intent.getStringExtra("assessment_type");
        String appointmentDate = intent.getStringExtra("appointment_date");
        String appointmentTime = intent.getStringExtra("appointment_time");

        assessmentTypeEditText.setHint(appointmentTitle);
        assessmentDueDateEditText.setHint(assessmentType);
        assessmentNotesEditText.setHint(appointmentDate);
        assessmentCourseIdEditText.setText(appointmentTime);


        assessmentEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (assessmentTypeEditText.getText().toString().isEmpty()){ assessmentTypeEditText.setText(appointmentTitle); }
                if (assessmentDueDateEditText.getText().toString().isEmpty()){ assessmentDueDateEditText.setText(assessmentType); }
                if (assessmentNotesEditText.getText().toString().isEmpty()){ assessmentNotesEditText.setText(appointmentDate); }
                if (assessmentCourseIdEditText.getText().toString().isEmpty()){ assessmentCourseIdEditText.setText(appointmentTime); }
                /*
                Assessment updatedAssessment = new Assessment(1, appointmentTitleEditText.getText().toString(),
                        appointmentTypeEditText.getText().toString(),
                        appointmentDateEditText.getText().toString(),
                        appointmentTimeEditText.getText().toString()
                        );
                        MainActivity.getAppDatabase().assessmentDao().updateAssessment(updatedAssessment);
                 */

                Intent intent = new Intent(getApplicationContext(), AssessmentsActivity.class);
                startActivity(intent);
            }
        });
    }
}
