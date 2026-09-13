package pokemon;

public class Pokemon {
    // 외부의 무분별한 접근을 막기 위해 필드를 은닉합니다.
    private String name;
    private String type;
    private int level;

    // 객체 생성 시 데이터를 주입하는 생성자입니다.
    public Pokemon(String name, String type, int level) {
        this.name = name;
        this.type = type;
        setLevel(level);
    }

    // 이름을 조회하는 Getter입니다.
    public String getName() {
        return this.name;
    }

    // 이름을 변경하는 Setter입니다.
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    // 속성을 조회하는 Getter입니다.
    public String getType() {
        return this.type;
    }

    // 속성을 변경하는 Setter입니다.
    public void setType(String type) {
        if (type != null && !type.trim().isEmpty()) {
            this.type = type;
        }
    }

    // 레벨을 조회하는 Getter입니다.
    public int getLevel() {
        return this.level;
    }

    // 유효성 검사 로직이 포함된 레벨 Setter입니다.
    public void setLevel(int level) {
        if (level >= 1) {
            this.level = level;
        } else {
            this.level = 1;
            System.out.println("레벨은 1 이상이어야 하므로 기본값 1로 설정됩니다.");
        }
    }

    // 포켓몬 기본 정보 출력 메서드입니다.
    public void printInfo() {
        System.out.println("[" + this.type + "] " + this.name + " (Lv." + this.level + ")");
    }
}