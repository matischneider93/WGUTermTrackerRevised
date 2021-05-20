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

public class TermAddActivity extends AppCompatActivity {
    private EditText termTitleEditText;
    private EditText termStartDateEditText;
    private EditText termEndDateEditText;
    private Button termAddButton; // Add button
    Calendar myCalendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_add);
        termTitleEditText = findViewById(R.id.termTitleEditText);
        termStartDateEditText = findViewById(R.id.termStartDateEditText);
        termEndDateEditText = findViewById(R.id.termEndDateEditText);
        termAddButton = findViewById(R.id.addTermButton);

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
                    new DatePickerDialog(TermAddActivity.this, date, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        termEndDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    new DatePickerDialog(TermAddActivity.this, date, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        termAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Term term = new Term(termTitleEditText.getText().toString(), termStartDateEditText.getText().toString(), termEndDateEditText.getText().toString());
                MainActivity.getAppDatabase().termDao().insertTerm(term);
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
