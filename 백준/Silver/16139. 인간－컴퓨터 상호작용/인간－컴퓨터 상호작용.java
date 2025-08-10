import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String S = br.readLine();               // 소문자 알파벳 문자열
        int n = S.length();

        // pref[c][i] = S[0..i-1] 구간에서 문자 c('a'~'z')의 등장 횟수
        int[][] pref = new int[26][n + 1];

        // 전처리: i를 1..n로 돌리면 r+1, l을 그대로 쓰기 편함
        for (int i = 1; i <= n; i++) {
            int cur = S.charAt(i - 1) - 'a';
            for (int c = 0; c < 26; c++) {
                pref[c][i] = pref[c][i - 1];
            }
            pref[cur][i]++; // 현재 문자 카운트 증가
        }

        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ch = st.nextToken().charAt(0) - 'a';
            int l = Integer.parseInt(st.nextToken()); // 0-based
            int r = Integer.parseInt(st.nextToken()); // 0-based

            int ans = pref[ch][r + 1] - pref[ch][l];
            sb.append(ans).append('\n');
        }

        System.out.print(sb);
    }
}
