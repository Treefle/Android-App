package com.example.myapplication.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entities.ClassEntity;

import java.util.List;

@Dao
public interface ClassDAO {
    @Query("SELECT * FROM class_table")
    List<ClassEntity> getAll();

    @Insert
    void insert(ClassEntity classEntity);

    @Delete
    void delete(ClassEntity classEntity);

    @Update
    void update (ClassEntity classEntity);
}
