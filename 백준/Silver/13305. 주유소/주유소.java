import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	
	static StringBuilder sb;
	static BufferedReader br;
	
	public static void main(String[] args) throws Exception {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		// 마을 사이 거리(km)
		int[] distance = new int[N-1];
		String[] temp1 = br.readLine().split(" ");
		for(int i=0; i<N-1; i++) distance[i] = Integer.parseInt(temp1[i]);
		

		// 도시별 기름 가격
		long[] city = new long[N];
		String[] tmpe2 = br.readLine().split(" ");
		for(int i=0; i<N; i++) city[i] = Integer.parseInt(tmpe2[i]);

		
		// 마을 끝에 도달하면 종료
		long answer = 0;
		int curPosition = 0;
		long curPrice = city[curPosition];
		while (curPosition < N-1) {
			

			// 첫 번째 마을에선 무조건 구매 진행
			if(curPosition==0) {
				answer += distance[curPosition] * curPrice;
				curPosition++;
				continue;
			}
			

			if(city[curPosition] >= curPrice) {
				// 다음 마을의 주유비가 더 비싼 경우, 현위치의 마을에서 추가 구매 진행
				answer += (distance[curPosition] * curPrice);
				curPosition++;
			}else {
				// 다음 마을의 주유비가 더 싼 경우
				curPrice = city[curPosition];
			}


		}
		
		System.out.println(answer);
		br.close();
		
		
	}	
	

}