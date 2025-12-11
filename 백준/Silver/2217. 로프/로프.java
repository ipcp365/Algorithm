import java.io.*;
import java.util.*;


/**
 * [문제 읽기]
 * - k 개의 로프를 사용해서 중량이 w인 물체를 들어올릴 때, 각 로프에 w/k 만큼 중량이 걸린다
 * - 들어올릴 수 있는 물체의 최대 중량을 구하여라
 * - 모든 로프를 사용할 필요는 없다.
 */
public class Main {

	
    public static void main(String[] args) throws Exception {
    	
    	// input & init
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken()); // 로프 수
    	
    	int[] arr = new int[n];
    	for(int i=0; i<n; i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    	}
    	
    	Arrays.sort(arr);
    	

    	
    	// Simulation : 정렬 후 나눌 수 있는 가장 큰 값 찾기
    	// 최대 중량 = (로프의 최대 중량 * 로프의 수) : 가장 가벼운것부터 검사하기 때문에 처음엔 모든 로프를 이용해서 들 수 있다.
    	int answer = Integer.MIN_VALUE;
    	
    	for(int i=0; i<n; i++) {
    		int weight = arr[i] * (n - i);
    		answer = Math.max(answer,  weight);
    	}


    	// Result
    	System.out.println(answer);
    
    }


}
