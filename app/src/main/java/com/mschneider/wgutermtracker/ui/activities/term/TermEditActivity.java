package com.mschneider.wgutermtracker.ui.activities.term;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Term;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TermEditActivity extends AppCompatActivity {
    private EditText termTitleEditText;
    private EditText termStartDateEditText;
    private EditText termEndDateEditText;
    private Button termEditButton; // Edit button
    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_edit);
        termTitleEditText = findViewById(R.id.termTitleEditText);
        termStartDateEditText = findViewById(R.id.termStartDateEditText);
        termEndDateEditText = findViewById(R.id.termEndDateEditText);
        termEditButton = findViewById(R.id.editTermButton);

        Intent intent = getIntent();
        String termId = intent.getStringExtra("termId");
        String title = intent.getStringExtra("title");
        String startDate = intent.getStringExtra("start_date");
        String endDate = intent.getStringExtra("end_date");

        termTitleEditText.setHint(title);
        termStartDateEditText.setHint(startDate);
        termEndDateEditText.setHint(endDate);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (termStartDateEditText.hasFocus()){
                    updateStartDate();
                } else if (termEndDateEditText.hasFocus()){
                    updateEndDate();
                }
            }
        };


        termStartDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    new DatePickerDialog(TermEditActivity.this, date, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        termEndDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    new DatePickerDialog(TermEditActivity.this, date, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        termEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (termTitleEditText.getText().toString().isEmpty()){ termTitleEditText.setText(title); }
                if (termStartDateEditText.getText().toString().isEmpty()){ termStartDateEditText.setText(startDate); }
                if (termEndDateEditText.getText().toString().isEmpty()){ termEndDateEditText.setText(endDate); }
                Term newTerm = new Term(Long.valueOf(termId), termTitleEditText.getText().toString(),termStartDateEditText.getText().toString(), termEndDateEditText.getText().toString());
                MainActivity.getAppDatabase().termDao().updateTerm(newTerm);
                Intent intent = new Intent(getApplicationContext(), TermsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateStartDate(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        termStartDateEditText.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateEndDate(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        termEndDateEditText.setText(sdf.format(myCalendar.getTime()));
    }
}
