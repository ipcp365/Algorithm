import java.io.*;
import java.util.*;


public class Main {

	static int N, answer;
	
	static boolean[] colUsed; // 같은 열에 퀸
	static boolean[] diag1; // 대각선 ↘ (row + col)
	static boolean[] diag2; // 대각선 ↙ (row - col + N - 1)
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        answer = 0;
        colUsed = new boolean[N];
        diag1 = new boolean[2 * N - 1];
        diag2 = new boolean[2 * N - 1];
        
        // Simulation
        dfs(0);

        // Result
        System.out.println(answer);
    }

	private static void dfs(int row) {
		
		// 종료조건
		if(row == N) {
			answer++;
			return;
		}
		
		//점화식
		for(int col=0; col<N; col++) {
			// 조건 검사하기 : 중요한건 대각선상을 구할 때 N*N을 생각하지만 실제론 수학의 4분면 기준으로 봐야한다는 점
			int d1 = row + col;
			int d2 = row - col + (N - 1);
			
			if(colUsed[col] || diag1[d1] || diag2[d2]) continue;
			
			// 퀸
			colUsed[col] = true;
			diag1[d1] = true;
			diag2[d2] = true;
			
			// 다음 행
			dfs(row+1);
			
			colUsed[col] = false;
		    diag1[d1] = false;
		    diag2[d2] = false;
		}
		
	}// ... dfs

    

}
