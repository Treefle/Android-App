package com.example.myapplication.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment_table")
public class Assessment {
    @Ignore
    public Assessment() {
    }

    public Assessment( String assessmentClassName,String type, String title, String startDate, Boolean startAlerts, String endDate, Boolean endAlerts) {
        this.assessmentClassName = assessmentClassName;
        this.type = type;
        this.title = title;
        this.startDate = startDate;
        this.startAlerts = startAlerts;
        this.endDate = endDate;
        this.endAlerts = endAlerts;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "assessment_ID")
    private int assessmentID;
    @ColumnInfo(name = "assessment_class_name")
    private String assessmentClassName;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "start_date")
    private String startDate;
    @ColumnInfo(name = "end_date")
    private String endDate;
    @ColumnInfo(name = "start_alerts")
    private Boolean startAlerts;
    @ColumnInfo(name="end_alerts")
    private Boolean endAlerts;

    public int getAssessmentID() {
        return assessmentID;
    }
    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentClassName() {
        return assessmentClassName;
    }

    public void setAssessmentClassName(String assessmentClassName) {
        this.assessmentClassName = assessmentClassName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Boolean getStartAlerts() {
        return startAlerts;
    }

    public void setStartAlerts(Boolean startAlerts) {
        this.startAlerts = startAlerts;
    }

    public Boolean getEndAlerts() {
        return endAlerts;
    }

    public void setEndAlerts(Boolean endAlerts) {
        this.endAlerts = endAlerts;
    }
}
