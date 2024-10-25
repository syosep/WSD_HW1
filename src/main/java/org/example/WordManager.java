package org.example;

import java.io.*;
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
    public List<Word> searchWord(String keyword) {
        List<Word> result = new ArrayList<>();
        for (Word word : words) {
            if (word.getWord().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(word);
            }
        }
        return result;
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
    public boolean saveFile() {
        String fileName = "./src/main/java/org/example/WordList";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Word word : words) {
                writer.write(word.getId() + "," + word.getWord() + "," + word.getMeaning() + "," + word.getLevel());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void loadFile(String fileName) {
        words.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+", 4);
                int id = Integer.parseInt(parts[0]);
                int level = parts[1].length();
                String word = parts[2];
                String meaning = parts[3];
                words.add(new Word(id, word, meaning, level));
                nextId = Math.max(nextId, id + 1);
            }
        } catch (IOException e) {
            System.out.println("파일 로딩 중 오류 발생: " + e.getMessage());
        }
    }
}