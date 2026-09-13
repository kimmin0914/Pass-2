package bomb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BombGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 💡 오직 문자열만 담는 가방! (오른쪽  잊지 마세요!)
        ArrayList wires = new ArrayList();

        // 가방에 전선의 '결과'만 몰래 집어넣습니다. (색상 힌트 제거)
        wires.add("폭발");
        wires.add("해제");
        wires.add("랜덤"); // 행운 or 불행
        wires.add("안전");
        wires.add("안전");

        // 💡 [핵심] 가방 안의 결과들을 매 판마다 완벽하게 무작위로 섞습니다.
        Collections.shuffle(wires);

        int turnsLeft = 3; // 남은 기회 3번

        System.out.println("🚨 시한폭탄 해제 미션 시작! (기회: " + turnsLeft + "번)\n");

        while (turnsLeft > 0) {
            System.out.println("==== 남은 전선 목록 ====");
            // 💡 [힌트 완전 제거] 정답을 화면에 출력하지 않고 'N번 전선'으로만 보여줍니다.
            for (int i = 0; i < wires.size(); i++) {
                System.out.println(" ⚡ " + (i + 1) + "번 전선");
            }
            System.out.println("========================");

            System.out.print("자를 전선 번호를 입력하십시오 (1-" + wires.size() + ") > ");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > wires.size()) {
                System.out.println("잘못된 번호입니다.\n");
                continue;
            }

            // 가방에서 선택한 번호를 꺼냅니다. (인텔리제이 에러 방지용 (String) 유지)
            String cutWire = (String) wires.remove(choice - 1);
            System.out.println("\n✂️ [" + choice + "번 전선]을(를) 잘랐습니다...");

            // 숨겨져 있던 글자가 무엇인지(equals)로 결과를 판정합니다!
            if (cutWire.equals("폭발")) {
                System.out.println("💥 쾅! 폭발선이었습니다! [게임 오버]");
                return;

            } else if (cutWire.equals("해제")) {
                System.out.println("🎉 삐- 해제 성공! 도시를 구했습니다! [게임 승리]");
                return;

            } else if (cutWire.equals("랜덤")) {
                System.out.println("❓ [특수 전선] 알 수 없는 전류가 흐릅니다...");
                int randomNum = (int) (Math.random() * 2);

                if (randomNum == 0) {
                    turnsLeft++;
                    System.out.println("🍀 럭키! 타이머가 지연되어 남은 기회가 1번 늘어납니다! (남은 기회: " + turnsLeft + "번)\n");
                } else {
                    turnsLeft--;
                    System.out.println("💀 앗! 타이머가 빨라집니다! 남은 기회가 1번 줄어듭니다! (남은 기회: " + turnsLeft + "번)\n");
                }

            } else {
                turnsLeft--;
                System.out.println("휴... 안전선이었습니다. (남은 기회: " + turnsLeft + "번)\n");
            }
        }

        if (turnsLeft <= 0) {
            System.out.println("⏰ 00:00! 시간 초과로 폭탄이 터졌습니다! [게임 오버]");
        }
    }
}