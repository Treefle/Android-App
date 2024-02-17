package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class AssessmentDialogFragment extends DialogFragment {

    public assessmentDialogInputListener assessmentDialogInputListener;
    public TextView assessmentClassName, assessmentName, assessmentStart, assessmentEnd;
    public AssessmentModel input;
    public RadioButton  Objective, Performance;
    public Button Save, Cancel;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public Switch startAlerts, endAlerts;
    public Boolean edit;


    public void setEdit(Boolean edit) {
        this.edit = edit;
    }
    public interface assessmentDialogInputListener{
        void saveAssessmentInput(AssessmentModel input);
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.assessment_dialog,container,false);
        assessmentClassName = v.findViewById(R.id.assessmentClassName);
        assessmentName = v.findViewById(R.id.assessmentTitle);
        assessmentStart = v.findViewById(R.id.assessmentStart);
        assessmentEnd = v.findViewById(R.id.assessmentEnd);
        Objective = v.findViewById(R.id.radioObjective);
        Performance = v.findViewById(R.id.radioPerformance);
        Save = v.findViewById(R.id.assessmentSave);
        Cancel = v.findViewById(R.id.assessmentCancel);
        startAlerts = v.findViewById(R.id.AssessmentStartAlerts);
        endAlerts = v.findViewById(R.id.AssessmentEndAlerts);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(edit){
            if(MainActivity.assessmentModel.type.equalsIgnoreCase("Objective")){
                Objective.setChecked(true);
                Performance.setChecked(false);
            }
            else if(MainActivity.assessmentModel.type.equalsIgnoreCase("Performance")){
                Performance.setChecked(true);
                Objective.setChecked(false);
            }

            assessmentClassName.setText(MainActivity.assessmentModel.assessmentClassName);
            assessmentName.setText(MainActivity.assessmentModel.title);
            assessmentStart.setText(MainActivity.assessmentModel.startDate);
            assessmentEnd.setText(MainActivity.assessmentModel.endDate);
            startAlerts.setChecked(MainActivity.assessmentModel.startAlerts);
            endAlerts.setChecked(MainActivity.assessmentModel.endAlerts);
        }
        Cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getDialog().cancel();
                    }
                });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "";
                String alerts = "";
                if(Objective.isChecked()){
                    type = "Objective";
                }
                else if(Performance.isChecked()){
                    type = "Performance";
                }

                input = new AssessmentModel(assessmentClassName.getText().toString(),
                        type,
                        assessmentName.getText().toString(),
                        assessmentStart.getText().toString(),
                        assessmentEnd.getText().toString(),
                        startAlerts.isChecked(),endAlerts.isChecked());
                assessmentDialogInputListener.saveAssessmentInput(input);
                getDialog().dismiss();
            }
        });
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            assessmentDialogInputListener = (AssessmentDialogFragment.assessmentDialogInputListener)getActivity();
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
    }

}
