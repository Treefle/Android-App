package com.example.myapplication.ui;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;

public class ClassDialogFragment  extends DialogFragment {

    public interface classDialogInputListener{
        void saveClassInput(ClassModel input);
    }
    private ClassDialogFragment binding;
    public classDialogInputListener classDialogInputListener;
    private TextView newClassTermName, newClassName, newClassStart, newClassEnd, newClassStatus, newInstructorName, newInstructorPhone, newInstructorEmail;
    private ClassModel input;
    private Button saveButton, cancelButton, noteButton;
    private Switch endAlerts, startAlerts;

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean edit;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){

        if(binding == null){
            binding = ClassDialogFragment.this;
        }
        View v = inflater.inflate(R.layout.class_dialog, container, false);
        newClassTermName  = v.findViewById(R.id.newClassTermName);
        newClassName  = v.findViewById(R.id.newClassName);
        newClassStart  = v.findViewById(R.id.newClassStart);
        newClassEnd  = v.findViewById(R.id.newClassEnd);
        newClassStatus = v.findViewById(R.id.newClassStatus);
        newInstructorName  = v.findViewById(R.id.instructorName);
        newInstructorPhone = v.findViewById(R.id.instructorPhone);
        newInstructorEmail = v.findViewById(R.id.instructorEmail);
        endAlerts = v.findViewById(R.id.CourseEndAlerts);
        startAlerts = v.findViewById(R.id.CourseStartAlerts);

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveButton = view.findViewById(R.id.button6);
        cancelButton = view.findViewById(R.id.button7);
        noteButton = view.findViewById(R.id.button_first);


        noteButton.setOnClickListener(view1 -> NavHostFragment.findNavController(ClassDialogFragment.this)
                .navigate(R.id.action_FirstFragment_to_classNoteActivity3));

        if(edit){
            newClassTermName.setText(MainActivity.classModel.getTermName());
            newClassName.setText(MainActivity.classModel.getClassName());
            newClassStart.setText(MainActivity.classModel.getClassStart());
            startAlerts.setChecked(MainActivity.classModel.getStartAlerts());
            newClassEnd.setText(MainActivity.classModel.getClassEnd());
            endAlerts.setChecked(MainActivity.classModel.getEndAlerts());
            newClassStatus.setText(MainActivity.classModel.getClassStatus());
            newInstructorName.setText(MainActivity.classModel.getInstructorName());
            newInstructorPhone.setText(MainActivity.classModel.getInstructorPhone());
            newInstructorEmail.setText(MainActivity.classModel.getInstructorEmail());
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
                        input = new ClassModel(newClassTermName.getText().toString(),
                                newClassName.getText().toString(),
                                newClassStart.getText().toString(),
                                startAlerts.isChecked(),
                                newClassEnd.getText().toString(),
                                endAlerts.isChecked(),
                                newClassStatus.getText().toString(),
                                newInstructorName.getText().toString(),
                                newInstructorPhone.getText().toString(),
                                newInstructorEmail.getText().toString());
                        classDialogInputListener.saveClassInput(input);
                        getDialog().dismiss();
                    }
                });

    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            classDialogInputListener = (classDialogInputListener)getActivity();
        }
        catch (ClassCastException e){

        }
    }
}
