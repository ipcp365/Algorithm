
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuffer sb = new StringBuffer();
	
	static int node, line, start;
	
	static int[][] arr;
	static boolean[] check;
	
	static Queue<Integer> q = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		node = Integer.parseInt(st.nextToken());
		line = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		arr = new int[node+1][node+1];
		check = new boolean[node+1];
		
		for(int i=0; i<line; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st2.nextToken());
			int b = Integer.parseInt(st2.nextToken());
			
			arr[a][b] = arr[b][a] = 1;
		}
		
		// DFS
		dfs(start);
		sb.append("\n");
		
		// BFS
		check = new boolean[node+1];
		bfs(start);
		
		// Result
		System.out.println(sb);
	}


	private static void dfs(int start) {
		sb.append(start + " ");
		check[start] = true;
		
		// 재귀 호출
		for(int i=1; i<=node; i++) {
			if(arr[start][i] == 1 && !check[i]) {
				check[i] = true;
				dfs(i);
			}
		}
	}


	private static void bfs(int start) {
		q.add(start);
		check[start] = true;
		
		while(!q.isEmpty()) {
			start = q.poll();
			sb.append(start + " ");
			
			for(int i=1; i<=node; i++) {
				if(arr[start][i] == 1 && !check[i]) {
					q.add(i);
					check[i] = true;
				}
			}
		}
	}
	
	
}
