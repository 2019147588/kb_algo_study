package two_week.p1_2178;


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] twoArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                twoArr[i][j] = str.charAt(j) - '0';
            }
        }
//        bfs
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,1));
        int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};
        while (!q.isEmpty()){
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if(0 > ny || ny >= N || nx < 0 || nx >= M) continue;
                if(twoArr[ny][nx] != 1) continue;
                twoArr[ny][nx] = cur.d+1;
                q.add(new Node(ny, nx, cur.d + 1));
            }
        }
        System.out.println(twoArr[N-1][M-1]);

    }
    static class Node {
        int y, x, d;


        public Node(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}