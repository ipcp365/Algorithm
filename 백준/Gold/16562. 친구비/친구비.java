import java.util.*;

public class Main {
    static int N, M, K;
    static int[] cost;
    static boolean[] visited;
    static List<Integer>[] graph;

    static int dfs(int node) {
        visited[node] = true;
        int min = cost[node];
        for (int next : graph[node]) {
            if (!visited[next]) {
                min = Math.min(min, dfs(next));
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); K = sc.nextInt();

        cost = new int[N + 1];
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            cost[i] = sc.nextInt();
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int v = sc.nextInt();
            int u = sc.nextInt();
            graph[v].add(u);
            graph[u].add(v);
        }

        int total = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                total += dfs(i);
            }
        }

        System.out.println(total <= K ? total : "Oh no");
    }
}
