import java.util.*;
import java.io.*;


/**
 * [문제읽기]
 * - N, M. 길이가 M 인 수열 
 * - 길이가 1부터 N 까지 자연수 중에서 중복없이 M개를 고른 수열
 */
public class Main {
	
	static int N, M;
	static boolean[] visited;
    static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
    	
    	// init & input
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	visited = new boolean[N+1];
    	arr = new int[M];
    	
    	// Simulation
    	dfs(0);
    	
    	// Result
    	System.out.println(sb.toString());
    }

	private static void dfs(int depth) {
		// 종료조건 : 주어진 현재 문자(cur) 이 최대길이(M) 과 동일할 때
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 점화!
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				dfs(depth+1);
				visited[i] = false;
			}
		}
	}
}