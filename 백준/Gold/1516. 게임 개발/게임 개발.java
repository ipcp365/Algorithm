import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] indegree = new int[N + 1];
        int[] buildTime = new int[N + 1];
        int[] resultTime = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            buildTime[i] = sc.nextInt();

            while (true) {
                int prev = sc.nextInt();
                if (prev == -1) break;

                graph.get(prev).add(i);  // prev → i
                indegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        // 초기 건물들 (선행 조건 없는 것들)
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                resultTime[i] = buildTime[i];
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph.get(now)) {
                indegree[next]--;

                resultTime[next] = Math.max(resultTime[next], resultTime[now] + buildTime[next]);

                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(resultTime[i]);
        }
    }
}
