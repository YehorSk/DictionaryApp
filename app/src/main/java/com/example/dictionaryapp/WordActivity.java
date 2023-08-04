
package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class WordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Word");
        setSupportActionBar(toolbar);
        TextView word_orig = findViewById(R.id.word_original);
        TextView word_trans = findViewById(R.id.word_translation);
        TextView word_descr = findViewById(R.id.word_description);
        word_orig.setText(getIntent().getStringExtra("word_original"));
        word_trans.setText(getIntent().getStringExtra("word_translation"));
        word_descr.setText(getIntent().getStringExtra("word_description"));
    }
}