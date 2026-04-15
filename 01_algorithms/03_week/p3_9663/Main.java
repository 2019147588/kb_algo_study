import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if (N == 0) {
            System.out.println(0);
            return;
        }
        int[] board = new int[N]; // board[row] = col : row행, col열에 퀸이 있음을 의미
        int count = 0;

        int row = 0; // 현재 탐색 중인 행, 0부터 시작

        // row가 0보다 작아지면 모든 탐색이 끝났다는 의미
        while (row >= 0) {

            // placed: 현재 row에 퀸을 성공적으로 놓았는지 확인하는 플래그
            boolean placed = false;

            // 이전에 시도했던 열(board[row]) 다음부터 탐색 시작
            int startCol = board[row];
            board[row] = 0; // 현재 행의 탐색 위치를 초기화 (백트래킹을 위해)

            for (int col = startCol; col < N; col++) {
                // isSafe: (row, col) 위치가 유망한지(안전한지) 확인하는 플래그
                boolean isSafe = true;

                // 이전 행들(0 ~ row-1)에 놓인 퀸들과 충돌하는지 검사
                for (int i = 0; i < row; i++) {
                    if (board[i] -1 == col || Math.abs(row - i) == Math.abs(col - (board[i]-1))) {
                        isSafe = false;
                        break;
                    }
                }

                // 현재 위치가 안전하다면, 퀸을 놓고 다음 행으로 전진
                if (isSafe) {
                    // 다음 탐색을 위해 (현재 열 + 1) 위치를 기록
                    board[row] = col + 1;
                    row++; // 다음 행으로 이동
                    placed = true;
                    break; // 현재 행의 탐색을 마치고 다음 행으로
                }
            }

            // 모든 행에 퀸을 성공적으로 놓았다면 (해답 발견)
            if (row == N) {
                count++;
                row--; // 마지막 행에서 후퇴하여 다른 해답 탐색 시작
                continue;
            }

            // 현재 행의 모든 열을 시도했지만 퀸을 놓지 못했다면 (후퇴)
            if (!placed) {
                row--; // 이전 행으로 돌아감 (백트래킹)
            }
        }

        System.out.println(count);
    }
}
