package com.example.myapplication.ui;

import com.example.myapplication.entities.Term;

public class TermModel extends Term {
    String termName;
    String termStart;
    String termEnd;


    boolean selected;


    public TermModel(String termName, String termStart, String termEnd) {
        this.termName = termName;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }
    public TermModel(){

    }
    public String getTermName() {
        return termName;
    }

    public String getTermStart() {
        return termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
