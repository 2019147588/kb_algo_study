package two_week.p5_14502;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, k = 1;
    static int[][] board;
    static Queue<Node> q = new ArrayDeque<>();
    static List<Node> list = new ArrayList<>();
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] visited;
    static int cnt = 0;


    static int count(int[][] twoArr) {
        int ret = 0;
        for (int[] arr : twoArr) {
            for (int el : arr) {
                if (el == 0) ret++;
            }
        }
        return ret;
    }

    static public void bfs(int[][] twoArr) {
        for (int i = 0; i < list.size(); i++) {
            q.add(list.get(i));
        }
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (visited[ny][nx] == k) continue;
                if (twoArr[ny][nx] == 1 || twoArr[ny][nx] == 2) continue;
                twoArr[ny][nx] = 2;
                q.add(new Node(ny, nx));
                visited[ny][nx] = k;
            }
        }
        cnt = Math.max(cnt, count(twoArr));

    }

    static public void combi(int start, int count) {
        if (count == 3) {
            int[][] tmp = new int[N][];
            for (int i = 0; i < N; i++) {
                tmp[i] = board[i].clone();
            }
            bfs(tmp);
            k++;
            return;
        }
        for (int i = start; i < N * M; i++) {
            int r = i / M;
            int c = i % M;

            if (board[r][c] == 0) {
                board[r][c] = 1; // 원본에 직접 벽을 세웠다 치우는 방식이 메모리에 효율적
                combi(i + 1, count + 1);
                board[r][c] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                board[i][j] = v;
                if (v == 2) {
                    list.add(new Node(i, j));
                }
            }
        }
        combi(0, 0);
        System.out.println(cnt);

    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}