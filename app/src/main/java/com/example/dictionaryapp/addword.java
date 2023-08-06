package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addword extends AppCompatActivity {
    private ArrayList<Word> words = new ArrayList<>();
    private EditText word_orig;
    private EditText word_trans;
    private EditText word_descr;
    private Button add_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addword);
        setUi();
        add_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(word_orig.getText().toString().length() == 0 || word_trans.getText().toString().length()==0 || word_descr.getText().toString().length()==0){
                    Toast.makeText(addword.this, "Please fill the fields!",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(addword.this, MainActivity.class);
                    intent.putExtra("word_orig",word_orig.getText().toString());
                    intent.putExtra("word_trans",word_trans.getText().toString());
                    intent.putExtra("word_descr",word_descr.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    public void setUi(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Word");
        setSupportActionBar(toolbar);
        word_orig = findViewById(R.id.word_original);
        word_trans = findViewById(R.id.word_translation);
        word_descr = findViewById(R.id.word_description);
        add_word = findViewById(R.id.button);
    }
}