package poker;

// Main 클래스 시작: 프로그램이 가장 먼저 실행되는 출발점 (무대)
public class Main {

    // main 메서드 시작: 자바 프로그램의 전원 스위치 역할
    public static void main(String[] args) {

        // Game 클래스를 설계도 삼아 'vipRoom'이라는 실제 게임 방(객체)을 하나 만듦
        Game vipRoom = new Game();

        // 만들어진 vipRoom 방의 리모컨 버튼(start 메서드)을 눌러서 게임을 본격적으로 가동!
        vipRoom.start();

    } // main 메서드 종료

} // Main 클래스 종료