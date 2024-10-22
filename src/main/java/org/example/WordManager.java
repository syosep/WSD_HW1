package org.example;

import java.util.*;

public class WordManager implements WordCRUD {
    private List<Word> words;
    private int nextId = 1;

    public WordManager() {
        this.words = new ArrayList<>();
    }

    @Override
    public void listWord() {
        for (Word word : words) {
            System.out.println(word);
        }
    }

    @Override
    public void listWordByLevel(int level) {
        for (Word word : words) {
            if (word.getLevel() == level) {
                System.out.println(word);
            }
        }
    }

    @Override
    public void searchWord(String keyword) {
        for (Word word : words) {
            if (word.getWord().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(word);
            }
        }
    }

    @Override
    public void addWord(String word, String meaning, int level) {
        words.add(new Word(nextId++, word, meaning, level));
    }

    @Override
    public void modifyWord(int id, String word, String meaning, int level) {
        for (Word w : words) {
            if (w.getId() == id) {
                w.setWord(word);
                w.setMeaning(meaning);
                w.setLevel(level);
                break;
            }
        }

    }

    @Override
    public void deleteWord(int id) {
        words.removeIf(word -> word.getId() == id);

    }

    @Override
    public void saveFile(String fileName) {

    }

    @Override
    public void loadFile(String fileName) {

    }
}
