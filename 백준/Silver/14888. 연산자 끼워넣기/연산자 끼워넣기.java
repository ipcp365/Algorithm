import java.io.*;
import java.util.*;


/**
 * [문제 읽기]
 * - N개의 수열과 N-1 개의 연산자(+, -, *, /)
 * - 숫자의 순서는 변경할 수 없우며, 수식의 위치는 바꿀 수 있다.
 * - 식의 결과가 최대인 것과 최소일 때의 값을 구하여라.
 */
public class Main {

	static int N, plus, minus, mul, div;
	static long maxValue = Integer.MIN_VALUE;
	static long minValue = Integer.MAX_VALUE;
	static int[] nums;
	
	
    public static void main(String[] args) throws IOException {
    	// input
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());    	
    	N = Integer.parseInt(st.nextToken());
    	
    	// 숫자 배열
    	nums = new int[N];
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<N; i++) {
    		nums[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 연산자 : 덧셈 / 뺄셈 / 곱셈 / 나눗셈
    	st = new StringTokenizer(br.readLine());
    	plus = Integer.parseInt(st.nextToken());
    	minus = Integer.parseInt(st.nextToken());
    	mul = Integer.parseInt(st.nextToken());
    	div = Integer.parseInt(st.nextToken());
    	

    	// Simulation
    	dfs(1, nums[0]);

    	
    	// Result
    	System.out.println(maxValue);
    	System.out.println(minValue);
    }


    // dfs(현재 총 인원수, a팀, b팀?
	private static void dfs(int numIdx, int values) {
		
		// 종료 조건
		if(numIdx == N) {
			minValue = Math.min(minValue, values);
			maxValue = Math.max(maxValue, values);
			return;
		}
		
		// 연산자 고르기
		if(plus > 0) {
			plus--;
			dfs(numIdx + 1, values + nums[numIdx]);
			plus++;
		}
		
		
		if(minus > 0) {
			minus--;
			dfs(numIdx + 1, values - nums[numIdx]);
			minus++;
		}
		
		if(mul > 0) {
			mul--;
			dfs(numIdx + 1, values * nums[numIdx]);
			mul++;
		}

		
		if(div > 0) {
			div--;
			dfs(numIdx + 1, values / nums[numIdx]);
			div++;
		}
	}


}