package com.mschneider.wgutermtracker.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mschneider.wgutermtracker.models.Term;

import java.util.List;

@Dao
public interface TermDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Term term);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerms(List<Term> terms);


    @Update
    void updateTerm(Term term);


    @Delete
    void deleteTerms(Term term);

    @Query("DELETE FROM TERMS WHERE termId = :id")
    void deleteById(int id);

    @Query("SELECT * FROM terms WHERE termId = :termId")
    Term getTermById(int termId);

    @Query("SELECT * FROM terms ORDER BY termId ASC")
    List<Term> getAllTerms();

    @Query("DELETE FROM terms")
    int deleteAllTerms();

    @Query("SELECT COUNT(*) FROM terms")
    int getTermCount();
}
