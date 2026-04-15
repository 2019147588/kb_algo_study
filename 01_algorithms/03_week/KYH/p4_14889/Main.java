import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] ability;
    static boolean[] isStartTeam;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        ability = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isStartTeam = new boolean[n];
        chooseStartTeam(0, 0);
        System.out.println(answer);
    }

    private static void chooseStartTeam(int index, int picked) {
        if (picked == n / 2) {
            answer = Math.min(answer, calculateDiff());
            return;
        }

        for (int i = index; i < n; i++) {
            isStartTeam[i] = true;
            chooseStartTeam(i + 1, picked + 1);
            isStartTeam[i] = false;
        }
    }

    private static int calculateDiff() {
        int startSum = 0;
        int linkSum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isStartTeam[i] && isStartTeam[j]) {
                    startSum += ability[i][j] + ability[j][i];
                } else if (!isStartTeam[i] && !isStartTeam[j]) {
                    linkSum += ability[i][j] + ability[j][i];
                }
            }
        }

        return Math.abs(startSum - linkSum);
    }
}

/*
코드 설명
- N명 중 N/2명을 스타트 팀으로 고르는 조합을 백트래킹으로 생성한다.
- 팀 구성이 완성되면 능력치 합 차이를 계산해 전역 최소값(answer)을 갱신한다.
- calculateDiff에서 같은 팀인 쌍만 더해 두 팀의 총 능력치를 구한다.
*/

/*
알고리즘 설명
- 핵심은 "팀 나누기"이므로 조합 탐색(C(N, N/2))을 수행한다.
- 각 조합마다 모든 선수 쌍(i, j)을 확인해 스타트팀/링크팀 내부 시너지를 계산한다.
- 최종적으로 두 팀 점수 차이의 절댓값 최소를 출력한다.
*/

/*
구현 배경
- 완전탐색 문제이지만 N <= 20이라 조합 탐색이 충분히 가능하다.
- boolean 배열로 팀 소속만 기록하면 코드가 단순하고 계산 시 조건 분기가 명확해진다.
- (i, j), (j, i)를 한 번에 더하고 i < j만 순회해 중복 연산을 줄였다.
*/
