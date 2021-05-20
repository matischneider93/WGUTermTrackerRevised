package com.mschneider.wgutermtracker.database.repo;

import android.app.Application;
import android.os.AsyncTask;

import com.mschneider.wgutermtracker.database.AppDatabase;
import com.mschneider.wgutermtracker.database.dao.TermDao;
import com.mschneider.wgutermtracker.models.Term;

import java.util.List;

public class TermRepo {
    private TermDao termDao;
    private List<Term> allTerms;


    public TermRepo(Application application){
        AppDatabase appDatabase = (AppDatabase) AppDatabase.getDatabaseInstance(application.getApplicationContext());
        termDao = appDatabase.termDao();
        allTerms = termDao.getAllTerms();

    }

    //Insert methods
    public void insertTerm(Term term){
        new InsertTermAsyncTask(termDao).execute(term);

    }

    //Delete methods
    public void deleteTerm(Term term){
        new DeleteTermAsyncTask(termDao).execute(term);

    }

    public void deleteAllTerms(){
        new DeleteAllTermsAsyncTask(termDao).execute();

    }


    //Get methods
    public List<Term> getAllTerms(){
        return allTerms;
    }

    public Term getTermById(int termId){
        return termDao.getTermById(termId);
    }

    private static class InsertTermAsyncTask extends AsyncTask<Term, Void, Void> {
        private TermDao termDao;

        private InsertTermAsyncTask(TermDao termDao){
            this.termDao = termDao;
        }

        @Override
        protected Void doInBackground(Term... terms) {
            termDao.insertTerms((List<Term>) terms[0]);
            return null;
        }
    }

    private static class DeleteTermAsyncTask extends AsyncTask<Term, Void, Void>{
        private TermDao termDao;

        private DeleteTermAsyncTask(TermDao termDao){
            this.termDao = termDao;
        }

        @Override
        protected Void doInBackground(Term... terms) {
            termDao.deleteTerms(terms[0]);
            return null;
        }
    }

    private static class DeleteAllTermsAsyncTask extends AsyncTask<Void, Void, Void>{
        private TermDao termDao;

        private DeleteAllTermsAsyncTask(TermDao termDao){
            this.termDao = termDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            termDao.deleteAllTerms();
            return null;
        }
    }
}
