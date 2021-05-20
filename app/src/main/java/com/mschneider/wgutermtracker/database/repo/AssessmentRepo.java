package com.mschneider.wgutermtracker.database.repo;

import android.app.Application;
import android.os.AsyncTask;

import com.mschneider.wgutermtracker.database.AppDatabase;
import com.mschneider.wgutermtracker.database.dao.AssessmentDao;
import com.mschneider.wgutermtracker.models.Assessment;

import java.util.List;

public class AssessmentRepo {
    private AssessmentDao assessmentDao;
    private List<Assessment> allAssessments;


    public AssessmentRepo(Application application){
        AppDatabase appDatabase = (AppDatabase) AppDatabase.getDatabaseInstance(application.getApplicationContext());
        assessmentDao = appDatabase.assessmentDao();
        allAssessments = assessmentDao.getAllAssessments();

    }

    //Insert methods
    public void insertAssessment(Assessment assessment){
        new InsertAssessmentAsyncTask(assessmentDao).execute(assessment);

    }

    //Delete methods
    public void deleteAssessment(Assessment assessment){
        new DeleteAssessmentAsyncTask(assessmentDao).execute(assessment);

    }

    public void deleteAllAssessments(){
        new DeleteAllAssessmentsAsyncTask(assessmentDao).execute();

    }


    //Get methods
    public List<Assessment> getAllAssessments(){
        return allAssessments;
    }

    public Assessment getAssessmentById(int assessmentId){
        return assessmentDao.getAssessmentById(assessmentId);
    }

    private static class InsertAssessmentAsyncTask extends AsyncTask<Assessment, Void, Void> {
        private AssessmentDao assessmentDao;

        private InsertAssessmentAsyncTask(AssessmentDao assessmentDao){
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(Assessment... assessments) {
            assessmentDao.insertAssessments((List<Assessment>) assessments[0]);
            return null;
        }
    }

    private static class DeleteAssessmentAsyncTask extends AsyncTask<Assessment, Void, Void>{
        private AssessmentDao assessmentDao;

        private DeleteAssessmentAsyncTask(AssessmentDao assessmentDao){
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(Assessment... assessments) {
            assessmentDao.deleteAssessment(assessments[0]);
            return null;
        }
    }

    private static class DeleteAllAssessmentsAsyncTask extends AsyncTask<Void, Void, Void>{
        private AssessmentDao assessmentDao;

        private DeleteAllAssessmentsAsyncTask(AssessmentDao assessmentDao){
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            assessmentDao.deleteAllAssessments();
            return null;
        }
    }
}
