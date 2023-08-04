package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private ArrayList<Word> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Dictionary");
        setSupportActionBar(toolbar);
        AddWords();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, words, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
    public void AddWords(){
        words.add(new Word("Cat", "Кошка", "Домашнее животное, которому раньше поклонялись Египтяне"));
        words.add(new Word("Dog", "Собака", "Верный друг и компаньон человека"));
        words.add(new Word("Book", "Книга", "Печатное издание с текстом и/или иллюстрациями"));
        words.add(new Word("Car", "Машина", "Транспортное средство с двигателем, предназначенное для перевозки людей и грузов"));
        words.add(new Word("Sun", "Солнце", "Звезда, вокруг которой вращается Земля"));
        words.add(new Word("Moon", "Луна", "Естественный спутник Земли"));
        words.add(new Word("Computer", "Компьютер", "Устройство для обработки данных и выполнения программ"));
        words.add(new Word("Tree", "Дерево", "Растение с прямым стволом и ветвями"));
        words.add(new Word("River", "Река", "Естественный водный поток"));
        words.add(new Word("Mountain", "Гора", "Высокий естественный рельеф с земной поверхности"));
        words.add(new Word("Phone", "Телефон", "Устройство для передачи аудиосигналов по расстоянию"));
        words.add(new Word("House", "Дом", "Место проживания, строение для жилья"));
        words.add(new Word("Pen", "Ручка", "Инструмент для письма и рисования"));
        words.add(new Word("Table", "Стол", "Мебель для размещения предметов на рабочей поверхности"));
        words.add(new Word("Chair", "Стул", "Мебель для сидения"));
        words.add(new Word("City", "Город", "Крупное поселение с развитой инфраструктурой"));
        words.add(new Word("Lake", "Озеро", "Водоем с пресной водой, окруженный сушей"));
        words.add(new Word("Ocean", "Океан", "Самый большой тип водного пространства на планете"));
        words.add(new Word("Beach", "Пляж", "Песчаная или галечная полоса на берегу моря или озера"));
        words.add(new Word("Fire", "Огонь", "Процесс горения с выделением света и тепла"));
    }

    @Override
    public void onItemClick() {

    }
}