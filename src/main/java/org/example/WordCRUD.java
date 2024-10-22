package org.example;

public interface WordCRUD {
    void listWord();
    void listWordByLevel(int level);
    void searchWord(String keyword);
    void addWord(String word, String meaning, int level);
    void modifyWord(int id, String word, String meaning, int level);
    void deleteWord(int id);
    void saveFile(String fileName);
    void loadFile(String fileName);
}



