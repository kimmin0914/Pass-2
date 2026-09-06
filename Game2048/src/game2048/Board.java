package game2048;

// 실제 4x4 게임판을 만들고 밀고 합치는 모든 두뇌 역할을 하는 클래스입니다.
public class Board {

    // 4개의 가로줄, 4개의 세로줄을 가진 2차원 배열 공간을 선언합니다. 그 안에는 Tile 객체가 들어갑니다.
    Tile[][] grid = new Tile[4][4];

    // 보드판이 세상에 처음 태어날 때 실행되는 초기화 세팅입니다.
    public Board() {
        // 보드판의 4개 가로줄(행)을 0번 줄부터 3번 줄까지 차례대로 내려가기 위한 반복문입니다.
        for (int i = 0; i < 4; i++) {
            // 한 줄(행) 안에서 4개의 칸(열)을 왼쪽부터 오른쪽으로 이동하며 세팅하기 위한 반복문입니다.
            for (int j = 0; j < 4; j++) {
                // 모든 칸에 일단 숫자 0을 가진 새 타일을 만들어서 꽉꽉 채워 넣습니다. (초기화)
                grid[i][j] = new Tile(0);
            }
        }
        // 시작하자마자 썰렁하지 않게 임의의 빈칸에 숫자 타일 2개를 뿅뿅 만들어줍니다.
        addRandomTile();
        addRandomTile();
    }

    // 현재 타일들의 위치와 숫자를 콘솔 창에 텍스트로 그려주는 메서드입니다.
    public void printBoard() {
        // 화면 윗부분 꾸밈 선입니다.
        System.out.println("==== 2048 보드판 ====");
        // 4개의 가로줄을 위에서 아래로 하나씩 그리기 위한 반복문입니다.
        for (int i = 0; i < 4; i++) {
            // 한 줄 안에서 4개의 칸을 순서대로 출력합니다.
            for (int j = 0; j < 4; j++) {
                // 만약 이 칸의 타일 숫자가 0이라면 (빈칸을 의미함)
                if (grid[i][j].value == 0) {
                    // 0 대신에 화면에 [ . ] 모양으로 찍어서 빈 공간임을 예쁘게 보여줍니다. (줄바꿈 안 함)
                    System.out.print("[ . ] ");
                } else {
                    // 0이 아니라 실제 숫자가 있다면 그 숫자를 찍어줍니다.
                    System.out.print("[" + grid[i][j].value + " ] ");
                }
            }
            // 4개 칸(한 줄) 출력이 끝났으므로 엔터를 쳐서 다음 줄로 넘깁니다.
            System.out.println();
        }
        // 화면 아랫부분 꾸밈 선입니다.
        System.out.println("=====================\n");
    }

