import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[] selected;
    static boolean[] used;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        selected = new int[m];
        used = new boolean[n + 1];

        dfs(0);
        System.out.print(sb);
    }

    private static void dfs(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int num = 1; num <= n; num++) {
            if (used[num]) {
                continue;
            }

            used[num] = true;
            selected[depth] = num;
            dfs(depth + 1);
            used[num] = false;
        }
    }
}

/*
코드 설명
- 입력 N, M을 받은 뒤 길이가 M인 순열을 백트래킹으로 생성한다.
- used 배열로 이미 선택한 수를 중복 사용하지 않도록 막고, selected 배열에 현재 수열을 저장한다.
- depth가 M에 도달하면 selected 내용을 StringBuilder에 출력 형식으로 누적한다.
*/

/*
알고리즘 설명
- 1부터 N까지 숫자 중 아직 사용하지 않은 숫자를 현재 위치(depth)에 배치한다.
- 다음 위치로 재귀 호출하고, 호출이 끝나면 방문 상태를 되돌리는(백트래킹) 방식으로 모든 경우를 탐색한다.
- 시간 복잡도는 순열 개수인 P(N, M)에 비례한다.
*/

/*
구현 배경
- N과 M (1)은 "중복 없이 순서 있게 뽑기"이므로 순열형 백트래킹이 정석이다.
- 매번 문자열을 바로 출력하면 느릴 수 있어 StringBuilder에 모아 한 번에 출력하도록 구성했다.
- 상태가 depth/used/selected로 명확히 나뉘어 가독성과 디버깅이 쉽다.
*/
