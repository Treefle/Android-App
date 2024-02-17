package com.example.myapplication.ui;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;

public class NoteDialogFragment extends DialogFragment {

    public interface noteDialogInputListener {
        void saveNote(NoteModel noteModel);
    }
    public noteDialogInputListener noteDialogInputListener;
    public EditText label, note;
    public NoteModel noteModel;
    public Button saveButton, cancelButton;

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean edit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.note_dialog,container,false);
        label = v.findViewById(R.id.noteDialogLabel);
        note = v.findViewById(R.id.noteDialogText);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveButton = view.findViewById(R.id.noteSave);
        cancelButton = view.findViewById(R.id.noteCancel);

        if(edit){
            label.setText(ClassNoteActivity.noteModel.getLabel());
            note.setText(ClassNoteActivity.noteModel.getNoteContent());
        }

        cancelButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getDialog().cancel();
                    }
                });
        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        noteModel = new NoteModel();
                        noteModel.setNoteContent(note.getText().toString());
                        noteModel.setLabel(label.getText().toString());
                        noteDialogInputListener.saveNote(noteModel);
                        getDialog().dismiss();
                    }
                });
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            noteDialogInputListener = (noteDialogInputListener)getActivity();
        }
        catch (ClassCastException e){

        }
    }
}