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

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    Context context;
    static ArrayList<ClassModel> classModels;
    private final RecyclerViewInterface classRecyclerViewInterface;

    public ClassAdapter(Context context, ArrayList<ClassModel> classModels,
                        RecyclerViewInterface classRecyclerViewInterface){
        this.context = context;
        this.classModels = classModels;
        this.classRecyclerViewInterface = classRecyclerViewInterface;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.class_recycler_fragment, viewGroup, false);
        return new ViewHolder(view, classRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(classModels.get(position).getClassName());
        holder.getTextView2().setText(classModels.get(position).getClassStart());
        holder.getTextView3().setText(classModels.get(position).getClassEnd());
        holder.getTextView4().setText(classModels.get(position).getClassStatus());
    }

    @Override
    public int getItemCount() {
        return classModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;
        private final TextView textView2;
        private final TextView textView3;
        private final TextView textView4;

        public ViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface){
            super(view);
            textView = view.findViewById(R.id.ClassTitle);
            textView2 = view.findViewById(R.id.ClassStart);
            textView3 = view.findViewById(R.id.ClassEnd);
            textView4 = view.findViewById(R.id.ClassStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onClassClick(position);
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
                            recyclerViewInterface.onLongClassClick(position);
                        }
                    }
                    return false;
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }

        public TextView getTextView2() {
            return textView2;
        }

        public TextView getTextView3() {
            return textView3;
        }

        public TextView getTextView4() {
            return textView4;
        }

    }

}
