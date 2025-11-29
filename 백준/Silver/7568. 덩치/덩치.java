import java.util.*;
import java.io.*;


/**
 * [문제읽기]
 * - 사람의 덩치 (x, y)
 * - 등수의 기준 : 자신보다 더 큰 덩치의 사람의 수.
 *   만약 자신보다 더 큰 덩치의 사람이 k 명이면 그 사람의 덩치 등수는 k+1;
 */
public class Main {
	
    public static void main(String[] args) throws IOException {
    	
    	// init & input
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	int[] weight = new int[n];
    	int[] height = new int[n];
    	
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		weight[i] = Integer.parseInt(st.nextToken());
    		height[i] = Integer.parseInt(st.nextToken());		
    	}
    	
    	// Simulation
    	StringBuilder sb = new StringBuilder();    	
    	for(int i=0; i<n; i++) {
    		// 현재 i 번쨰인물의 등수
    		int k = 1;
    		
    		// i 번째 인물이 몇번째 위치 했는지 구하기
    		for(int j=0; j<n; j++) {
    			
    			// 자기자신 중복 생략
    			if(i == j) continue;
    			
    			// 키와 몸무게 모두 큰 경우에만 더 덩치큰 사람이라고 판단함
    			if(weight[i] < weight[j] && height[i] < height[j]) k++;
    		}

    		// 정답
    		sb.append(k).append(" ");
    	}
    	
    	// Result
    	System.out.println(sb.toString());
    	
    
    }
}