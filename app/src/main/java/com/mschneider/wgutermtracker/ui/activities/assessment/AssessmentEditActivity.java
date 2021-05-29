package com.mschneider.wgutermtracker.ui.activities.assessment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Assessment;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AssessmentEditActivity extends AppCompatActivity {
    private long assessmentId;
    private long courseId;
    private EditText assessmentTypeEditText;
    private EditText assessmentDueDateEditText;
    private EditText assessmentNotesEditText;
    private Button assessmentEditButton; // Edit button
    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_edit);
        assessmentTypeEditText = findViewById(R.id.assessmentTypeEditText);
        assessmentDueDateEditText = findViewById(R.id.assessmentDueDateEditText);
        assessmentNotesEditText = findViewById(R.id.assessmentNotesEditText);

        assessmentEditButton = findViewById(R.id.editAssessmentButton);

        Intent intent = getIntent();
        assessmentId = intent.getLongExtra("assessmentId",1);
        courseId = intent.getLongExtra("courseId",1);
        String assessmentType = intent.getStringExtra("assessment_type");
        String dueDate = intent.getStringExtra("due_date");
        String notes = intent.getStringExtra("notes");

        assessmentTypeEditText.setHint(assessmentType);
        assessmentDueDateEditText.setHint(dueDate);
        assessmentNotesEditText.setHint(notes);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (assessmentDueDateEditText.hasFocus()){
                    updateDueDate();
                }
            }
        };



        assessmentEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (assessmentTypeEditText.getText().toString().isEmpty()){ assessmentTypeEditText.setText(assessmentType); }
                if (assessmentDueDateEditText.getText().toString().isEmpty()){ assessmentDueDateEditText.setText(dueDate); }
                if (assessmentNotesEditText.getText().toString().isEmpty()){ assessmentNotesEditText.setText(notes); }
                Assessment assessment = new Assessment(assessmentId, courseId, assessmentTypeEditText.getText().toString(), assessmentDueDateEditText.getText().toString(), assessmentNotesEditText.getText().toString());
                MainActivity.getAppDatabase().assessmentDao().insertAssessment(assessment);
                Intent intent = new Intent(getApplicationContext(), AssessmentsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void updateDueDate(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        assessmentDueDateEditText.setText(sdf.format(myCalendar.getTime()));
    }
}
