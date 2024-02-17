package com.example.myapplication.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.DAOs.ClassDAO;
import com.example.myapplication.entities.ClassEntity;

@Database(entities = {ClassEntity.class}, version = 1, exportSchema = false)
public abstract class ClassDatabaseBuilder extends RoomDatabase {
    public abstract ClassDAO classDAO();
    private static volatile ClassDatabaseBuilder INSTANCE;
    static ClassDatabaseBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ClassDatabaseBuilder.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ClassDatabaseBuilder.class,"ClassDatabaseBuilder").
                            fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}

