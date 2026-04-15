import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static String[] words;
    static boolean[] visited; // 각 알파벳을 배웠는지 여부
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            // "anta", "tica" 제거
            words[i] = word.substring(4, word.length() - 4);
        }

        // K가 5보다 작으면 어떤 단어도 읽을 수 없음
        if (K < 5) {
            System.out.println(0);
            return;
        }
        // K가 26이면 모든 단어를 읽을 수 있음
        if (K == 26) {
            System.out.println(N);
            return;
        }

        visited = new boolean[26];
        // 기본 5글자는 항상 배워야 함
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        // K-5개의 글자를 선택하는 조합 탐색
        combination(0, 0);
        System.out.println(max);
    }

    // index: 현재 알파벳, count: 선택한 글자 수
    public static void combination(int index, int count) {
        // K-5개의 글자를 모두 선택한 경우
        if (count == K - 5) {
            int readableWords = 0;
            for (String word : words) {
                boolean canRead = true;
                for (char c : word.toCharArray()) {
                    // 단어의 글자를 배우지 않았다면 읽을 수 없음
                    if (!visited[c - 'a']) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) {
                    readableWords++;
                }
            }
            max = Math.max(max, readableWords);
            return;
        }

        // 알파벳 'a'부터 'z'까지 탐색
        for (int i = index; i < 26; i++) {
            // 아직 배우지 않은 글자라면
            if (!visited[i]) {
                visited[i] = true; // 배운 것으로 표시
                combination(i + 1, count + 1);
                visited[i] = false; // 백트래킹: 상태 원상복구
            }
        }
    }
}
