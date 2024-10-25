package org.example;

public class Word {
    private int id;
    private String word;
    private String meaning;
    private int level;

    public Word(int id, String word, String meaning, int level) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public int getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return id + " " + "*".repeat(level) + " " + word + " " + meaning;
    }
}
