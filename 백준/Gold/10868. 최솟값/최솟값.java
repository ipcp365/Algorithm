import java.io.*;
import java.util.*;

/**
 * 백준 10868 - 최솟값 (Bottom-up Segment Tree, 반복문 버전)
 * 
 * ✅ 요청 반영: 비트 연산 대신 일반 곱셈/나눗셈/나머지로 표기 + 옆에 주석으로 대응 관계 표시
 *    - i << 1      → i * 2          // 왼쪽 자식
 *    - i << 1 | 1  → i * 2 + 1      // 오른쪽 자식
 *    - x >>= 1     → x = x / 2      // 부모로 이동
 *    - (x & 1)     → (x % 2)        // 홀짝 판별
 *
 * 빌드: O(N), 쿼리: O(log N)
 */
public class Main {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1) 입력: N(원소 수), M(질의 수)
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 2) 세그먼트 트리의 리프 시작 인덱스 base = N 이상인 가장 가까운 2의 거듭제곱
        int base = 1;
        while (base < N) base *= 2;   // base <<= 1 과 동일

        // 3) 트리 배열 (크기 = 2 * base), INF로 초기화
        int size = base * 2;          // base << 1 과 동일
        int[] tree = new int[size];
        Arrays.fill(tree, INF);

        // 4) 입력값을 리프 구간(tree[base] ~ tree[base+N-1])에 채우기
        int filled = 0;
        while (filled < N) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens() && filled < N) {
                int val = Integer.parseInt(st.nextToken());
                tree[base + filled] = val;
                filled++;
            }
        }

        // 5) 부모 노드 값 채우기 (bottom-up)
        //    tree[i] = min(tree[i*2], tree[i*2 + 1])   // i<<1, i<<1|1 대응
        for (int i = base - 1; i >= 1; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]); // (i<<1), (i<<1|1)
        }

        // 6) 질의 처리
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < M; q++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a > b) { int t = a; a = b; b = t; } // a > b 안전 처리

            // [a, b]를 리프 인덱스로 변환
            int l = base + (a - 1);
            int r = base + (b - 1);
            int ans = INF;

            // 구간을 위로 수축하며(min 병합) 계산
            while (l <= r) {
                // if ((l & 1) == 1) → if (l % 2 == 1): l이 오른쪽 자식이면 현재 노드 포함 후 l++
                if (l % 2 == 1) {
                    ans = Math.min(ans, tree[l]);
                    l += 1;
                }
                // if ((r & 1) == 0) → if (r % 2 == 0): r이 왼쪽 자식이면 현재 노드 포함 후 r--
                if (r % 2 == 0) {
                    ans = Math.min(ans, tree[r]);
                    r -= 1;
                }
                // l >>= 1; r >>= 1; → 부모로 이동
                l = l / 2;
                r = r / 2;
            }

            sb.append(ans).append('\n');
        }

        System.out.print(sb.toString());
    }
}
