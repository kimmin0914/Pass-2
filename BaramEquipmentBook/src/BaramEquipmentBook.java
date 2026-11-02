import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BaramEquipmentBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 장비 이름이 키, 공격력이 값입니다.
        HashMap<String, Integer> equipmentBook = new HashMap<>();

        equipmentBook.put("목도", 10);
        equipmentBook.put("철도", 35);
        equipmentBook.put("양첨목봉", 120);
        equipmentBook.put("월아검", 180);
        equipmentBook.put("환두대도", 250);
        equipmentBook.put("용마제십검", 1000);

        System.out.println("=== 부여성 대장간 장비 도감 ===");
        System.out.println("※ 실제 게임과 다른 학습용 가상 공격력입니다.");

        while (true) {
            System.out.println("\n========== 대장간 메뉴 ==========");
            System.out.println("1. 전체 장비 보기 (이름순)");
            System.out.println("2. 공격력 높은 순으로 보기");
            System.out.println("3. 공격력 낮은 순으로 보기");
            System.out.println("4. 장비 이름 검색");
            System.out.println("5. 장비 등록 / 공격력 수정");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택 > ");

            if (!scanner.hasNextLine()) break;
            String menu = scanner.nextLine().trim();

            if (menu.equals("1") || menu.equals("2") || menu.equals("3")) {
                // HashMap의 장비 이름들을 ArrayList에 담습니다.
                ArrayList<String> names =
                        new ArrayList<>(equipmentBook.keySet());

                if (menu.equals("1")) {
                    // 이름 오름차순
                    Collections.sort(names);

                    System.out.println("\n[장비 이름순]");

                } else if (menu.equals("2")) {
                    // 공격력 내림차순
                    Collections.sort(names, (name1, name2) ->
                            Integer.compare(
                                    equipmentBook.get(name2),
                                    equipmentBook.get(name1)
                            )
                    );

                    System.out.println("\n[공격력 높은 순]");

                } else {
                    // 공격력 오름차순
                    Collections.sort(names, (name1, name2) ->
                            Integer.compare(
                                    equipmentBook.get(name1),
                                    equipmentBook.get(name2)
                            )
                    );

                    System.out.println("\n[공격력 낮은 순]");
                }

                for (int i = 0; i < names.size(); i++) {
                    String name = names.get(i);

                    System.out.println(
                            (i + 1) + ". " + name
                                    + " | 공격력: " + equipmentBook.get(name)
                    );
                }

            } else if (menu.equals("4")) {
                System.out.print("검색할 이름 또는 일부 글자 > ");

                if (!scanner.hasNextLine()) break;
                String keyword = scanner.nextLine().trim();

                if (keyword.isEmpty()) {
                    System.out.println("검색어를 입력해 주세요.");
                    continue;
                }

                ArrayList<String> names =
                        new ArrayList<>(equipmentBook.keySet());

                Collections.sort(names);

                boolean found = false;

                for (String name : names) {
                    if (name.contains(keyword)) {
                        System.out.println(
                                name + " | 공격력: " + equipmentBook.get(name)
                        );

                        found = true;
                    }
                }

                if (!found) {
                    System.out.println(
                            "대장장이: 그런 장비는 아직 도감에 없소!"
                    );
                }

            } else if (menu.equals("5")) {
                System.out.print("장비 이름 > ");

                if (!scanner.hasNextLine()) break;
                String name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("장비 이름을 입력해 주세요.");
                    continue;
                }

                System.out.print("공격력 (0 이상의 정수) > ");

                if (!scanner.hasNextLine()) break;

                try {
                    int attack =
                            Integer.parseInt(scanner.nextLine().trim());

                    if (attack < 0) {
                        System.out.println(
                                "공격력은 0 이상으로 입력해 주세요."
                        );
                        continue;
                    }

                    boolean exists = equipmentBook.containsKey(name);

                    equipmentBook.put(name, attack);

                    System.out.println(
                            exists ? "공격력 수정 완료!" : "새 장비 등록 완료!"
                    );

                } catch (NumberFormatException e) {
                    System.out.println(
                            "입력 가능한 범위의 정수만 입력해 주세요."
                    );
                }

            } else if (menu.equals("0")) {
                break;

            } else {
                System.out.println("메뉴에 있는 번호를 입력해 주세요.");
            }
        }

        System.out.println("대장장이: 다음에 또 들르시오!");
        scanner.close();
    }
}