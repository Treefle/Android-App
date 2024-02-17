package com.example.myapplication.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entities.Assessment;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Query("SELECT * FROM assessment_table")
    List<Assessment> getAll();

    @Insert
    void insert(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Update
    void update (Assessment assessment);

}
