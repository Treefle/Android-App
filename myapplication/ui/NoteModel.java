package com.example.myapplication.ui;

import com.example.myapplication.entities.Note;

public class NoteModel extends Note {
    String label;
    String noteContent;

    public NoteModel() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public NoteModel(String label, String noteContent) {
        this.label = label;
        this.noteContent = noteContent;
    }
}
