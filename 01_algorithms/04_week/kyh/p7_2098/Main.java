package kyh.p7_2098;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] cost;
    static int[][] dp;
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < (1 << N); j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(tsp(0, 1));
    }

    static int tsp(int now, int visited) {
        if (visited == (1 << N) - 1) {
            if (cost[now][0] == 0) return INF;
            return cost[now][0];
        }

        if (dp[now][visited] != -1) {
            return dp[now][visited];
        }

        dp[now][visited] = INF;

        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0 || cost[now][next] == 0) {
                continue;
            }

            int newVisited = visited | (1 << next);
            dp[now][visited] = Math.min(dp[now][visited],
                    cost[now][next] + tsp(next, newVisited));
        }

        return dp[now][visited];
    }
}
