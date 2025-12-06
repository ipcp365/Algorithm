import java.io.*;
import java.util.*;


public class Main {

	static int N, M;
	static int[] arr;
	static int[] nums;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visited = new boolean[N];
        
        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for(int i=0; i<N; i++) {
        	nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        
        // Simulation
        dfs(0);

        // Result
        System.out.println(sb.toString());
    }

	private static void dfs(int depth) {
		// 종료 조건
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 점화식
		for(int i=0; i<N; i++) {
			
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = nums[i];
				dfs(depth+1);
				visited[i] = false;
			}

		}
	}// ... dfs

    

}
