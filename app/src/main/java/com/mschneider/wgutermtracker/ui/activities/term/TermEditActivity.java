package com.mschneider.wgutermtracker.ui.activities.term;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Term;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;

public class TermEditActivity extends AppCompatActivity {
    private EditText termTitleEditText;
    private EditText termStartDateEditText;
    private EditText termEndDateEditText;
    private Button termEditButton; // Edit button
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

        termEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (termTitleEditText.getText().toString().isEmpty()){ termTitleEditText.setText(title); }
                if (termStartDateEditText.getText().toString().isEmpty()){ termStartDateEditText.setText(startDate); }
                if (termEndDateEditText.getText().toString().isEmpty()){ termEndDateEditText.setText(endDate); }




                Term newTerm = new Term(Integer.parseInt(termId), termTitleEditText.getText().toString(),termStartDateEditText.getText().toString(), termEndDateEditText.getText().toString());
                MainActivity.getAppDatabase().termDao().updateTerm(newTerm);
                Intent intent = new Intent(getApplicationContext(), TermsActivity.class);
                startActivity(intent);
            }
        });
    }
}
