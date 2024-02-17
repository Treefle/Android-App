package com.example.myapplication.entities;

import androidx.room.*;
import java.util.Date;

@Entity(tableName = "term_table")
public class Term {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "term_ID")
    private int termID;
    @ColumnInfo(name = "termName")
    private String termName;
    @ColumnInfo(name = "termStart")
    private String termStart;
    @ColumnInfo(name = "termEnd")
    private String termEnd;

    public Term() {
    }


    public int getTermID() {
        return termID;
    }
    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }

}
