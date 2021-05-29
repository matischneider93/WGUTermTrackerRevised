package com.mschneider.wgutermtracker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses", foreignKeys = {@ForeignKey(entity = Term.class,
        parentColumns = "termId",
        childColumns = "termId")
})
public class Course {
    @PrimaryKey
    @ColumnInfo(name = "courseId")
    public long courseId;
    @ColumnInfo(name = "termId")
    public long termId;
    @ColumnInfo(name = "course_title")
    public String title;
    @ColumnInfo(name = "start_date")
    public String startDate;
    @ColumnInfo(name = "end_date")
    public String endDate;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "mentor_name")
    public String mentorName;
    @ColumnInfo(name = "mentor_phone")
    public String mentorPhone;
    @ColumnInfo(name = "mentor_email")
    public String mentor_email;
    @ColumnInfo(name = "notes")
    public String notes;


    public Course(long courseId, long termId, String title, String startDate, String endDate, String status, String mentorName, String mentorPhone, String mentor_email, String notes) {
        this.courseId = courseId;
        this.termId = termId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentor_email = mentor_email;
        this.notes = notes;
    }




    public String getTitle() {
        return title;
    }



    public long getCourseId() {
        return courseId;
    }

    public long getTermId() {
        return termId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public String getMentorName() {
        return mentorName;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public String getMentor_email() {
        return mentor_email;
    }

    public String getNotes() {
        return notes;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public void setMentor_email(String mentor_email) {
        this.mentor_email = mentor_email;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}


