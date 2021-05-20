package com.mschneider.wgutermtracker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessment {
    @ColumnInfo(name = "assessment_id")
    @PrimaryKey(autoGenerate = true)
    private int assessmentId;
    @ColumnInfo(name = "course_Id")
    private int courseId;
    @ColumnInfo(name = "assessment_type")
    private String assessmentType;
    @ColumnInfo(name = "due_date")
    private String dueDate;
    @ColumnInfo(name = "notes")
    private String notes;



    @Ignore
    public Assessment(int assessmentId, int courseId, String assessmentType, String dueDate, String notes) {
        this.assessmentId = assessmentId;
        this.courseId = courseId;
        this.assessmentType = assessmentType;
        this.dueDate = dueDate;
        this.notes = notes;
    }
    @Ignore
    public Assessment(int courseId, String assessmentType, String dueDate, String notes) {
        this.courseId = courseId;
        this.assessmentType = assessmentType;
        this.dueDate = dueDate;
        this.notes = notes;
    }













    public Assessment() {
        this.assessmentId = 1;
        this.courseId = 1;
        this.assessmentType = "N/A";
        this.dueDate = "N/A";
        this.notes = "N/A";
    }

    public int getAssessmentId() {
        return assessmentId;
    }
    public int getCourseId() {
        return courseId;
    }

    public String getAssessmentType() {
        return assessmentType;
    }
    public String getDueDate() {
        return dueDate;
    }
    public String getNotes() {
        return notes;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }


}
