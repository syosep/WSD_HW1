package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordCRUD implements ICRUD {
    private List<Word> words;
    private int nextId;

    public WordCRUD() {
        words = new ArrayList<>();
        nextId = 1;
    }

    @Override
    public void addWord(int level, String word, String meaning) {
        Word newWord = new Word(nextId, level, word, meaning);
        words.add(newWord);
        nextId++;
        System.out.println("* 추가 성공!");
    }

    @Override
    public void modifyWord(int wordId, String newWord, String newMeaning) {
        for (Word word : words) {
            if (word.getId() == wordId) {
                word.setWord(newWord);
                word.setMeaning(newMeaning);
                System.out.println("* 수정 성공!");
                return;
            }
        }
        System.out.println("존재하지 않는 단어입니다.");
    }

    @Override
    public void deleteWord(int wordId) {
        words.removeIf(word -> word.getId() == wordId);
        System.out.println("* 삭제 성공!");
    }

    @Override
    public List<Word> search(String sWord) {
        List<Word> result = new ArrayList<>();
        for (Word word : words) {
            if (word.getWord().toLowerCase().contains(sWord.toLowerCase())) {
                result.add(word);
            }
        }
        return result;
    }

    @Override
    public void listWords() {
        for (Word word : words) {
            System.out.println(word);
        }
    }

    @Override
    public void listByLevel(int level) {
        for (Word word : words) {
            if (word.getLevel() == level) {
                System.out.println(word);
            }
        }
    }

    public void saveFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Word word : words) {
                writer.write(word.getId() + "," + word.getLevel() + "," + word.getWord() + "," + word.getMeaning());
                writer.newLine();
            }
            System.out.println("* 파일저장 완료!");
        } catch (IOException e) {
            System.out.println("파일저장 오류 " + e.getMessage());
        }
    }

    public void loadFile(String fileName) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordData = line.split(",");
                int id = Integer.parseInt(wordData[0]);
                int level = Integer.parseInt(wordData[1]);
                String word = wordData[2];
                String meaning = wordData[3];
                words.add(new Word(id, level, word, meaning));
                nextId = Math.max(nextId, id + 1);
                count++;
            }
            System.out.println("=> " + count + "개의 단어 로딩 완료!");
        } catch (IOException e) {
            System.out.println("로딩 오류 " + e.getMessage());
        }
    }
}
