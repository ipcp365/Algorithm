import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to, cost;
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static List<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 헛간 수
        int M = Integer.parseInt(st.nextToken()); // 길 수

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        dijkstra(1);

        System.out.println(dist[N]);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.to;
            int nowCost = cur.cost;

            if (nowCost > dist[now]) continue;

            for (Node next : graph[now]) {
                int nextCost = dist[now] + next.cost;
                if (nextCost < dist[next.to]) {
                    dist[next.to] = nextCost;
                    pq.offer(new Node(next.to, nextCost));
                }
            }
        }
    }
}
