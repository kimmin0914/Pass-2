package pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class PokemonParty {

    // 💡 포켓몬 객체들을 유연하게 관리하는 핵심 저장소입니다.
    private ArrayList party;
    private Scanner scanner;

    public PokemonParty() {
        party = new ArrayList<>();
        scanner = new Scanner(System.in);

        // 초기 데이터 (다형성: 일반 포켓몬과 전설 포켓몬을 동일한 리스트에 등록)
        party.add(new Pokemon("피카츄", "전기", 25));
        party.add(new Pokemon("파이리", "불꽃", 15));
        party.add(new LegendaryPokemon("뮤츠", "에스퍼", 70, "사이코브레이크"));
    }

    // 메뉴 루프 메서드
    public void start() {
        while (true) {
            System.out.println("\n===== 포켓몬 엔트리 관리 시스템 =====");
            System.out.println("1. 파티 현황 조회");
            System.out.println("2. 일반 포켓몬 영입");
            System.out.println("3. 전설 포켓몬 영입");
            System.out.println("4. 포켓몬 방생");
            System.out.println("5. 파티 순서 교체");
            System.out.println("6. 포켓몬 레벨 훈련");
            System.out.println("7. 프로그램 종료");
            System.out.print("메뉴를 선택하십시오 > ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    printParty();
                    break;
                case 2:
                    catchPokemon();
                    break;
                case 3:
                    summonLegendary();
                    break;
                case 4:
                    releasePokemon();
                    break;
                case 5:
                    swapPokemon();
                    break;
                case 6:
                    trainPokemon();
                    break;
                case 7:
                    System.out.println("포켓몬 관리 시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("목록에 있는 번호만 입력해 주십시오.");
                    break;
            }
        }
    }

    // 1. 조회: size()와 get(index)
    public void printParty() {
        if (party.isEmpty()) {
            System.out.println("보유 중인 포켓몬이 없습니다.");
            return;
        }

        System.out.println("\n==== 현재 파티 목록 (총 " + party.size() + "마리) ====");
        for (int i = 0; i < party.size(); i++) {
            System.out.print((i + 1) + "번 엔트리: ");
            // 다형성: 객체 타입에 따라 알맞은 printInfo() 자동 실행
            party.get(i).printInfo();
        }
        System.out.println("==========================================");
    }

    // 2. 추가: add(element)
    public void catchPokemon() {
        System.out.print("포획한 포켓몬 이름 > ");
        String name = scanner.nextLine();

        System.out.print("속성 > ");
        String type = scanner.nextLine();

        System.out.print("레벨 > ");
        int level = scanner.nextInt();
        scanner.nextLine();

        party.add(new Pokemon(name, type, level));
        System.out.println(name + "을(를) 파티에 영입했습니다.");
    }

    // 3. 자식 객체 추가 (다형성 활용)
    public void summonLegendary() {
        System.out.print("전설 포켓몬 이름 > ");
        String name = scanner.nextLine();

        System.out.print("속성 > ");
        String type = scanner.nextLine();

        System.out.print("레벨 > ");
        int level = scanner.nextInt();
        scanner.nextLine();

        System.out.print("전용 기술명 > ");
        String specialMove = scanner.nextLine();

        party.add(new LegendaryPokemon(name, type, level, specialMove));
        System.out.println("전설의 포켓몬 " + name + "이(가) 합류했습니다.");
    }

    // 4. 삭제: remove(index)
    public void releasePokemon() {
        if (party.isEmpty()) {
            System.out.println("방생할 포켓몬이 없습니다.");
            return;
        }

        System.out.print("방생할 포켓몬 이름 > ");
        String targetName = scanner.nextLine();

        boolean removed = false;
        for (int i = 0; i < party.size(); i++) {
            if (party.get(i).getName().equals(targetName)) {
                // remove 실행 시 뒤쪽 요소들이 자동으로 한 칸씩 당겨집니다.
                Pokemon released = party.remove(i);
                System.out.println(released.getName() + "을(를) 자연으로 방생했습니다.");
                removed = true;
                break;
            }
        }

        if (!removed) {
            System.out.println("해당 이름의 포켓몬이 파티에 존재하지 않습니다.");
        }
    }

    // 5. 교체: set(index, element)
    public void swapPokemon() {
        if (party.size() < 2) {
            System.out.println("교체할 수 있는 포켓몬이 충분하지 않습니다.");
            return;
        }

        System.out.print("첫 번째 포켓몬 번호 (1-" + party.size() + ") > ");
        int firstChoice = scanner.nextInt();

        System.out.print("두 번째 포켓몬 번호 (1-" + party.size() + ") > ");
        int secondChoice = scanner.nextInt();
        scanner.nextLine();

        boolean isFirstValid = (firstChoice >= 1 && firstChoice <= party.size());
        boolean isSecondValid = (secondChoice >= 1 && secondChoice <= party.size());

        if (!isFirstValid || !isSecondValid) {
            System.out.println("유효하지 않은 번호입니다. 교체를 취소합니다.");
            return;
        }

        if (firstChoice == secondChoice) {
            System.out.println("동일한 포켓몬을 선택했습니다.");
            return;
        }

        int firstIndex = firstChoice - 1;
        int secondIndex = secondChoice - 1;

        Pokemon temp = party.get(firstIndex);
        party.set(firstIndex, party.get(secondIndex));
        party.set(secondIndex, temp);

        System.out.println(firstChoice + "번과 " + secondChoice + "번 포켓몬의 위치를 교체했습니다.");
    }

    // 6. 훈련: Getter / Setter 조합
    public void trainPokemon() {
        if (party.isEmpty()) {
            System.out.println("훈련할 포켓몬이 없습니다.");
            return;
        }

        System.out.print("훈련시킬 포켓몬 번호 (1-" + party.size() + ") > ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > party.size()) {
            System.out.println("유효하지 않은 번호입니다.");
            return;
        }

        Pokemon target = party.get(choice - 1);
        target.setLevel(target.getLevel() + 1);

        System.out.println(target.getName() + "의 훈련이 완료되었습니다. (현재 레벨: " + target.getLevel() + ")");
    }
}