package com.example.myapplication.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.DAOs.AssessmentDAO;
import com.example.myapplication.entities.Assessment;

@Database(entities = {Assessment.class}, version = 1, exportSchema = false)
public abstract class AssessmentDatabaseBuilder extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();
    private static volatile AssessmentDatabaseBuilder INSTANCE;
    static AssessmentDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AssessmentDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AssessmentDatabaseBuilder.class, "AssessmentDatabase")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}

