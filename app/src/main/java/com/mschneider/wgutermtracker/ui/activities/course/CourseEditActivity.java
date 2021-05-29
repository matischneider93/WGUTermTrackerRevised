package com.mschneider.wgutermtracker.ui.activities.course;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Course;
import com.mschneider.wgutermtracker.models.Term;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;
import com.mschneider.wgutermtracker.ui.activities.term.TermEditActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.mschneider.wgutermtracker.ui.activities.MainActivity.appDatabase;

public class CourseEditActivity extends AppCompatActivity {
    private long courseId;
    private int  courseTermId;
    private EditText courseTitleEditText;
    private EditText courseStatusEditText;
    private EditText courseStartDateEditText;
    private EditText courseEndDateEditText;
    private EditText courseMentorNameEditText;
    private EditText courseMentorPhoneEditText;
    private EditText courseMentorEmailEditText;
    private EditText courseNotesEditText;

    private Button courseEditButton; // Edit button
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);
        courseTitleEditText = findViewById(R.id.courseTitleEditText);

        courseStatusEditText = findViewById(R.id.courseStatusEditText);
        courseStartDateEditText = findViewById(R.id.courseStartDateEditText);
        courseEndDateEditText = findViewById(R.id.courseEndDateEditText);
        courseMentorNameEditText = findViewById(R.id.mentorNameEditText);
        courseMentorPhoneEditText = findViewById(R.id.mentorPhoneEditText);
        courseMentorEmailEditText = findViewById(R.id.mentorEmailEditText);
        courseNotesEditText = findViewById(R.id.courseNotesEditText);

        courseEditButton = findViewById(R.id.editCourseButton);


        Spinner spinner = findViewById(R.id.termSpinner);
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
                courseTermId = Integer.parseInt(termId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { courseTermId = 1; }
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

        Intent intent = getIntent();
        courseId = intent.getLongExtra("courseId", 1);
        long termId = intent.getLongExtra("termId", 1);
        String title = intent.getStringExtra("title");
        String status = intent.getStringExtra("status");
        String startDate = intent.getStringExtra("start_date");
        String endDate = intent.getStringExtra("end_date");
        String mentorName = intent.getStringExtra("mentor_name");
        String mentorPhone = intent.getStringExtra("mentor_phone");
        String mentorEmail = intent.getStringExtra("mentor_email");
        String notes = intent.getStringExtra("notes");


        courseTitleEditText.setHint(title);
        courseStatusEditText.setHint(status);
        courseStartDateEditText.setHint(startDate);
        courseEndDateEditText.setHint(endDate);
        courseMentorNameEditText.setHint(mentorName);
        courseMentorPhoneEditText.setHint(mentorPhone);
        courseMentorEmailEditText.setHint(mentorEmail);
        courseNotesEditText.setHint(notes);

        courseStartDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    new DatePickerDialog(CourseEditActivity.this, date, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        courseEndDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    new DatePickerDialog(CourseEditActivity.this, date, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });



        courseEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (courseTitleEditText.getText().toString().isEmpty()){ courseTitleEditText.setText(title); }
                if (courseStartDateEditText.getText().toString().isEmpty()){ courseStartDateEditText.setText(startDate); }
                if (courseStatusEditText.getText().toString().isEmpty()){ courseStatusEditText.setText(status); }
                if (courseEndDateEditText.getText().toString().isEmpty()){ courseEndDateEditText.setText(endDate); }
                if (courseMentorNameEditText.getText().toString().isEmpty()){ courseMentorNameEditText.setText(mentorName); }
                if (courseMentorPhoneEditText.getText().toString().isEmpty()){ courseMentorPhoneEditText.setText(mentorPhone); }
                if (courseMentorEmailEditText.getText().toString().isEmpty()){ courseMentorEmailEditText.setText(mentorEmail); }
                if (courseNotesEditText.getText().toString().isEmpty()){ courseMentorEmailEditText.setText(notes); }

                Course newCourse = new Course(courseId, (long) courseTermId, courseTitleEditText.getText().toString(), courseStartDateEditText.getText().toString(),courseEndDateEditText.getText().toString(), courseStatusEditText.getText().toString(), courseMentorNameEditText.getText().toString(), courseMentorPhoneEditText.getText().toString(), courseMentorEmailEditText.getText().toString(), courseNotesEditText.getText().toString());
                MainActivity.getAppDatabase().courseDao().updateCourse(newCourse);
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
