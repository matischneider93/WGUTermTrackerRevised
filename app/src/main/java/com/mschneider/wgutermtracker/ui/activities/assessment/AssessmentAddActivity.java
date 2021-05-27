package com.mschneider.wgutermtracker.ui.activities.assessment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Assessment;
import com.mschneider.wgutermtracker.models.Course;
import com.mschneider.wgutermtracker.models.Term;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.mschneider.wgutermtracker.ui.activities.MainActivity.appDatabase;

public class AssessmentAddActivity extends AppCompatActivity {
    private Long  courseId = 1L;
    private EditText assessmentTypeEditText;
    private EditText assessmentDueDateEditText;
    private EditText assessmentNotesEditText;
    private Button assessmentAddButton; // Add button
    private Button assessmentsBackButton; // Add button
    private List<Course> courseList = new ArrayList<>();
    Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_add);
        Spinner spinner = findViewById(R.id.courseSpinner);
        List<Course> courses = appDatabase.courseDao().getAllCourses();
        for (Course course : courses) {
            courseList.add(course);
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (Course course : courseList) {
            arrayList.add(String.valueOf(course.getCourseId()));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String termId = parent.getItemAtPosition(position).toString();
                courseId = Long.valueOf(termId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                courseId = null;
            }
        });

        assessmentTypeEditText = findViewById(R.id.assessmentTypeEditText);
        assessmentDueDateEditText = findViewById(R.id.assessmentDueDateEditText);
        assessmentNotesEditText = findViewById(R.id.assessmentNotesEditText);
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
                Assessment assessment = new Assessment(courseId, assessmentTypeEditText.getText().toString(), assessmentDueDateEditText.getText().toString(), assessmentNotesEditText.getText().toString());
                appDatabase.assessmentDao().insertAssessment(assessment);
                Log.i("Check", "Assessment inserted");
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