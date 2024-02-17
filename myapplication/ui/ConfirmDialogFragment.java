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

public class ConfirmDialogFragment extends DialogFragment {

    public interface OnDialogInputListener{
        void confirmDelete(int input);
    }
    public OnDialogInputListener onDialogInputListener;
    private int input;
    private TextView promptText;
    private Button confirmButton, cancelButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
    View v =inflater.inflate(R.layout.confirm_dialog, container, false);
    promptText = v.findViewById(R.id.textView10);
    confirmButton = v.findViewById(R.id.button);
    cancelButton = v.findViewById(R.id.button2);
    promptText.setText("Are you sure?");
    return v;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDialogInputListener.confirmDelete(input);
                getDialog().dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });
    }

    @Override public void onAttach(Context context){
        super.onAttach(context);
        try{
            onDialogInputListener = (ConfirmDialogFragment.OnDialogInputListener)getActivity();
        }
        catch(ClassCastException e){

        }
    }

}
