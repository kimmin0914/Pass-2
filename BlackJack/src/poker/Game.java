package poker;

import java.util.Scanner;

// Game 클래스 시작
public class Game {

    // 필드(멤버 변수) 선언
    Deck roomDeck;
    Player dealer;
    Player me;

    // 생성자 시작: 게임 방 세팅
    public Game(){
        roomDeck = new Deck();
        dealer = new Player("딜러: ");
        me = new Player("나: ");
    }

    // start 메서드 시작: 게임 메인 흐름
    public void start() {
        System.out.println("====== 블랙잭 게임을 시작합니다. =====");

        // 입력 객체 생성
        Scanner input = new Scanner(System.in);

        // 전체 게임 루프 시작
        while (true) {

            // 매 판 데이터 초기화
            roomDeck = new Deck();
            roomDeck.shuffle();
            me.sumScore = 0;
            dealer.sumScore = 0;

            // 배팅 로직 시작
            System.out.println("\n현재 남은 돈: " + me.money + "원\n배팅할 금액을 입력해주세요. > ");
            int myBet = input.nextInt();
            input.nextLine();

            me.placeBet(myBet);

            // 초기 카드 분배 시작
            System.out.println("\n ---- 딜러가 카드를 나눠줍니다. ----");
            Card firstCard = roomDeck.draw();
            dealer.receiveCard(firstCard);
            me.receiveCard(roomDeck.draw());

            // 플레이어 턴 루프 시작 (Hit or Stand)
            while (true) {
                System.out.println("\n 카드를 더 받으시겠습니까? (1: Hit, 2: Stand) > ");
                String choice = input.nextLine();

                if (choice.equals("1")) {
                    me.receiveCard(roomDeck.draw());

                    if(me.sumScore > 21) {
                        System.out.println("Bust! 딜러 Win!");
                        break;
                    }
                } else if (choice.equals("2")) {
                    System.out.println("Stand!");
                    break;
                } else {
                    System.out.println("1 또는 2만 입력해주세요.");
                }
            } // 플레이어 턴 루프 종료

            // 딜러 턴 및 승패 판정 시작
            if (me.sumScore <= 21){
                System.out.println("\n---- 딜러 턴 ----");
                while (dealer.sumScore <= 16){
                    System.out.println("딜러가 카드를 추가로 뽑습니다.");
                    dealer.receiveCard(roomDeck.draw());
                }

                System.out.println("\n ==== 최종 결과 ====");

                if(dealer.sumScore > 21){
                    System.out.println("딜러 Bust! 플레이어 Win!");
                    me.money = me.money + (me.betting * 2);
                } else if (me.sumScore > dealer.sumScore) {
                    System.out.println("플레이어 Win!");
                    me.money = me.money + (me.betting * 2);

                } else if (me.sumScore < dealer.sumScore) {
                    System.out.println("딜러 Win!");

                } else {
                    System.out.println("동점입니다.");
                    me.money = me.money + me.betting;
                }
            }

            System.out.println("현재 잔액: " + me.money + "원");

            // 파산 확인 로직 시작
            if (me.money <= 0) {
                System.out.println("\n파산하셨습니다. 게임을 종료합니다.");
                break;
            }

            // 재시작 여부 확인 로직 시작
            System.out.println("\n한 판 더 하시겠습니까? (1: 예, 2: 아니오) > ");
            String replayChoice = input.nextLine();

            if (replayChoice.equals("2")) {
                System.out.println("게임을 종료합니다. 최종 잔액: " + me.money + "원");
                System.out.println("수고하셨습니다!");
                break;
            }

        } // 전체 게임 루프 종료
    } // start 메서드 종료
} // Game 클래스 종료