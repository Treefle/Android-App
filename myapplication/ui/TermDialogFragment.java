package com.example.myapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;

public class TermDialogFragment extends DialogFragment{

    public interface OnDialogInputListener{
        void saveTermInput(TermModel input);
    }
    public OnDialogInputListener onDialogInputListener;

    private TextView newTermName, newStartDate, newEndDate;
    private TermModel input;
    private Button saveButton, cancelButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.term_dialog, container, false);
        newTermName = v.findViewById(R.id.newTermName);
        newStartDate = v.findViewById(R.id.newStartDate);
        newEndDate = v.findViewById(R.id.newEndDate);
        newTermName.setText(MainActivity.getTermName());
        newStartDate.setText(MainActivity.getTermStart());
        newEndDate.setText(MainActivity.getTermEnd());
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cancelButton = view.findViewById(R.id.cancelButton);
        saveButton = view.findViewById(R.id.saveButton);

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
                        input = new TermModel(newTermName.getText().toString(),newStartDate.getText().toString(),newEndDate.getText().toString());
                        onDialogInputListener.saveTermInput(input);
                        getDialog().dismiss();
                    }
        });
    }

    @Override public void onAttach(Context context){
        super.onAttach(context);
        try{
            onDialogInputListener = (OnDialogInputListener)getActivity();
        }
        catch(ClassCastException e){

        }
    }
}
