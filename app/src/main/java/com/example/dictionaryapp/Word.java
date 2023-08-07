package com.example.dictionaryapp;

public class Word {
    private static int static_id = 1;
    private Integer id;
    private String word_original;
    private String word_translate;
    private String word_description;

    public Word(String word_original,String word_translate, String word_description){
        this.id = static_id;
        this.word_original = word_original;
        this.word_translate = word_translate;
        this.word_description = word_description;
        static_id++;
    }
    public String getWord_original(){
        return this.word_original;
    }
    public String getWord_translate(){
        return this.word_translate;
    }
    public String getWord_description(){
        return this.word_description;
    }

    public void setWord_original(String text){this.word_original=text;}
    public void setWord_translate(String text){this.word_translate=text;}
    public void setWord_description(String text){this.word_description=text;}

}
