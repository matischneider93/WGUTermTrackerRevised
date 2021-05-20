package com.mschneider.wgutermtracker.ui.activities.assessment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Assessment;

import java.util.Calendar;
import java.util.Locale;

public class AssessmentAddActivity extends AppCompatActivity {
    private EditText assessmentCourseIdEditText;
    private EditText assessmentTypeEditText;
    private EditText assessmentDueDateEditText;
    private EditText assessmentNotesEditText;
    private Button assessmentAddButton; // Add button
    private Button assessmentsBackButton; // Add button
    Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_add);
        assessmentCourseIdEditText = findViewById(R.id.assessmentCourseIdUpdateText);
        assessmentTypeEditText = findViewById(R.id.assessmentTypeEditText);
        assessmentDueDateEditText = findViewById(R.id.assessmentDueDateEditText);
        assessmentNotesEditText = findViewById(R.id.assessmentCourseIdUpdateText);
        assessmentAddButton = findViewById(R.id.addAssessmentButton);
        assessmentsBackButton = findViewById(R.id.backAssessmentButton); // leads to main assessment screen


        // Date and Time Mechanisms here
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };



        assessmentDueDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    new DatePickerDialog(AssessmentAddActivity.this, date, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });




        // vaccinated status is boolean set default as true
        assessmentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Assessment assessment = new Assessment(1, assessmentTypeEditText.getText().toString(), assessmentDueDateEditText.getText().toString(), assessmentNotesEditText.getText().toString());
                Intent intent = new Intent(getApplicationContext(), AssessmentsActivity.class);
                startActivity(intent);
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


    private void updateLabel(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        assessmentDueDateEditText.setText(sdf.format(myCalendar.getTime()));
    }

}