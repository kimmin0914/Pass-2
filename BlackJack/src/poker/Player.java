package poker;

// Player 클래스 시작
public class Player {

    // [필드 선언: 플레이어가 가지는 4가지 상태]
    String name;      // 플레이어의 이름 ("딜러: ", "나: " 등을 저장)
    int sumScore = 0; // 플레이어가 지금까지 받은 카드의 총합 점수
    int money = 10000; // 플레이어가 현재 들고 있는 게임 머니 잔액 (기본 1만 원)
    int betting = 0;   // 이번 판에 플레이어가 걸어둔 배팅 금액

    // 생성자 시작: 처음 참가자가 만들어질 때 이름을 지어줌
    public Player(String inputName){
        name = inputName;
    } // 생성자 종료

    // placeBet 메서드 시작: 배팅을 하면 내 돈에서 빼고 배팅금에 올려두는 기능
    public void placeBet(int amount){
        money = money - amount;
        betting = amount;

        System.out.println(name + amount + "원을 배팅했습니다. (남은 돈: " + money + "원)");
    } // placeBet 메서드 종료

    // receiveCard 메서드 시작
    public void receiveCard(Card newCard) {
        sumScore = sumScore + newCard.score; // 새로 받은 카드의 점수를 내 총점에 더함

        // 방금 받은 카드의 무늬(shape)와 이름(display)을 꺼내서 점수와 함께 출력합니다!
        System.out.println(name + "[" + newCard.shape + " " + newCard.display + "] 카드를 받았습니다. (현재 점수: " + sumScore + "점)");
    } // receiveCard 메서드 종료

} // Player 클래스 종료