package com.example.myapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;

public class EditDeleteDialogFragment extends DialogFragment {

    public interface OnEditInputListener{
        void editDeleteInput (boolean input);
    }
    public OnEditInputListener onEditInputListener;
    private boolean input;
    private Button editButton, deleteButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.edit_delete_dialog, container, false);
        editButton = v.findViewById(R.id.button3);
        deleteButton = v.findViewById(R.id.button4);
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = true;
                onEditInputListener.editDeleteInput(input);
                getDialog().dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = false;
                onEditInputListener.editDeleteInput(input);
                getDialog().dismiss();
            }
        });
    }

    @Override public void onAttach(Context context){
        super.onAttach(context);
        try{
            onEditInputListener = (EditDeleteDialogFragment.OnEditInputListener)getActivity();
        }
        catch(ClassCastException e){

        }
    }
}
