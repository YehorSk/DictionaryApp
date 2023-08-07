package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<Word> words;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        setUI();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            words.add(new Word(getIntent().getStringExtra("word_orig"),getIntent().getStringExtra("word_trans"),getIntent().getStringExtra("word_descr")));
            Toast.makeText(MainActivity.this, "Word added!",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        saveData();
    }
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(words);
        editor.putString("dictionary", json);
        editor.apply();
    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dictionary",null);
        Type type = new TypeToken<ArrayList<Word>>() {}.getType();
        words = gson.fromJson(json, type);
        if(words==null){
            words = new ArrayList<Word>();
        }
    }

    public void addWordActivity(View view){
        Intent intent = new Intent(MainActivity.this, addword.class);
        startActivity(intent);
    }
    public void setUI(){
        FloatingActionButton addWord = findViewById(R.id.floatingActionButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Dictionary");
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(this, words, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, WordActivity.class);
        intent.putExtra("word_original",words.get(position).getWord_original());
        intent.putExtra("word_translation",words.get(position).getWord_translate());
        intent.putExtra("word_description",words.get(position).getWord_description());
        intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        words.remove(position);
        recyclerViewAdapter.notifyItemRemoved(position);
        Toast.makeText(MainActivity.this,"Word deleted!",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }
}