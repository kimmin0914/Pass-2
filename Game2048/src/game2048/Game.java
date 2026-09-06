package game2048;

// 입력을 받기 위한 스캐너 도구를 자바 기본 라이브러리에서 가져옵니다.
import java.util.Scanner;

// 게임 전체의 흐름(입력, 출력, 판정)을 담당하는 클래스입니다.
public class Game {

    // 실제 타일들이 움직이는 '보드판' 부품을 담아둘 변수입니다.
    Board board;

    // Game 객체가 처음 만들어질 때 실행되는 생성자입니다.
    public Game() {
        // 새로운 보드판 객체를 생성해서 변수에 쏙 넣어줍니다. (이때 무작위 타일 2개가 배치됩니다)
        board = new Board();
    }

    // 실제 게임이 끝날 때까지 반복되는 메인 로직입니다.
    public void start() {
        // 사용자 키보드 입력을 읽어들일 스캐너 객체를 만듭니다.
        Scanner scanner = new Scanner(System.in);

        // 무한 반복하는 쳇바퀴입니다. 게임 오버나 종료를 누르기 전까지 계속 돕니다.
        while (true) {
            // 현재 타일들이 어떻게 배치되어 있는지 화면에 글자로 그려줍니다.
            board.printBoard();

            // 보드판 객체에게 "지금 타일이 꽉 차서 아무 데로도 못 움직여?"라고 물어봅니다.
            // isGameOver() 메서드가 true(막힘)를 반환하면 if문 안으로 들어갑니다.
            if (board.isGameOver()) {
                // 더 이상 움직일 수 없으므로 차분하게 종료 멘트를 띄웁니다.
                System.out.println("게임 오버. 더 이상 움직일 수 없습니다.");
                // 무한 반복문(while)을 강제로 깨고(break) 게임을 완전히 끝냅니다.
                break;
            }

            // 사용자에게 어떤 방향으로 밀지 물어보는 안내 멘트입니다.
            System.out.print("방향 입력 (w:위, s:아래, a:왼쪽, d:오른쪽, q:종료) > ");

            // 사용자가 입력한 글자를 가져오고, 대문자로 입력했더라도 오류가 안 나게 전부 소문자로 바꿉니다.
            String input = scanner.nextLine().toLowerCase();

            // 만약 사용자가 'q'를 입력했다면?
            if (input.equals("q")) {
                // 종료 멘트를 띄웁니다.
                System.out.println("게임을 종료합니다.");
                // 게임 루프를 빠져나갑니다.
                break;
            }

            // 💡 여기의 moved는 "이번 턴에 타일이 한 칸이라도 위치를 바꿨나?"를 기억하는 스위치입니다.
            // 아직 키를 누르기 직전이므로, 처음에는 당연히 "안 움직였음(false)" 상태로 세팅해 둡니다.
            boolean isMoved = false;

            // 입력한 방향에 따라 보드판에게 밀라고 명령하고, 그 결과(움직였는지 안 움직였는지 boolean 값)를 isMoved에 덮어씌웁니다.
            if (input.equals("w")) {
                // 보드판 위로 밀기를 실행하고, 성공해서 움직였다면 true, 막혀서 변화가 없었다면 false를 돌려받습니다.
                isMoved = board.moveUp();
            } else if (input.equals("s")) {
                // 아래로 밀기를 실행합니다.
                isMoved = board.moveDown();
            } else if (input.equals("a")) {
                // 왼쪽으로 밀기를 실행합니다.
                isMoved = board.moveLeft();
            } else if (input.equals("d")) {
                // 오른쪽으로 밀기를 실행합니다.
                isMoved = board.moveRight();
            } else {
                // w, a, s, d, q 외에 이상한 글자를 쳤을 때의 처리입니다.
                System.out.println("잘못된 입력입니다.");
                // 아래 코드는 무시하고 다시 while문 맨 위(보드판 출력)로 돌아가서 키를 다시 받게 합니다.
                continue;
            }

            // 만약 방금 내린 이동 명령으로 타일이 진짜로 한 칸이라도 밀리거나 합쳐졌다면?(true)
            if (isMoved) {
                // 움직여서 생긴 빈 공간 어딘가에 숫자 2나 4를 새롭게 하나 스폰(생성)합니다.
                board.addRandomTile();
            } else {
                // 입력한 방향으로 전부 벽에 막혀서 아무 타일도 이동하지 못했을 때(false)의 멘트입니다.
                System.out.println("그 방향으로는 타일을 밀 수 없습니다.");
            }
        }
    }
}