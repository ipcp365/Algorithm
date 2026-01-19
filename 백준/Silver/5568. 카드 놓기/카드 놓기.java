import java.io.*;
import java.util.*;

/**
 * [문제 읽기]
 * - 카드 4~10 장, 각 1 이상 99이하 숫자
 * - 2~4장 선택후 가로로 나란히 정수를 만든다. 만들 수 있는 정수의 가지수는 ? (중복이 생길 수 있다. 중복 숫자는 제외 해야 한다.)
 */
public class Main {
	
	static int N, K, answer;
	static int[] cards;
	static boolean[] visited;
	static Set<String> nums;
	
	public static void main(String[] args) throws Exception {
		
		// init & input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 카드 수
		K = Integer.parseInt(br.readLine()); // 숫자 길이
		cards = new int[N];
		visited = new boolean[N];
		nums = new HashSet<String>();
		
		for(int i=0; i<N; i++) {
			int idx = Integer.parseInt(br.readLine());
			cards[i] = idx;
		}
		
		
		// Simulation : 숫자 조합을 만든다. 단 숫자의 길이는 K 가 되어야 한다.
		for(int i=0; i<N; i++) {
			visited[i] = true;
			simulation(String.valueOf(cards[i]), 1);
			visited[i] = false;
		}
		
		// Result
		System.out.println(nums.size());
	}


	private static void simulation(String num, int cnt) {
		// 종료 조건
		if(cnt == K) {
			nums.add(num);
			return;
		}
		
		// 점화식
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				simulation(num + cards[i], cnt+1);
				visited[i] = false;
			}
		}
	}

}