    // 💡 [핵심] 왼쪽으로 전체 타일을 미는 메서드입니다.
    public boolean moveLeft() {
        // 💡 질문하신 boolean moved = false; 입니다!
        // 이 메서드의 목적은 "왼쪽으로 밀어!" 인데, 만약 이미 다 왼쪽에 붙어있으면 안 움직이겠죠?
        // 그래서 처음에는 "아무 변화도 안 일어났음(false)"으로 깔아두고 시작합니다.
        // 나중에 타일 위치가 한 칸이라도 바뀌거나 숫자가 합쳐지면 그때 true로 바꿀 겁니다.
        boolean moved = false;

        // 4개의 가로줄(행)을 각각 따로따로 검사하기 위해 맨 위 줄부터 맨 아래 줄까지 4번 도는 반복문입니다.
        for (int i = 0; i < 4; i++) {

            // [1단계] 빈칸 없애기 (오른쪽에 있는 숫자를 왼쪽 빈칸으로 당겨오기)
            // 맨 오른쪽 끝 칸(인덱스 3)은 자기보다 더 오른쪽에 당겨올 칸이 없으므로, 0부터 2까지만 기준점(빈칸 후보)으로 잡습니다.
            for (int j = 0; j < 3; j++) {
                // 만약 현재 바라보고 있는 기준칸(j)이 숫자 0(빈칸)이라면?
                if (grid[i][j].value == 0) {
                    // 빈칸의 바로 오른쪽 칸(j+1)부터 맨 끝 칸(인덱스 3)까지 뒤져서 숫자를 찾아옵니다.
                    for (int k = j + 1; k < 4; k++) {
                        // 오! 오른쪽을 뒤지다가 0이 아닌 진짜 숫자가 들어있는 칸(k)을 발견했습니다!
                        if (grid[i][k].value != 0) {
                            // 그 숫자를 왼쪽 빈칸(j)에 덮어씌워서 끌어옵니다.
                            grid[i][j].value = grid[i][k].value;
                            // 끌어왔으니 원래 있던 자리(k)는 0으로 만들어서 비워줍니다.
                            grid[i][k].value = 0;
                            // 타일 위치가 이동했으므로, 처음에 false였던 변수를 true(움직였음)로 바꿔줍니다.
                            moved = true;
                            // 💡 가장 중요한 break의 이유!
                            // 한 빈칸을 메꾸기 위해 오른쪽에서 숫자 하나를 무사히 끌어왔다면,
                            // 더 이상 오른쪽을 뒤질 필요가 없습니다! 안 멈추면 뒤에 있는 숫자를 또 덮어씌워 버리니까요.
                            // 그래서 break로 현재의 탐색(k 반복문)만 중단하고, 다음 기준점(j+1) 검사로 넘어가는 겁니다.
                            break;
                        }
                    }
                }
            }

            // [2단계] 나란히 붙어있는 같은 숫자 합치기
            // 왼쪽부터 오른쪽으로 검사합니다. 마지막 칸은 오른쪽 짝꿍이 없으니 2까지만 돕니다.
            for (int j = 0; j < 3; j++) {
                // 현재 칸(j)이 빈칸이 아니고, 내 숫자와 바로 오른쪽 칸(j+1)의 숫자가 완벽하게 똑같다면?
                if (grid[i][j].value != 0 && grid[i][j].value == grid[i][j+1].value) {
                    // 내 칸의 숫자를 2배로 뻥튀기합니다. (2+2=4 합체 성공!)
                    grid[i][j].value *= 2;
                    // 합쳐져서 내 쪽으로 흡수되었으니, 오른쪽 칸은 0(빈칸)으로 지워버립니다.
                    grid[i][j+1].value = 0;
                    // 타일이 합쳐지는 변화가 생겼으므로 moved를 true로 바꿔줍니다.
                    moved = true;
                }
            }

            // [3단계] 합치면서 중간에 뻥 뚫린 빈칸을 다시 당겨와서 꽉 채우기
            // [1단계]와 완벽하게 똑같은 원리의 당기기 작업입니다.
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].value == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if (grid[i][k].value != 0) {
                            grid[i][j].value = grid[i][k].value;
                            grid[i][k].value = 0;
                            moved = true;
                            break; // 1단계 설명과 같이 하나 당겨오면 k 탐색을 즉시 멈추기 위함입니다.
                        }
                    }
                }
            }
        }
        // 왼쪽으로 밀기 작업이 모두 끝났습니다.
        // 처음 세팅했던 moved(false)가 중간에 한 번이라도 true로 바뀌었다면 true를 리턴하고,
        // 끝까지 안 바뀌었다면(이미 다 왼쪽에 붙어있어서) false를 리턴하여 밖으로 알려줍니다.
        return moved;
    }

    // 💡 오른쪽으로 미는 메서드 (왼쪽 밀기의 거울 모드라고 생각하시면 됩니다)
    public boolean moveRight() {
        // 움직였는지 확인하는 스위치를 꺼두고 시작합니다.
        boolean moved = false;

        // 4개의 가로줄을 하나씩 검사합니다.
        for (int i = 0; i < 4; i++) {

            // 1단계: 당기기.
            // 오른쪽으로 미는 거니까, 맨 오른쪽 끝 칸(인덱스 3)부터 왼쪽 방향(0)으로 깎아 내려가며 기준을 잡습니다.
            for (int j = 3; j > 0; j--) {
                // 오른쪽 기준칸이 비어있다면?
                if (grid[i][j].value == 0) {
                    // 기준칸의 바로 왼쪽 칸(j-1)부터 맨 왼쪽 끝 칸(0)까지 역순으로 뒤집니다.
                    for (int k = j - 1; k >= 0; k--) {
                        // 숫자를 찾았다면?
                        if (grid[i][k].value != 0) {
                            // 오른쪽 끝으로 쭉 끌고 옵니다.
                            grid[i][j].value = grid[i][k].value;
                            grid[i][k].value = 0;
                            moved = true;
                            break; // 하나 끌어왔으니 왼쪽 탐색(k 반복)을 멈춥니다.
                        }
                    }
                }
            }
            // 2단계: 합치기
            // 오른쪽 끝부터 차례대로 자기 왼쪽 칸과 비교해서 같으면 합칩니다.
            for (int j = 3; j > 0; j--) {
                if (grid[i][j].value != 0 && grid[i][j].value == grid[i][j-1].value) {
                    grid[i][j].value *= 2;
                    grid[i][j-1].value = 0;
                    moved = true;
                }
            }
            // 3단계: 다시 꽉 채우기 위해 당기기 (1단계와 동일)
            for (int j = 3; j > 0; j--) {
                if (grid[i][j].value == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (grid[i][k].value != 0) {
                            grid[i][j].value = grid[i][k].value;
                            grid[i][k].value = 0;
                            moved = true;
                            break;
                        }
                    }
                }
            }
        }
        // 움직임 발생 여부를 리턴합니다.
        return moved;
    }

    // 💡 위로 미는 메서드 (가로가 아니라 세로로 훑어 내려갑니다)
    public boolean moveUp() {
        boolean moved = false;

        // 세로줄(열)을 기준으로 검사해야 하므로 j(열) 반복문을 먼저 바깥에 둡니다.
        for (int j = 0; j < 4; j++) {

            // 맨 위쪽 칸(i=0)부터 아래쪽으로 내려가며 빈칸을 찾습니다. 맨 아랫칸은 밑이 없으니 2까지만 돕니다.
            for (int i = 0; i < 3; i++) {
                if (grid[i][j].value == 0) {
                    // 빈칸 바로 밑에 칸(i+1)부터 맨 아래 끝 칸(3)까지 뒤집니다.
                    for (int k = i + 1; k < 4; k++) {
                        // 밑에서 숫자를 발견하면?
                        if (grid[k][j].value != 0) {
                            // 위쪽 빈칸으로 끌어올립니다!
                            grid[i][j].value = grid[k][j].value;
                            grid[k][j].value = 0;
                            moved = true;
                            break; // 하나 끌어올렸으니 탐색 중단.
                        }
                    }
                }
            }
            // 위에서 아래로 내려오며 세로로 인접한 숫자가 같으면 위쪽 칸으로 합칩니다.
            for (int i = 0; i < 3; i++) {
                if (grid[i][j].value != 0 && grid[i][j].value == grid[i+1][j].value) {
                    grid[i][j].value *= 2;
                    grid[i+1][j].value = 0;
                    moved = true;
                }
            }
            // 다시 한번 빈칸 당겨서 메꾸기
            for (int i = 0; i < 3; i++) {
                if (grid[i][j].value == 0) {
                    for (int k = i + 1; k < 4; k++) {
                        if (grid[k][j].value != 0) {
                            grid[i][j].value = grid[k][j].value;
                            grid[k][j].value = 0;
                            moved = true;
                            break;
                        }
                    }
                }
            }
        }
        return moved;
    }

    // 💡 아래로 미는 메서드
    public boolean moveDown() {
        boolean moved = false;
        // 4개의 세로줄 검사
        for (int j = 0; j < 4; j++) {
            // 이번엔 맨 아래칸(i=3)부터 위로 올라가며 빈칸을 봅니다.
            for (int i = 3; i > 0; i--) {
                if (grid[i][j].value == 0) {
                    // 빈칸 바로 위칸(i-1)부터 맨 위칸(0)까지 역순으로 뒤집니다.
                    for (int k = i - 1; k >= 0; k--) {
                        // 위에 숫자가 있으면?
                        if (grid[k][j].value != 0) {
                            // 밑으로 끄집어 내립니다.
                            grid[i][j].value = grid[k][j].value;
                            grid[k][j].value = 0;
                            moved = true;
                            break;
                        }
                    }
                }
            }
            // 맨 아래부터 위로 확인하며 숫자가 같으면 아래쪽으로 합칩니다.
            for (int i = 3; i > 0; i--) {
                if (grid[i][j].value != 0 && grid[i][j].value == grid[i-1][j].value) {
                    grid[i][j].value *= 2;
                    grid[i-1][j].value = 0;
                    moved = true;
                }
            }
            // 다시 당겨오기
            for (int i = 3; i > 0; i--) {
                if (grid[i][j].value == 0) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (grid[k][j].value != 0) {
                            grid[i][j].value = grid[k][j].value;
                            grid[k][j].value = 0;
                            moved = true;
                            break;
                        }
                    }
                }
            }
        }
        return moved;
    }

    // 움직임이 일어난 후, 빈 공간 중 딱 한 곳에 2 또는 4를 소환하는 로직입니다.
    public void addRandomTile() {
        // 현재 보드판에 빈칸(0)이 총 몇 개인지 개수를 담아둘 변수입니다.
        int emptyCount = 0;

        // 전체 16개 칸을 샅샅이 뒤져서 빈칸의 개수를 셉니다.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j].value == 0) {
                    emptyCount++; // 빈칸 하나 발견할 때마다 1씩 증가
                }
            }
        }

        // 만약 빈칸이 하나도 없다면? (16칸 꽉 참)
        // 새로운 타일을 놓을 자리가 없으므로 아무것도 안 하고 메서드를 즉시 종료(return)합니다.
        if (emptyCount == 0) return;

        // 0부터 (빈칸 개수 - 1) 사이에서 무작위 숫자 하나를 뽑습니다.
        // 예: 빈칸이 3개면 0, 1, 2 중 하나가 뽑힙니다. 이것이 타일이 들어갈 "당첨 번호"입니다.
        int targetIndex = (int) (Math.random() * emptyCount);

        // 반복문 돌면서 내가 지금 몇 번째 빈칸을 마주치고 있는지 세기 위한 변수입니다.
        int count = 0;

        // 다시 전체 16칸을 뒤지며 당첨된 빈칸을 찾아 여행을 떠납니다.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 어? 빈칸(0)을 하나 찾았습니다.
                if (grid[i][j].value == 0) {
                    // 이 빈칸의 순번(count)이 아까 뽑은 당첨 번호(targetIndex)랑 똑같다면?
                    if (count == targetIndex) {
                        // 이 자리가 바로 타일이 생성될 당첨 자리입니다!
                        // Math.random()은 0~1 사이의 소수를 뽑는데, 0.1보다 작으면(10% 확률) 4를 주고, 아니면(90%) 2를 줍니다.
                        grid[i][j].value = (Math.random() < 0.1) ? 4 : 2;

                        // 원하던 곳에 타일을 성공적으로 박아넣었으니, 남은 칸은 뒤질 필요 없이 즉시 메서드를 종료(return)합니다.
                        return;
                    }
                    // 빈칸이긴 한데 당첨 번호가 아니라면? 순번만 하나 올리고 계속 다음 칸을 탐색합니다.
                    count++;
                }
            }
        }
    }

    // 타일이 꽉 막혀서 게임이 끝났는지(GameOver) 아닌지 판정해 주는 메서드입니다.
    public boolean isGameOver() {
        // 1. 전체 보드판을 뒤져서 빈칸이 딱 하나라도 있는지 확인합니다.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 빈칸이 있다?
                // 그러면 타일이 밀릴 공간이 있다는 뜻이므로 "아직 안 끝났음(false)"을 즉시 알려주고 빠져나갑니다.
                if (grid[i][j].value == 0) return false;
            }
        }

        // 2. 가로로 찰싹 붙어있는 숫자 둘이 똑같아서 합쳐질 수 있는지 확인합니다.
        for (int i = 0; i < 4; i++) {
            // 오른쪽 짝꿍(j+1)이랑 비교해야 하니 마지막 칸은 제외하고 2까지만 돕니다.
            for (int j = 0; j < 3; j++) {
                // 내 숫자와 오른쪽 숫자가 같다면?
                // 아직 합칠 수 있으므로 "안 끝났음(false)"!
                if (grid[i][j].value == grid[i][j+1].value) return false;
            }
        }

        // 3. 세로로 위아래 붙어있는 숫자 둘이 똑같은지 확인합니다.
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                // 내 숫자와 아래쪽 숫자가 같다면?
                // 역시 합칠 수 있으므로 false!
                if (grid[i][j].value == grid[i+1][j].value) return false;
            }
        }

        // 위의 3가지 깐깐한 관문(빈칸 검사, 가로 검사, 세로 검사)을 모두 뚫고 여기까지 코드가 도달했다면?
        // 빈칸도 0개고, 가로로도 세로로도 합칠 숫자가 단 하나도 없다는 뜻입니다.
        // 그러므로 "진짜로 다 막혀서 끝났어!(true)" 라고 최종 사망 선고(리턴)를 내립니다.
        return true;
    }
}