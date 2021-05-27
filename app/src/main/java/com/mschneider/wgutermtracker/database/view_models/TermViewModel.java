package com.mschneider.wgutermtracker.database.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.mschneider.wgutermtracker.database.repo.TermRepo;
import com.mschneider.wgutermtracker.models.Term;

import java.util.List;

public class TermViewModel extends AndroidViewModel {
    private TermRepo mRepository;
    public List<Term> mAllTerms;




    public TermViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TermRepo(application);
        mAllTerms = mRepository.getAllTerms();
    }


    public List<Term> getAllTerms() { return mAllTerms; }

    public void insert(Term term) { mRepository.insertTerm(term); }


}