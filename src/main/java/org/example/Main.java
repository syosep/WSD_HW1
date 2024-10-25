package org.example;

import java.util.List;
import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
           WordManager manager = new WordManager();
           Scanner in = new Scanner(System.in);

           System.out.println("*** MY VOCA **\n");
           while (true) {
               System.out.println("1.List 2.List(level) 3.Search 4.Add 5.Modify 6.Delete 7.Save file 0.Exit\n");
               System.out.print("=> 원하는 메뉴는? ");
               int choice = in.nextInt();
               in.nextLine();

               switch (choice) {
                   case 1:
                       manager.loadFile("./src/main/java/org/example/WordList");
                       System.out.println("--------------------------------");
                       manager.listWord();
                       System.out.println("--------------------------------");
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
                       String input = in.nextLine();
                       String[] parts = input.split(" ", 2);

                       if (parts.length < 2) {
                           System.out.println("잘못된 입력입니다.");
                           break;
                       }

                       int addlevel;
                       try {
                           addlevel = Integer.parseInt(parts[0]);
                       } catch (NumberFormatException e) {
                           System.out.println("잘못된 입력입니다.");
                           break;
                       }

                       String word = parts[1];

                       System.out.print("=> 뜻 입력 : ");
                       String meaning = in.nextLine();

                       manager.addWord(word, meaning, addlevel);
                       System.out.println("* 추가 성공!");
                       break;
                   case 5:
                       System.out.print("=> 수정할 단어 검색 : ");
                       String mKeyword = in.nextLine();
                       List<Word> mWord = manager.searchWord(mKeyword);

                       if (mWord.isEmpty()) {
                           System.out.println("검색한 단어가 없습니다.");
                           break;
                       }

                       System.out.println("--------------------------------");
                       for (int i=0; i<mWord.size(); i++) {
                           Word w = mWord.get(i);
                           System.out.println((i + 1) + " * " + w.getWord() + " " + w.getMeaning());
                       }
                       System.out.println("--------------------------------");

                       System.out.print("=> 수정할 번호 선택 : ");
                       int mIndex = in.nextInt();
                       in.nextLine();
                       if (mIndex < 1 || mIndex > mWord.size()) {
                           System.out.println("유효하지 않은 번호입니다.");
                           break;
                       }

                       Word selectedMWord = mWord.get(mIndex - 1);

                       System.out.println("=> 뜻 입력 : ");
                       String newMeaning = in.nextLine();

                       manager.modifyWord(selectedMWord.getId(), selectedMWord.getWord(), newMeaning, selectedMWord.getLevel());
                       System.out.println("* 수정 성공!");
                       break;
                   case 6:
                       System.out.print("=> 삭제할 단어 검색 : ");
                       String dKeyword = in.nextLine();
                       List<Word> dWord = manager.searchWord(dKeyword);

                       if (dWord.isEmpty()) {
                           System.out.println("검색한 단어가 없습니다.");
                           break;
                       }

                       System.out.println("--------------------------------");
                       for (int i=0; i<dWord.size(); i++) {
                           Word w = dWord.get(i);
                           System.out.println((i + 1) + " * " + w.getWord() + " " + w.getMeaning());
                       }
                       System.out.println("--------------------------------");

                       System.out.print("=> 수정할 번호 선택 : ");
                       int dIndex = in.nextInt();
                       in.nextLine();
                       if (dIndex < 1 || dIndex > dWord.size()) {
                           System.out.println("유효하지 않은 번호입니다.");
                           break;
                       }

                       Word selectedDWord = dWord.get(dIndex - 1);
                       manager.deleteWord(selectedDWord.getId());
                       System.out.println("* 삭제 성공!");
                       break;
                   case 7:
                       if (manager.saveFile()) {
                           System.out.println("* 파일저장 완료!");
                       }
                       break;
                   case 0:
                       System.out.println("프로그램 종료! 다음에 만나요~");
                       return;
                   default:
                       System.out.println("유효하지 않은 메뉴입니다. 다시 시도해주세요.");
               }
        }
    }
}