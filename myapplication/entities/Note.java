package com.example.myapplication.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    public Note(){
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_ID")
    private int noteID;
    @ColumnInfo(name = "note_label")
    private String noteLabel;
    @ColumnInfo(name = "note_content")
    private String noteContent;

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteLabel() {
        return noteLabel;
    }
    public void setNoteLabel(String noteLabel) {
        this.noteLabel = noteLabel;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

}
