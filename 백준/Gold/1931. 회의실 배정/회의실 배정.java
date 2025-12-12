import java.io.*;
import java.util.*;

public class Main {
	
	static class Node implements Comparable<Node>{
		int start, end;
		
		Node(int start, int end){
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Node o) {
			if(this.end != o.end) {
				return Integer.compare(this.end, o.end);
			}
			
			// end 가 같을 경우 start 시간으로
			return Integer.compare(this.start, o.start);
		}
	}
	
	static int N;

	
	public static void main(String[] args) throws Exception {
		
		// init & input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		List<Node> schedule = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String[] lesson = br.readLine().split(" ");
			
			schedule.add(new Node(Integer.parseInt(lesson[0]), Integer.parseInt(lesson[1])));
		}
		
		Collections.sort(schedule);
		
		// Simulation
		int answer = 1;
		int lastIndex = schedule.get(0).end;
		
		for(int i=1; i<N; i++) {
			if(lastIndex <= schedule.get(i).start) {
				// 가능한 경우 새 수업을 듣는다.
				lastIndex =  schedule.get(i).end;
				answer++;
			}else {
				// 불가능 한 경우 i 값이 증가하기때문에 다음 수업이 가능한지 확인가능
			}
		}

		// Result
		System.out.println(answer);
	}
}
