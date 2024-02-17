package com.example.myapplication.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Database.Repository;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityClassNoteBinding;
import com.example.myapplication.entities.ClassEntity;
import com.example.myapplication.entities.Note;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ClassNoteActivity extends AppCompatActivity implements NoteInterface, RecyclerViewInterface, NoteDialogFragment.noteDialogInputListener {

    NotesAdapter notesAdapter;
    ArrayList<NoteModel> notesList = new ArrayList<>();
    private ActivityClassNoteBinding binding;
    public boolean add;
    public static NoteModel noteModel;
    public int noteClickPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClassNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.floatingActionButton2.setOnClickListener(view ->
            Snackbar.make(view, "Adding New Note", Snackbar.LENGTH_LONG).setAction("Action", notefabOnClick()).show());

        BottomNavigationView navView = findViewById(R.id.nav_view_note);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_class_note);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        RecyclerView notesView = findViewById(R.id.notesRecycler);
        notesAdapter = new NotesAdapter(this, notesList, this);
        notesView.setAdapter(notesAdapter);
        notesView.setLayoutManager(new LinearLayoutManager(this));
        setNotes();
        }

        private void setNotes() {
        Repository repository = new Repository(getApplication());
          repository.getAllNotes().stream().iterator().forEachRemaining(note -> {
             if(note.getNoteLabel().equalsIgnoreCase(repository.getAllClasses().get(MainActivity.getClassClickPosition()).getClassName())){
                 notesList.add(new NoteModel(note.getNoteLabel(), note.getNoteContent()));
             }
          });
          if(notesList.isEmpty()){
                     NoteModel noteModel = new NoteModel();
                     noteModel.setLabel(repository.getAllClasses().get(MainActivity.getClassClickPosition()).getClassName());
                     noteModel.setNoteContent("Note Content");
                     notesList.add(noteModel);
                     Note note = new Note();
                     note.setNoteLabel(noteModel.label);
                     note.setNoteContent(noteModel.noteContent);
                     repository.insert(note);
                 }


        }

    @Override
    public void onTermClick(int position) {

    }

    @Override
    public void onLongTermClick(int position) {

    }

    @Override
    public void onClassClick(int position) {

    }

    @Override
    public void onLongClassClick(int position) {

    }

    @Override
    public void onAssessmentClick() {

    }

    @Override
    public void onLongAssessmentClick(int position) {

    }

    @Override
    public void noteClick(int position) {
        add = false;
        noteClickPosition = position;
        noteModel = notesList.get(position);
        openNoteDialog(true);
    }

    @Override
    public void noteLongClick(int position) {
        noteClickPosition = position;
        new AlertDialog.Builder(this).setTitle("Send Text or Delete?").setPositiveButton("send text", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Repository repository = new Repository(getApplication());
                Note note = repository.getAllNotes().get(noteClickPosition);
                NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_class_note);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.action_global_shareNote2);
                ShareNote.noteText = note.getNoteContent();
            }
        }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Repository repository = new Repository(getApplication());
                Note note = repository.getAllNotes().get(noteClickPosition);
                repository.delete(note);
                notesList.remove(noteClickPosition);
                notesAdapter.notifyItemRemoved(noteClickPosition);
            }
        }).show();
    }

    @Override
    public void saveInput() {

    }
    public View.OnClickListener notefabOnClick(){
        add = true;
        openNoteDialog(false);
        return null;
    }

    public void openNoteDialog(boolean edit){
        NoteDialogFragment noteDialogFragment = new NoteDialogFragment();
        noteDialogFragment.setEdit(edit);
        noteDialogFragment.show(getSupportFragmentManager(), "note");
    }
    @Override
    public void saveNote(NoteModel noteModel) {
        //Toast.makeText(this,noteModel.getLabel() + noteModel.getNoteContent(), Toast.LENGTH_LONG).show();
        Repository repository = new Repository(getApplication());
        if(add){
            Note note = new Note();
            note.setNoteLabel(noteModel.getLabel());
            note.setNoteContent(noteModel.getNoteContent());
            Toast.makeText(this,note.getNoteLabel() + note.getNoteContent(),Toast.LENGTH_SHORT).show();
            repository.insert(note);
            notesList.add(noteModel);
            notesAdapter.notifyItemInserted(notesList.size());
        }else{
            Note note = repository.getAllNotes().get(noteClickPosition);
            note.setNoteLabel(noteModel.getLabel());
            note.setNoteContent(noteModel.getNoteContent());
            repository.update(note);
            notesList.set(noteClickPosition,(noteModel));
            notesAdapter.notifyItemChanged(noteClickPosition);
        }
    }
}