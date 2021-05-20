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
    private TextView appointmentTimeDetailText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        appointmentTitleDetailText = findViewById(R.id.appointmentTitleDetailText);
        assessmentTypeDetailText = findViewById(R.id.appointmentTypeDetailText);
        assessmentDueDateDetailText = findViewById(R.id.appointmentDateDetailText);
        appointmentTimeDetailText = findViewById(R.id.appointmentTimeDetailText);

        Button appointmentsBackButton = findViewById(R.id.backAppointmentsButton);

        Intent intent = getIntent();
        String appointmentTitle = intent.getStringExtra("appointment_title");
        String assessmentType = intent.getStringExtra("assessment_type");
        String appointmentDate = intent.getStringExtra("appointment_date");
        String appointmentTime = intent.getStringExtra("appointment_time");


        appointmentTitleDetailText.setText(appointmentTitle);
        assessmentTypeDetailText.setText(assessmentType);
        assessmentDueDateDetailText.setText(appointmentDate);
        appointmentTimeDetailText.setText(appointmentTime);

        appointmentsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AssessmentsActivity.class);
                startActivity(intent);
            }
        });



    }
}
