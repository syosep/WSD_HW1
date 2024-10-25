package org.example;

import java.util.List;

public interface ICRUD {
    void addWord(int level, String word, String meaning);
    void modifyWord(int wordId, String newWord, String newMeaning);
    void deleteWord(int wordId);
    List<Word> search(String sWord);
    void listWords();
    void listByLevel(int level);
}
