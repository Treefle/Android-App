package com.example.myapplication.ui;

import com.example.myapplication.entities.Assessment;

public class AssessmentModel extends Assessment {
    String assessmentClassName, type, title,  startDate, endDate;
    boolean startAlerts, endAlerts;

    public AssessmentModel() {

    }

    public String getType() {
        return type;
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

    public boolean isStartAlerts() {
        return startAlerts;
    }

    public boolean isEndAlerts() {
        return endAlerts;
    }

    public AssessmentModel(String assessmentClassName,
                           String type,
                           String title,
                           String startDate,
                           String endDate,
                           boolean startAlerts,
                           boolean endAlerts
                            ){
        this.assessmentClassName = assessmentClassName;
        this.type = type;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startAlerts = startAlerts;
        this.endAlerts = endAlerts;
    }
}
