package org.example;

import java.util.List;
import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
           WordManager manager = new WordManager();
           Scanner in = new Scanner(System.in);

           System.out.println("*** MY VOCA ***");
           while (true) {
               System.out.println("1.List 2.List(level) 3.Search 4.Add 5.Modify 6.Delete 7.Save file 0.Exit\n");
               System.out.print("=> 원하는 메뉴는? ");
               int choice = in.nextInt();
               in.nextLine();

               switch (choice) {
                   case 1:
                       manager.listWord();
                       break;
                   case 2:
                       System.out.print("=> 레벨(1:초급, 2:중급, 3:고급) 선택 : ");
                       int level = in.nextInt();
                       in.nextLine();
                       manager.listWordByLevel(level);
                       break;
                   case 3:
                       System.out.print("=> 검색할 단어 입력 : ");
                       String keyword = in.nextLine();
                       manager.searchWord(keyword);
                       break;
                   case 4:
                       System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
                       int addlevel = Integer.parseInt(in.next());
                       in.nextLine();
                       String word = in.nextLine();
                       System.out.print("뜻 입력 : ");
                       String meaning = in.nextLine();
                       manager.addWord(word, meaning, addlevel);
                       System.out.println("* 추가 성공!");
                       break;
                   case 5:
                       System.out.print("=> 수정할 단어 검색 : ");
                       String mKeyword = in.nextLine();
                       List<Word> words = manager.searchWord(mKeyword);

                       if (words.isEmpty()) {
                           System.out.println("검색한 단어가 없습니다.");
                           break;
                       }

                       System.out.println("--------------------------------");
                       for (int i=0; i<words.size(); i++) {
                           Word w = words.get(i);
                           System.out.println((i + 1) + " * " + w.getWord() + " " + w.getMeaning());
                       }
                       System.out.println("--------------------------------");

                       System.out.print("=> 수정할 번호 선택 : ");
                       int mIndex = in.nextInt();
                       in.nextLine();
                       if (mIndex < 1 || mIndex > words.size()) {
                           System.out.println("유효하지 않은 번호입니다.");
                           break;
                       }

                       Word selectedWord = words.get(mIndex - 1);

                       System.out.println("=> 뜻 입력 : ");
                       String newMeaning = in.nextLine();

                       manager.modifyWord(selectedWord.getId(), selectedWord.getWord(), newMeaning, selectedWord.getLevel());
                       System.out.println("* 수정 성공!");
                       break;
               }
        }
    }
}