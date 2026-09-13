package pokemon;

// Pokemon 클래스를 상속받아 확장한 전설 포켓몬 클래스입니다.
public class LegendaryPokemon extends Pokemon {
    // 전설 포켓몬만의 고유 필드입니다.
    private String specialMove;

    // 부모 생성자(super)를 호출하고 고유 필드를 초기화합니다.
    public LegendaryPokemon(String name, String type, int level, String specialMove) {
        super(name, type, level);
        this.specialMove = specialMove;
    }

    public String getSpecialMove() {
        return this.specialMove;
    }

    public void setSpecialMove(String specialMove) {
        if (specialMove != null && !specialMove.trim().isEmpty()) {
            this.specialMove = specialMove;
        }
    }

    // 부모의 printInfo 메서드를 재정의(@Override)하여 전용기를 함께 출력합니다.
    @Override
    public void printInfo() {
        System.out.println("[전설 / " + getType() + "] " + getName() + " (Lv." + getLevel() + ") | 전용기: " + this.specialMove);
    }
}