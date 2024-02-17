package com.example.myapplication.ui;

public interface RecyclerViewInterface {
    void onTermClick(int position);

    void onLongTermClick(int position);

    void onClassClick(int position);

    void onLongClassClick(int position);

    void onAssessmentClick();

    void onLongAssessmentClick(int position);

    void noteClick(int position);

    void noteLongClick(int position);
}
