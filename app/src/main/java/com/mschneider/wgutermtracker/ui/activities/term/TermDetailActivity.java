package com.mschneider.wgutermtracker.ui.activities.term;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;

public class TermDetailActivity extends AppCompatActivity {
    private TextView termTitleDetailText;
    private TextView termStartDateDetailText;
    private TextView termEndDateDetailText;
    private TextView termIdDetailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        termTitleDetailText = findViewById(R.id.termTitleDetailText);
        termStartDateDetailText = findViewById(R.id.termStartDateDetailText);
        termEndDateDetailText = findViewById(R.id.termEndDateDetailText);


        Button termBackButton = findViewById(R.id.backTermButton);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String startDate = intent.getStringExtra("start_date");
        String endDate = intent.getStringExtra("end_date");
        String termId = intent.getStringExtra("termId");

        termIdDetailText.setText(termId);
        termTitleDetailText.setText(title);
        termStartDateDetailText.setText(startDate);
        termEndDateDetailText.setText(endDate);



        termBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TermsActivity.class);
                startActivity(intent);
            }
        });



    }
}
