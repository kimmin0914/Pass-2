package poker;

// Deck 클래스 시작: 52장의 카드를 찍어내고 관리하는 '카드 상자' 역할
public class Deck {

    // [필드 선언: 덱이 가지고 있는 상태]
    Card[] cards = new Card[52]; // 52장의 카드 부품을 순서대로 담아둘 거대한 상자(배열)
    int nextCardIndex;           // 다음번에 뽑아줄 카드의 위치(순번)를 기억하는 변수

    // 생성자 시작: 게임이 시작될 때 카드를 52장 조립해서 상자에 채워 넣는 작업
    public Deck() {
        String[] shapes = {"♠️", "♥️", "♦️", "♣️"}; // 4가지 무늬
        String[] displays = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; // 13가지 이름
        int[] scores = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}; // 13가지 이름에 딱 맞는 점수들

        int index = 0; // 0번부터 51번까지 카드를 차곡차곡 쌓기 위한 위치 변수

        // 무늬 4번 × 이름 13번 = 총 52번 쳇바퀴를 돌며 카드 생성
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 13; j++){
                // 재료들을 조합해서 새 카드를 만들고 상자(cards)에 쏙 넣음
                cards[index] = new Card(shapes[i], displays[j], scores[j]);
                index = index + 1; // 다음 빈칸으로 이동!
            }
        }
    } // 생성자 종료

    // shuffle 메서드 시작: 상자 안의 52장 카드를 무작위로 섞는 기능
    void shuffle() {
        for (int i = 0; i < 52; i++) {
            int randomIndex = (int) (Math.random() * 52); // 0~51 사이의 랜덤한 숫자 뽑기

            // i번째 카드와 랜덤한 위치의 카드를 서로 맞바꾸기(Swap)
            Card tempCard = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = tempCard;
        }

        System.out.println("카드를 섞었습니다.");
    } // shuffle 메서드 종료

    // draw 메서드 시작: 맨 위에서부터 카드를 한 장씩 꺼내서 건네주는 기능
    public Card draw() {
        Card pickedCard = cards[nextCardIndex]; // 이번에 뽑을 카드를 꺼냄
        nextCardIndex = nextCardIndex + 1;      // "다음엔 그 밑에 장 뽑아야지~" 하고 순서를 1 증가시킴
        return pickedCard;                      // 꺼낸 카드를 요청한 사람에게 던져줌!
    } // draw 메서드 종료

} // Deck 클래스 종료