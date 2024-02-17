package com.example.myapplication.ui;

import com.example.myapplication.entities.ClassEntity;

import java.util.ArrayList;

public class ClassModel extends ClassEntity {
    String termName;
    String className;
    String classStart;
    String classEnd;
    String classStatus;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    ArrayList<String> assessmentsList;
    Boolean startAlerts, endAlerts;


    public ClassModel(String termName,
                      String className,
                      String classStart,
                      Boolean startAlerts,
                      String classEnd,
                      Boolean endAlerts,
                      String classStatus,
                      String instructorName,
                      String instructorPhone,
                      String instructorEmail){
        this.termName = termName;
        this.className = className;
        this.classStart = classStart;
        this.startAlerts = startAlerts;
        this.classEnd = classEnd;
        this.endAlerts = endAlerts;
        this.classStatus = classStatus;
        this.instructorName = instructorName;
        this.instructorPhone =  instructorPhone;
        this.instructorEmail = instructorEmail;

    }

    public ClassModel() {

    }

    public ClassModel(String classTermName, String className, String classStart, String classEnd, String classStatus,Boolean startAlerts, Boolean endAlerts) {
        this.termName = classTermName;
        this.className = className;
        this.classStart = classStart;
        this.startAlerts = startAlerts;
        this.classEnd = classEnd;
        this.endAlerts = endAlerts;
        this.classStatus = classStatus;
    }

    public String getTermName() {
        return termName;
    }

    public String getClassName() {
        return className;
    }

    public String getClassStart() {
        return classStart;
    }

    public String getClassEnd() {
        return classEnd;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    @Override
    public Boolean getStartAlerts() {
        return startAlerts;
    }

    @Override
    public Boolean getEndAlerts() {
        return endAlerts;
    }

    public ArrayList<String> getAssessmentsList() {
        return assessmentsList;
    }

}
