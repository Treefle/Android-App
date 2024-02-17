package com.example.myapplication.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entities.Term;

import java.util.List;

@Dao
public interface TermDAO {

    @Query("SELECT * FROM term_table")
    List<Term> getAll();

    @Query("SELECT * FROM term_table WHERE term_ID IN(:term_IDs)")
    List<Term> loadAllByIds(int[] term_IDs);

    @Insert
    void insert(Term term);

    @Delete
    void delete(Term term);

    @Update
    void update(Term term);
}
