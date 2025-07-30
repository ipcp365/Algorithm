import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] classes = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			classes[i][0] = Integer.parseInt(input[0]); // 시작 시간
			classes[i][1] = Integer.parseInt(input[1]); // 종료 시간
		}

		// 시작 시간 기준 정렬
		Arrays.sort(classes, (a, b) -> a[0] - b[0]);

		// 종료 시간을 담는 우선순위 큐 (min heap)
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(classes[0][1]); // 첫 강의 종료 시간 삽입

		for (int i = 1; i < N; i++) {
			if (classes[i][0] >= pq.peek()) {
				// 강의실 재사용 가능
				pq.poll();
			}
			// 강의실 사용 (새로운 강의 시작 or 기존 연장)
			pq.add(classes[i][1]);
		}

		// 현재 우선순위 큐 크기가 필요한 강의실 수
		System.out.println(pq.size());
	}
}
