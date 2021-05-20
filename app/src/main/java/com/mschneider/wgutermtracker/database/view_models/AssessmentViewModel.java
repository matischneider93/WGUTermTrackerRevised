package com.mschneider.wgutermtracker.database.view_models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.mschneider.wgutermtracker.database.repo.AssessmentRepo;
import com.mschneider.wgutermtracker.models.Assessment;

import java.util.List;

public class AssessmentViewModel extends AndroidViewModel {

    private AssessmentRepo mRepository;

    private List<Assessment> mAllAssessments;

    public AssessmentViewModel(Application application) {
        super(application);
        mRepository = new AssessmentRepo(application);
        mAllAssessments = mRepository.getAllAssessments();
    }

    List<Assessment> getAllAppointments() { return mAllAssessments; }

    public void insert(Assessment assessment) { mRepository.insertAssessment(assessment); }
}