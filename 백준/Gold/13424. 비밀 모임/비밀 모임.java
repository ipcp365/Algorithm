import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    static class Node implements Comparable<Node> {
        int v; int d;
        Node(int v, int d) { this.v = v; this.d = d; }
        public int compareTo(Node o) { return Integer.compare(this.d, o.d); }
    }

    static final int INF = 1_000_000_000;

    static int N, M, K;
    static List<Edge>[] g;

    static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.d != dist[cur.v]) continue;
            for (Edge e : g[cur.v]) {
                int nd = cur.d + e.w;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.add(new Node(e.to, nd));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            g = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                g[a].add(new Edge(b, c));
                g[b].add(new Edge(a, c));
            }

            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] friends = new int[K];
            for (int i = 0; i < K; i++) friends[i] = Integer.parseInt(st.nextToken());

            long[] sum = new long[N + 1];

            // 친구마다 다익스트라 실행 후 각 정점으로의 거리합 누적
            for (int s : friends) {
                int[] dist = dijkstra(s);
                for (int v = 1; v <= N; v++) sum[v] += dist[v];
            }

            // 최소 합 정점 선택 (동률 시 번호가 작은 정점)
            long best = Long.MAX_VALUE;
            int ans = 1;
            for (int v = 1; v <= N; v++) {
                if (sum[v] < best) { best = sum[v]; ans = v; }
            }
            sb.append(ans).append('\n');
        }

        System.out.print(sb.toString());
    }
}
