package com.mschneider.wgutermtracker.ui.activities.course;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Course;
import com.mschneider.wgutermtracker.models.Term;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;
import com.mschneider.wgutermtracker.ui.activities.assessment.AssessmentAddActivity;
import com.mschneider.wgutermtracker.ui.activities.term.TermAddActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.mschneider.wgutermtracker.ui.activities.MainActivity.*;
import static com.mschneider.wgutermtracker.ui.activities.MainActivity.appDatabase;

public class CourseAddActivity extends AppCompatActivity {
    private long courseId;
    private long  courseTermId;
    private EditText courseTitleEditText;
    private EditText courseStartDateEditText;
    private EditText courseEndDateEditText;
    private EditText courseStatusEditText;
    private EditText mentorPhoneEditText;
    private EditText mentorNameEditText;
    private EditText mentorEmailEditText;
    private EditText courseNotesEditText;
    private Button addCourseButton; // Add button
    private Button coursesBackButton; // Add button
    Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);
        Spinner spinner = findViewById(R.id.termSpinner);
        courseTitleEditText = findViewById(R.id.courseTitleEditText);
        courseStartDateEditText = findViewById(R.id.courseStartDateEditText);
        courseEndDateEditText = findViewById(R.id.courseEndDateEditText);
        courseStatusEditText = findViewById(R.id.courseStatusEditText);
        mentorNameEditText = findViewById(R.id.mentorNameEditText);
        mentorPhoneEditText = findViewById(R.id.mentorPhoneEditText);
        mentorEmailEditText = findViewById(R.id.mentorEmailEditText);
        courseNotesEditText = findViewById(R.id.courseNotesEditText);
        addCourseButton = findViewById(R.id.addCourseButton);
        coursesBackButton = findViewById(R.id.coursesBackButton); // leads to main patient screen

        Intent intent = getIntent();
        courseId = intent.getLongExtra("courseId", 1);
        Log.d("Check", String.valueOf(courseId));
        List<Term> terms = appDatabase.termDao().getAllTerms();
        List<Term> termsList = new ArrayList<>();
        for (Term term : terms) { termsList.add(term); }
        ArrayList<String> arrayList = new ArrayList<>();
        for (Term term : termsList) { arrayList.add(String.valueOf(term.getTermId())); }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String termId = parent.getItemAtPosition(position).toString();
                courseTermId = Long.parseLong(termId);
            }
;
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                courseTermId = 1;
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (courseStartDateEditText.hasFocus()){
                    updateStartDate();
                } else if (courseEndDateEditText.hasFocus()){
                    updateEndDate();
                }
            }
        };

        courseStartDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    new DatePickerDialog(CourseAddActivity.this, date, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        courseEndDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    new DatePickerDialog(CourseAddActivity.this, date, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });


        // vaccinated status is boolean set default as true
        addCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                Course course = new Course(courseId,courseTermId, courseTitleEditText.getText().toString(), courseStartDateEditText.getText().toString(), courseEndDateEditText.getText().toString(), courseStatusEditText.getText().toString(), mentorNameEditText.getText().toString(),mentorPhoneEditText.getText().toString(), mentorEmailEditText.getText().toString(), courseNotesEditText.getText().toString());
                MainActivity.getAppDatabase().courseDao().insertCourse(course);
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

    private void updateStartDate(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        courseStartDateEditText.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateEndDate(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        courseEndDateEditText.setText(sdf.format(myCalendar.getTime()));
    }


}