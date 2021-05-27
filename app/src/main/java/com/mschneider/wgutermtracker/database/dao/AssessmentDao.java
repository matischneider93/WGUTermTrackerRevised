package com.mschneider.wgutermtracker.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mschneider.wgutermtracker.models.Assessment;
import com.mschneider.wgutermtracker.models.Course;

import java.util.List;

@Dao
public interface AssessmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssessment(Assessment assessment);

    @Update
    void updateAssessment(Assessment assessment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssessments(List<Assessment> assessments);

    @Delete
    void deleteAssessment(Assessment assessment);

    @Query("SELECT * FROM ASSESSMENTS WHERE  assessment_id =:assessmentId")
    Assessment getAssessmentById(Long assessmentId);

    @Query("SELECT * FROM assessments ORDER BY assessment_id ASC")
    List<Assessment> getAllAssessments();

    @Query("SELECT * FROM courses WHERE courseId = :courseId")
    List<Course> getAssessmentsByCourseId(Long courseId);

    @Query("DELETE FROM assessments")
    int deleteAllAssessments();

    @Query("SELECT COUNT(*) FROM assessments")
    int getAssessmentsCount();

    @Query("DELETE FROM assessments WHERE  assessment_id =:selectedPosition")
    void deleteAssessment(int selectedPosition);





}
