package com.example.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    Context context;
    static ArrayList<NoteModel> noteList;
    private final RecyclerViewInterface recyclerViewInterface;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView noteText, noteLabel;

        public ViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);
            noteText = view.findViewById(R.id.noteText);
            noteLabel = view.findViewById(R.id.noteLabel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.noteClick(position);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.noteLongClick(position);
                        }
                    }
                    return false;
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_recycler_fragment,
                viewGroup,false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.noteText.setText(noteList.get(position).getNoteContent());
        holder.noteLabel.setText(noteList.get(position).getLabel());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public NotesAdapter (Context context, ArrayList<NoteModel> noteList, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.noteList = noteList;
        this.recyclerViewInterface = recyclerViewInterface;
    }
}
