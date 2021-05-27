package com.mschneider.wgutermtracker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "terms")
public class Term {
    @ColumnInfo(name = "termId")
    @PrimaryKey
    public Long termId;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "start_date")
    public String startDate;
    @ColumnInfo(name = "end_date")
    public String endDate;

    @Ignore
    public Term(Long termId, String title, String startDate, String endDate) {
        this.termId = termId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Ignore
    public Term(String title, String startDate, String endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Term() {
        this.termId = Long.valueOf(1);
        this.title = "test";
        this.startDate = "test";
        this.endDate = "N/A";
    }

    public Long getTermId() {
        return termId;
    }
    public String getTitle() {
        return title;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }

    public void setTermId(Long termId) {
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

}
