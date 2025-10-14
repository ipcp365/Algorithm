import java.io.*;
import java.util.*;

/**
 * 문제 읽기
 * - 파이프의 물이 떨어지는 위치 : 가장 왼쪽에서 정수만큼 떨어진 거리만 물이 샌다.
 * - 길이가 L인 테이프 무한개 소지
 * - 물을 막을 때, 그 위치의 좌우 0.5 만큼 간격을 줘야 물이 다시는 새지 않는다.
 *   테이프는 자를 수 없다
 *   테이프를 겹쳐서 붙이는 것은 가능하다.
 */
public class Main {

    public static void main(String[] args) throws Exception {
    	
    	// 1. init & input
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] nl = br.readLine().split(" ");
    	int N = Integer.parseInt(nl[0]); // 물이 새는 곳의 개수
    	int L = Integer.parseInt(nl[1]); // 테이프의 길이
    	
    	int[] arr = new int[N];
    	String[] locations = br.readLine().split(" ");
    	for(int i=0; i<N; i++) {
    		arr[i] = Integer.parseInt(locations[i]);
    	}
    	Arrays.sort(arr);
    	
    	// 2. Simulation
    	int L2 = L*2;
    	int coveredEnd = Integer.MIN_VALUE;
    	int answer = 0;
    	
    	for(int x: arr) {
    		int p = x * 2;
    		
    		// 이미 덮여있는 경우 스킵
    		if(coveredEnd >= p+1) continue;
    		
    		coveredEnd = (p - 1) + L2;
    		answer++;
    	}
    	
    	
    	// 3. Result
    	System.out.println(answer);
    	
    }

}
