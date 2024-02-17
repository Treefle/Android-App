package com.example.myapplication.ui;

import android.Manifest;
import android.os.Bundle;

import com.example.myapplication.AlarmManager.MyIntentService;
import com.example.myapplication.Database.Repository;
import com.example.myapplication.databinding.ActivityShareNoteBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.core.app.ActivityCompat;

import com.example.myapplication.R;
import com.example.myapplication.entities.Note;

import java.util.ArrayList;

public class ShareNote extends AppCompatActivity {


    private ActivityShareNoteBinding binding;
    public static String noteText;
    private EditText text;
    private EditText editTextSendtoNumber;
    ArrayList<NoteModel> allNotes = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShareNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        text = findViewById(R.id.textViewContent);
        text.setText(noteText);
        editTextSendtoNumber = findViewById(R.id.textViewSendtoNumber);
    }


    public void sendSMS(View view){
        String message = text.getText().toString();
        String number = editTextSendtoNumber.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        try{
            smsManager.sendTextMessage(number,null,message,null,null);
        }catch (Exception e){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        }
    }
}