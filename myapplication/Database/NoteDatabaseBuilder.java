package com.example.myapplication.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.myapplication.DAOs.NoteDAO;
import com.example.myapplication.entities.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabaseBuilder extends RoomDatabase {
    public abstract NoteDAO noteDAO();

    private static volatile NoteDatabaseBuilder INSTANCE;

    static NoteDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    NoteDatabaseBuilder.class, "MyNoteDatabaseBuilder")
                            .fallbackToDestructiveMigrationOnDowngrade().build();
                }
            }
        }
        return INSTANCE;
    }
}
