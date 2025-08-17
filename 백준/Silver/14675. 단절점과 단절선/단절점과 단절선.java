import java.io.*;
import java.util.*;

/**
 * BOJ 14675 - 단절점과 단절선
 *
 * 관찰:
 * 1) 입력 그래프는 트리(정점 N, 간선 N-1, 연결)이다.
 * 2) 트리에서 모든 간선은 단절선(bridge)이다. => 간선 제거 시 반드시 2개의 컴포넌트로 분리됨.
 * 3) 트리에서 단절점은 차수(degree)가 2 이상인 정점만 해당한다.
 *    - 리프(차수 1) 제거 시 그래프는 여전히 연결된 하나의 컴포넌트로 남는다(정점 수만 줄뿐).
 *
 * 쿼리:
 * - t = 1, k: 정점 k가 단절점인가? => degree[k] >= 2 이면 "yes", 아니면 "no"
 * - t = 2, k: 간선 k가 단절선인가?  => 트리의 모든 간선은 단절선이므로 항상 "yes"
 *
 * - 간선 정보는 차수 계산에만 사용하므로, degree 배열만 유지하면 됨.
 * - t=2에서 k는 "간선의 입력 순서 인덱스"지만, 트리에선 어떤 간선이든 단절선이므로 실제 간선 저장 불필요.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());

        // 각 정점의 차수 저장용 배열 (1-indexed)
        int[] degree = new int[N + 1];

        // 간선 N-1개 입력: 차수만 기록
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            degree[u]++;
            degree[v]++;
        }

        int Q = Integer.parseInt(br.readLine().trim());

        // 쿼리 처리
        // t == 1: 정점 k가 단절점인지? -> degree[k] >= 2 이면 yes
        // t == 2: 간선 k가 단절선인지? -> 트리의 모든 간선은 단절선 -> 항상 yes
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (t == 1) {
                out.append(degree[k] >= 2 ? "yes" : "no").append('\n');
            } else {
                // t == 2
                out.append("yes").append('\n');
            }
        }

        System.out.print(out);
    }
}
