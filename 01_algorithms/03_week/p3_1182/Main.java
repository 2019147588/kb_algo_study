import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int target;
    static int[] numbers;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, false);
        System.out.println(count);
    }

    private static void dfs(int index, int sum, boolean selected) {
        if (index == n) {
            if (selected && sum == target) {
                count++;
            }
            return;
        }

        dfs(index + 1, sum + numbers[index], true);
        dfs(index + 1, sum, selected);
    }
}

/*
코드 설명
- 입력으로 N, S와 수열을 받은 뒤, dfs(index, sum, selected)로 모든 부분수열을 탐색한다.
- index는 현재 확인 중인 원소 위치, sum은 지금까지 선택한 원소 합, selected는 원소를 1개 이상 선택했는지 여부이다.
- index가 n에 도달했을 때 selected가 true이고 sum == target이면 정답 count를 증가시킨다.
*/

/*
알고리즘 설명
- 각 원소마다 "선택한다 / 선택하지 않는다" 두 가지 경우를 재귀로 분기한다.
- 따라서 전체 경우의 수는 2^N이며, N <= 20 범위에서 완전탐색으로 충분히 처리 가능하다.
- selected 플래그를 둬서 공집합(아무 원소도 선택하지 않은 경우)이 정답에 포함되지 않게 처리한다.
*/

/*
구현 배경
- 이 문제는 부분수열의 모든 조합을 빠짐없이 확인해야 하므로 백트래킹(DFS 완전탐색)이 가장 직관적이다.
- 별도의 복잡한 자료구조 없이 상태(index, sum, selected)만 관리하면 구현이 단순하고 가독성이 좋다.
- 입력 크기가 작아(최대 20개) 시간 복잡도 O(2^N) 방식이 안전하게 통과된다.
*/
