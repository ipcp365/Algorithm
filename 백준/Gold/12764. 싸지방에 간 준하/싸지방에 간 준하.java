import java.io.*;
import java.util.*;

/**
 * 문제 읽기
 * 
 * - 모든 사람은 정해진 시간에만 싸지방을 이용한다.
 * - 컴퓨터가 있는 자리에는 1번부터 순서대로 번호가 매겨짐
 * - 모든 사람이 '기다리지 않고' 싸지방을 이용할 수 있는 컴퓨터 최소 개수와, 자리별로 몇 명의 사림이 사용 했는지?
 * 
 * - 싸지방에 들어오면 비어있는 자리 중 번호가 가장 작은 자리에 먼저 앉는게 규칙
 * 
 */
class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 컴퓨터 이용 시간, 종료시간
		int[][] computer = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			computer[i][0] = Integer.parseInt(st.nextToken());
			computer[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 시작시간 - 종료시간 기준 오름차순 정렬
		// 단, 시작/종료 시각이 다른 사람과 겹치는 경우는 없다.
		Arrays.sort(computer, (i1, i2) -> {
			if (i1[0] != i2[0]) return i1[0] - i2[0];
			return i1[1] - i2[1];
		});
		
		int seatCount = 0;
		PriorityQueue<int[]> inUse = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 종료시간 오름차순
		PriorityQueue<Integer> available = new PriorityQueue<>(); // 자리 번호 오름차순
		int[] useCount = new int[100_001]; // 최대 100_000명

		for (int[] person : computer) {
			
			// i 번째 사람의 컴퓨터 시작, 종료 시간
		    int start = person[0];
		    int end = person[1];
		    
		    // 종료된 자리 회수
		    /**
		     * 사용중인 컴퓨터(inUse) 중, 현재(start) 보다 작은값이 있는 경우 => 사용이 종료된 컴퓨터를 의미함
		     * => 빈자리 회수 대상, available 라는 queue 에 넣어줌 (빈자리의 번호 모음)
		     */
		    while (!inUse.isEmpty() && inUse.peek()[0] <= start) {
		        int[] finished = inUse.poll();
		        available.offer(finished[1]); // 해당 자리번호를 빈 자리로 돌려줌
		    }


		    // 사용가능한 빈자리가 있다면 queue 에서 꺼냄 (이때, 항상 가장 작은 번호자리 먼저 쓴다고 되어있끼 때문에 PQ 사용의 장점!)
		    int seat;
		    if (!available.isEmpty()) {
		        seat = available.poll(); // 빈 자리 중 번호 가장 작은 것
		    } else {
		        seat = seatCount++; // 새 자리 번호 부여
		    }

		    // 할당이 끝나고나면 해당 seat 번째의 사용 횟수 추가
		    // 이때, 종료시간을 먼저 넣어야 이후 i+1 번째 사람의 컴퓨터 이용 시작시간과 이용가능함에 유의
		    useCount[seat]++;
		    inUse.offer(new int[]{end, seat});
		}
		
		
		// Result
		System.out.println(seatCount); // 사용된 자리 수
		for (int i = 0; i < seatCount; i++) {
		    System.out.print(useCount[i] + " ");
		}



	}
}