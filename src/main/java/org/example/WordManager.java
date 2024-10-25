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
        Word newWord = new Word(nextId++, word, meaning, level);
        words.add(newWord);
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
                String[] parts = line.split(",", 4);
                if (parts.length < 4) {
                    System.out.println("오류 발생! " + line);
                    continue;
                }
                int id = Integer.parseInt(parts[0].trim());
                String word = parts[1].trim();
                String meaning = parts[2].trim();
                int level = Integer.parseInt(parts[3].trim());
                words.add(new Word(id, word, meaning, level));
                nextId = Math.max(nextId, id + 1);
            }
        } catch (IOException e) {
            System.out.println("오류 발생! " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("오류 발생! " + e.getMessage());
        }
    }
}