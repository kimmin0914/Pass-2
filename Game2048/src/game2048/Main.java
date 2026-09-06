package game2048;

// 프로그램이 맨 처음 실행되는 진입점 클래스입니다.
public class Main {

    // 자바 프로그램의 전원을 켜는 메인 메서드입니다.
    public static void main(String[] args) {

        // Game 클래스의 설계도를 바탕으로 실제 게임 진행자(game) 객체를 하나 생성합니다.
        Game game = new Game();

        // 생성된 게임 객체의 start 메서드를 호출해서 본격적인 게임의 쳇바퀴를 돌리기 시작합니다.
        game.start();
    }
}