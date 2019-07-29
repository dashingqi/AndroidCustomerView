package com.example.indexwordproject;

public class Person {
    private String name;
    private String pinyin;

    public Person(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }
}
