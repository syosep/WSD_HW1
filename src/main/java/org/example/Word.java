package org.example;

import java.util.List;

public class Word {
    private int id;
    private int level;
    private String word;
    private List<String> meanings;

    public Word(int id, int level, String word, List<String> meanings) {
        this.id = id;
        this.level = level;
        this.word = word;
        this.meanings = meanings;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<String> meanings) {
        this.meanings = meanings;
    }

    // 레벨을 '*' 기호로 변환하는 메서드
    private String levelToStars() {
        return "*".repeat(level);
    }

    @Override
    public String toString() {
        return id + " " + levelToStars() + " " + word + " " + String.join(", ", meanings);
    }
}