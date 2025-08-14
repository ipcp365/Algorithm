import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    static class Node implements Comparable<Node> {
        int v; long d;
        Node(int v, long d) { this.v = v; this.d = d; }
        public int compareTo(Node o) { return Long.compare(this.d, o.d); }
    }

    static final long INF = Long.MAX_VALUE / 4;
    static int V, E, P;
    static List<Edge>[] g;

    static long[] dijkstra(int start) {
        long[] dist = new long[V + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.d != dist[cur.v]) continue;
            for (Edge e : g[cur.v]) {
                long nd = cur.d + e.w;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        g = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            g[a].add(new Edge(b, c));
            g[b].add(new Edge(a, c)); // 양방향
        }

        long[] d1 = dijkstra(1);
        long[] dp = dijkstra(P);

        if (d1[V] == d1[P] + dp[V]) System.out.println("SAVE HIM");
        else System.out.println("GOOD BYE");
    }
}
