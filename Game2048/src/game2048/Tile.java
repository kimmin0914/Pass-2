package game2048;

// 보드판의 한 칸을 구성하는 타일 객체 설계도입니다.
public class Tile {

    // 이 타일이 현재 가지고 있는 숫자를 저장할 변수입니다. (빈칸은 0을 의미합니다)
    int value;

    // 타일 객체를 처음 만들 때, 어떤 숫자를 넣을지 외부에서 받아서 세팅하는 생성자입니다.
    public Tile(int value) {
        // 외부에서 전달받은 value 값을 이 객체의 value 변수에 덮어씌웁니다.
        this.value = value;
    }
}