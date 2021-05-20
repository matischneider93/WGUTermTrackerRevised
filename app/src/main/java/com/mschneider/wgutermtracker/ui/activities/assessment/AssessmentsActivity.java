package com.mschneider.wgutermtracker.ui.activities.assessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.database.AppDatabase;
import com.mschneider.wgutermtracker.models.Assessment;
import com.mschneider.wgutermtracker.ui.activities.MainActivity;
import com.mschneider.wgutermtracker.ui.adapters.AssessmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class AssessmentsActivity extends AppCompatActivity implements AssessmentAdapter.ViewHolder.OnAssessmentClickListener {
    private RecyclerView assessmentsRecyclerView;
    private List<Assessment> assessmentList = new ArrayList<>();
    private Button assessmentAddButton;
    private Button assessmentEditButton;
    private Button assessmentDetailButton;
    private Button assessmentDeleteButton;
    private int selectedPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments);
        AppDatabase appDatabase = MainActivity.getAppDatabase();
        List<Assessment> assessments = appDatabase.assessmentDao().getAllAssessments();
        for (Assessment assessment : assessments){ assessmentList.add(assessment); }

        assessmentsRecyclerView = findViewById(R.id.assessmentsRecyclerView);
        assessmentsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        assessmentsRecyclerView.setLayoutManager(layoutManager);
        assessmentsRecyclerView.setAdapter(new AssessmentAdapter(assessmentList, this));

        // Button Connections
        assessmentAddButton = findViewById(R.id.assessmentAddButton);
        assessmentEditButton = findViewById(R.id.assessmentUpdateButton);
        assessmentDetailButton = findViewById(R.id.assessmentDetailButton);
        assessmentDeleteButton = findViewById(R.id.assessmentDeleteButton);

        assessmentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AssessmentAddActivity.class);
                startActivity(intent);
            }
        });
        assessmentDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getAppDatabase().assessmentDao().deleteAssessment(selectedPosition);
                assessmentList.remove(selectedPosition);
                assessmentsRecyclerView.getAdapter().notifyDataSetChanged();

            }
        });

        assessmentEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AssessmentEditActivity.class);
                intent.putExtra("assessment_id", assessments.get(selectedPosition).getAssessmentId());
                intent.putExtra("assessment_type", assessments.get(selectedPosition).getAssessmentType());
                intent.putExtra("due_date", assessments.get(selectedPosition).getDueDate());
                intent.putExtra("notes", assessments.get(selectedPosition).getNotes());
                startActivity(intent);
            }
        });

        assessmentDetailButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AssessmentDetailActivity.class);
                intent.putExtra("assessment_id", assessments.get(selectedPosition).getAssessmentId());
                intent.putExtra("assessment_type", assessments.get(selectedPosition).getAssessmentType());
                intent.putExtra("due_date", assessments.get(selectedPosition).getDueDate());
                intent.putExtra("notes", assessments.get(selectedPosition).getNotes());
                startActivity(intent);
            }
        });

    }


    @Override
    public void onAssessmentClick(int position) {
        selectedPosition = position;
    }
}

