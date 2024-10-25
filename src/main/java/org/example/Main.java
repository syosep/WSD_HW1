package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WordCRUD manager = new WordCRUD();
        Scanner in = new Scanner(System.in);

        manager.loadFile("./src/main/java/org/example/WordList");

        while (true) {
            System.out.println("*** MY VOCA ***\n");
            System.out.println("1. List  2. List(level)  3. Search  4. Add  5. Modify  6. Delete  7. Save file  0. Exit\n");
            System.out.println("=> 원하는 메뉴는? ");
            int choice = in.nextInt();
            in.nextLine();

            if (choice == 0) {
                System.out.println("* 프로그램 종료! 다음에 만나요~");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("--------------------------------");
                    manager.listWords();
                    System.out.println("--------------------------------");
                    break;
                case 2:
                    System.out.print("\n=> 레벨(1:초급, 2:중급, 3:고급) 선택 : ");
                    int level = in.nextInt();
                    System.out.println("--------------------------------");
                    manager.listByLevel(level);
                    System.out.println("--------------------------------");
                    break;
                case 3:
                    System.out.print("\n=> 검색할 단어 입력 : ");
                    String searchWord = in.nextLine();
                    List<Word> results = manager.search(searchWord);
                    if (results.isEmpty()) {
                        System.out.println("검색한 단어가 존재하지 않습니다.");
                    } else {
                        for (Word word : results) {
                            System.out.println("--------------------------------");
                            System.out.println(word);
                            System.out.println("--------------------------------");
                        }
                    }
                    break;
                case 4:
                    System.out.print("\n=> 난이도(1,2,3) & 새 단어 입력 : ");
                    String input = in.nextLine();
                    String[] inputWords = input.split(" ", 2);
                    int addLevel = Integer.parseInt(inputWords[0]);
                    String newWord = inputWords[1];
                    System.out.print("=> 뜻 입력 : ");
                    String newMeaning = in.nextLine();
                    manager.addWord(addLevel, newWord, newMeaning);
                    break;
                case 5:
                    System.out.print("\n=> 수정할 단어 검색 : ");
                    String modifyWord = in.nextLine();
                    List<Word> modifyResults = manager.search(modifyWord);

                    if (modifyResults.isEmpty()) {
                        System.out.println("검색한 단어가 존재하지 않습니다");
                    } else {
                        System.out.println("--------------------------------");
                        for (int i = 0; i < modifyResults.size(); i++) {
                            System.out.println((i + 1) + " * " + modifyResults.get(i));
                        }
                        System.out.println("--------------------------------");
                        System.out.print("=> 수정할 번호 선택 : ");
                        int modifyChoice = in.nextInt();
                        in.nextLine();

                        if (modifyChoice > 0 && modifyChoice <= modifyResults.size()) {
                            Word selectedWord = modifyResults.get(modifyChoice - 1);
                            System.out.print("=> 뜻 입력 : ");
                            String modifyMeaning = in.nextLine();
                            manager.modifyWord(selectedWord.getId(), selectedWord.getWord(), modifyMeaning);
                        } else {
                            System.out.println("잘못된 선택입니다.");
                        }
                    }
                    break;
                case 6:
                    System.out.print("\n=> 삭제할 단어 검색 : ");
                    String deleteWord = in.nextLine();
                    List<Word> deleteResults = manager.search(deleteWord);

                    if (deleteResults.isEmpty()) {
                        System.out.println("검색한 단어가 존재하지 않습니다.");
                    } else {
                        System.out.println("-------------------------------");
                        for (int i = 0; i < deleteResults.size(); i++) {
                            System.out.println((i + 1) + " * " + deleteResults.get(i));
                        }
                        System.out.println("-------------------------------");
                        System.out.println("=> 삭제할 번호 선택 : ");
                        int deleteChoice = in.nextInt();
                        in.nextLine();

                        if (deleteChoice > 0 && deleteChoice <= deleteResults.size()) {
                            Word selectedWord = deleteResults.get(deleteChoice - 1);
                            manager.deleteWord(selectedWord.getId());
                            System.out.println("* 삭제 성공!");
                        }
                        else {
                            System.out.println("잘못된 선택입니다.");
                        }
                    }
                    break;
                case 7:
                    manager.saveFile(("./src/main/java/org/example/WordList"));
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
        in.close();
    }
}