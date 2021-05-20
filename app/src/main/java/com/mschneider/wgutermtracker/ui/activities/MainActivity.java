package com.mschneider.wgutermtracker.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.database.AppDatabase;
import com.mschneider.wgutermtracker.models.Term;
import com.mschneider.wgutermtracker.ui.activities.assessment.AssessmentsActivity;
import com.mschneider.wgutermtracker.ui.activities.course.CoursesActivity;
import com.mschneider.wgutermtracker.ui.activities.term.TermsActivity;

public class MainActivity extends AppCompatActivity {


    public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button termsButton, coursesButton, assessmentsButton;

        termsButton = (Button) findViewById(R.id.termsButton);
        coursesButton = (Button) findViewById(R.id.coursesButton);
        assessmentsButton = (Button) findViewById(R.id.assessmentsButton);




        // when upgrading versions, kill the original tables by using fallbackToDestructiveMigration()
        appDatabase = AppDatabase.getDatabaseInstance(getApplicationContext());
        appDatabase.termDao().insertTerm(new Term("Term 1", "05/29/21", "06/02/21"));
        Log.d("Check", "Term Inserted");















        termsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TermsActivity.class);
                startActivity(intent);
            }
        });

        coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
        assessmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AssessmentsActivity.class);
                startActivity(intent);
            }
        });

    }


    // Function to clear app database entries
    private void clearAppDatabases() {
        appDatabase.assessmentDao().deleteAllAssessments();
        appDatabase.courseDao().deleteAllCourses();
        appDatabase.termDao().deleteAllTerms();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

}
