import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	

	private static int N, R;
	private static int[] numbers;
	
	static StringBuilder sb = new StringBuilder();

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] arr = br.readLine().split(" ");
		N = Integer.parseInt(arr[0]);
		R = Integer.parseInt(arr[1]);
		
		numbers = new int[R];

		
		dfs(0);
		
		System.out.println(sb);
	}


	private static void dfs(int depth) {
		
		// 깊이가 R 과 같아질 경우 (종료조건)
		if(depth == R) {
			for(int i=0; i<R; i++) {
				sb.append(numbers[i] +" ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i=1; i<=N; i++) {
			numbers[depth] = i;
			dfs(depth+1);
		}
		
	}
}