package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class addword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addword);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Word");
        setSupportActionBar(toolbar);
    }
}