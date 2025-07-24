import java.util.*;

public class Main {
	static int N, M;
	static boolean[] visited;
	static List<Integer>[] graph;

	static void dfs(int node, int depth) {
		if (depth == 5) {
			System.out.println(1);
			System.exit(0); // 조건 만족하면 바로 종료
		}

		visited[node] = true;
		
		for (int next : graph[node]) {
			if (!visited[next]) {
	            dfs(next, depth + 1);
			}
		}
		
		visited[node] = false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		visited = new boolean[N];
		graph = new ArrayList[N];

		for (int i = 0; i <N; i++) {
			graph[i] = new ArrayList<>();
		}

		// 친구 관계 입력 받기
		for (int i = 0; i < M; i++) {
			int v = sc.nextInt();
			int u = sc.nextInt();
			graph[v].add(u);
			graph[u].add(v);
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				dfs(i, 1);
			}
		}

		System.out.println(0);
	}
}
