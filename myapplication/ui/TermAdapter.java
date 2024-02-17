package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {
    Context context;
    static ArrayList<TermModel> termModels;
    private final RecyclerViewInterface recyclerViewInterface;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_recycler_fragment,
                viewGroup, false);
        return new ViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        viewHolder.getTextView().setText(termModels.get(position).getTermName());
        viewHolder.getTextView2().setText(termModels.get(position).getTermStart());
        viewHolder.getTextView3().setText(termModels.get(position).getTermEnd());
    }

    @Override
    public int getItemCount() {
        return termModels.size();
    }

    public TermAdapter(Context context, ArrayList<TermModel> termModels,
                       RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.termModels = termModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView textView2;
        private final TextView textView3;

        public ViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);
            textView = view.findViewById(R.id.textView);
            textView2 = view.findViewById(R.id.textView2);
            textView3 = view.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onTermClick(position);

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
                            recyclerViewInterface.onLongTermClick(position);
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

        public void highlight(View itemView){
                itemView.setBackgroundColor(Color.parseColor("#88FF88"));
        }

        public void unhighlight(View itemView){
            itemView.setBackgroundColor(Color.parseColor("#000000"));
        }
    }
}
