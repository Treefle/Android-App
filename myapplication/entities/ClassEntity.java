package com.example.myapplication.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "class_table")
public class ClassEntity {

    public ClassEntity() {
    }
    @Ignore
    public ClassEntity(int classID, String termName, String className, String classStart, String classEnd, String classStatus) {
        this.classID = classID;
        this.termName = termName;
        this.className = className;
        this.classStart = classStart;
        this.classEnd = classEnd;
        this.classStatus = classStatus;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "class_ID")
    private int classID;
    @ColumnInfo(name = "termName")
    private String termName;
    @ColumnInfo(name = "className")
    private String className;
    @ColumnInfo(name = "classStart")
    private String classStart;
    @ColumnInfo(name = "classEnd")
    private String classEnd;
    @ColumnInfo(name = "classStatus")
    private String classStatus;
    @ColumnInfo(name = "instructorName")
    private String instructorName;
    @ColumnInfo(name = "instructorPhone")
    private String instructorPhone;
    @ColumnInfo(name = "instructorEmail")
    private String instructorEmail;
    @ColumnInfo(name = "start_alerts")
    private Boolean startAlerts;
    @ColumnInfo(name="end_alerts")
    private Boolean endAlerts;


    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }
    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassStart() {
        return classStart;
    }

    public void setClassStart(String classStart) {
        this.classStart = classStart;
    }

    public String getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(String classEnd) {
        this.classEnd = classEnd;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
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
