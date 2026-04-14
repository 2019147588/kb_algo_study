import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int count;
    static boolean[] colUsed;
    static boolean[] diagDownUsed; // row - col + (n - 1)
    static boolean[] diagUpUsed;   // row + col

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        colUsed = new boolean[n];
        diagDownUsed = new boolean[2 * n - 1];
        diagUpUsed = new boolean[2 * n - 1];

        placeQueen(0);
        System.out.println(count);
    }

    private static void placeQueen(int row) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int down = row - col + (n - 1);
            int up = row + col;

            if (colUsed[col] || diagDownUsed[down] || diagUpUsed[up]) {
                continue;
            }

            colUsed[col] = true;
            diagDownUsed[down] = true;
            diagUpUsed[up] = true;

            placeQueen(row + 1);

            colUsed[col] = false;
            diagDownUsed[down] = false;
            diagUpUsed[up] = false;
        }
    }
}

/*
코드 설명
- 한 행(row)씩 퀸을 놓는 DFS를 수행한다.
- 현재 칸에 놓을 수 있는지 열/대각선 사용 여부 배열로 O(1)에 검사한다.
- 모든 행에 퀸을 배치하면 유효한 경우 1개로 count를 증가시킨다.
*/

/*
알고리즘 설명
- 백트래킹으로 각 행에서 가능한 열만 시도한다.
- 충돌 조건은 같은 열, 같은 / 대각선, 같은 \ 대각선 세 가지다.
- 충돌 배열을 갱신 후 재귀, 반환 후 원상복구하여 다른 경우를 탐색한다.
*/

/*
구현 배경
- N-Queen은 대표적인 백트래킹 문제로, 체스판 전체 탐색보다 행 단위 탐색이 효율적이다.
- 대각선을 인덱스로 변환하면 별도 반복 검사 없이 빠르게 충돌을 판단할 수 있다.
- N <= 14 범위에서 이 방식이 가장 구현이 간결하고 실전적으로 자주 쓰인다.
*/
