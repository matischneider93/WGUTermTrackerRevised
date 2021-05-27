package com.mschneider.wgutermtracker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessment {
    @ColumnInfo(name = "assessment_id")
    @PrimaryKey
    private Long assessmentId;
    @ColumnInfo(name = "course_Id")
    private Long courseId;
    @ColumnInfo(name = "assessment_type")
    private String assessmentType;
    @ColumnInfo(name = "due_date")
    private String dueDate;
    @ColumnInfo(name = "notes")
    private String notes;



    @Ignore
    public Assessment(Long assessmentId, Long courseId, String assessmentType, String dueDate, String notes) {
        this.assessmentId = assessmentId;
        this.courseId = courseId;
        this.assessmentType = assessmentType;
        this.dueDate = dueDate;
        this.notes = notes;
    }
    @Ignore
    public Assessment(Long courseId, String assessmentType, String dueDate, String notes) {
        this.courseId = courseId;
        this.assessmentType = assessmentType;
        this.dueDate = dueDate;
        this.notes = notes;
    }













    public Assessment() {
        this.assessmentId = Long.valueOf(1);
        this.courseId = Long.valueOf(1);
        this.assessmentType = "N/A";
        this.dueDate = "N/A";
        this.notes = "N/A";
    }

    public Long getAssessmentId() {
        return assessmentId;
    }
    public Long getCourseId() {
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

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }
    public void setCourseId(Long courseId) {
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
