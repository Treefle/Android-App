package com.example.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.myapplication.R;

import java.util.ArrayList;

public class AssessmentAdapter  extends Adapter<AssessmentAdapter.ViewHolder> {
    Context context;
    static ArrayList<AssessmentModel> assessmentModels;
    private final RecyclerViewInterface recyclerViewInterface;

    public AssessmentAdapter(Context context, ArrayList<AssessmentModel> assessmentModels,
                             RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.assessmentModels = assessmentModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public AssessmentAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.assessment_recycler_fragment, viewGroup, false);
        return new ViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.ViewHolder holder, int position) {
        holder.getTextView().setText(assessmentModels.get(position).getTitle());
        holder.getTextView2().setText(assessmentModels.get(position).getStartDate());
        holder.getTextView3().setText(assessmentModels.get(position).getEndDate());
        holder.getTextView4().setText(assessmentModels.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return assessmentModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private  final TextView textView;
        private  final TextView textView2;
        private  final TextView textView3;
        private  final TextView textView4;

        public ViewHolder(View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);
            textView = view.findViewById(R.id.AssessmentTitle);
            textView2 = view.findViewById(R.id.AssessmentStart);
            textView3 = view.findViewById(R.id.AssessmentEnd);
            textView4 = view.findViewById(R.id.AssessmentStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onAssessmentClick();
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onLongAssessmentClick(position);
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
