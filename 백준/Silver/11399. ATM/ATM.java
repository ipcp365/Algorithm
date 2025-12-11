import java.io.*;
import java.util.*;


/**
 * [문제 읽기]
 * - N 명의 사람과 인출하는데 걸리는 시간 P 가 주어질 때, 각 사람이 돈을 인출하는데 필요한 시간의 합의 '최솟값'을 구하여라.
 */
public class Main {

	
    public static void main(String[] args) throws Exception {
    	
    	// input & init
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine()); // 유제품 수
    	
    	int[] arr = new int[N];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i=0; i<N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(arr);
    	

    	//Simulation
        int sum = 0;       // 지금까지 누적된 합
        int answer = 0;     // 전체 합

        for (int i = 0; i < N; i++) {
            sum += arr[i];   // i번째 사람까지의 누적합
            answer += sum;  // 전체 합에 더하기
        }


    	// Result
    	System.out.println(answer);
    
    }


}
