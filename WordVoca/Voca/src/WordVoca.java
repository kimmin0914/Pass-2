import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class WordVoca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap voca = new HashMap<>();

        voca.put("apple", "사과");
        voca.put("banana", "바나나");
        voca.put("computer", "컴퓨터");

        while (true) {
            System.out.println("\n=== 나만의 영단어장 ===");
            System.out.println("1. 단어 추가  2. 단어 검색  3. 랜덤 퀴즈  4. 종료");
            System.out.print("메뉴 선택 > ");
            String menu = scanner.nextLine();

            if (menu.equals("1")) {
                System.out.print("영어 단어 입력: ");
                String eng = scanner.nextLine();
                System.out.print("한글 뜻 입력: ");
                String kor = scanner.nextLine();

                voca.put(eng, kor);
                System.out.println("단어 저장 완료!");

            } else if (menu.equals("2")) {
                System.out.print("검색할 영어 단어: ");
                String eng = scanner.nextLine();

                if (voca.containsKey(eng)) {
                    System.out.println("검색 결과: " + voca.get(eng));
                } else {
                    System.out.println("사전에 등록되지 않은 단어입니다.");
                }

            } else if (menu.equals("3")) {
                if (voca.isEmpty()) {
                    System.out.println("단어장이 비어있습니다.");
                    continue;
                }

                ArrayList keys = new ArrayList<>(voca.keySet());
                Collections.shuffle(keys);
                String question = (String) keys.get(0);

                System.out.print("[" + question + "] 의 뜻은? > ");
                String answer = scanner.nextLine();

                if (answer.equals(voca.get(question))) {
                    System.out.println("정답입니다! 🎉");
                } else {
                    System.out.println("오답입니다. 정답은 '" + voca.get(question) + "' 입니다.");
                }

            } else if (menu.equals("4")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
        scanner.close();
    }
}