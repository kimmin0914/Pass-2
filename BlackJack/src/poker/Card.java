package poker;

// Card 클래스 시작
public class Card {

    // [필드 선언: 카드가 가지는 3가지 속성]
    String shape;   // 카드의 무늬를 저장하는 변수 (예: "♠️", "♥️")
    String display; // 화면에 보여질 카드의 글자를 저장하는 변수 (예: "A", "7", "K")
    int score;      // 게임에서 실제로 계산될 점수를 저장하는 변수 (예: 11, 7, 10)

    // 생성자 시작: 새로운 카드를 찍어낼 때 초기값을 세팅해 주는 역할
    public Card(String shape, String display, int score){
        this.shape = shape;       // 외부에서 전달받은 무늬를 내 카드 무늬로 저장
        this.display = display;   // 외부에서 전달받은 글자를 내 카드 글자로 저장
        this.score = score;       // 외부에서 전달받은 점수를 내 카드 점수로 저장
    } // 생성자 종료

} // Card 클래스 종료