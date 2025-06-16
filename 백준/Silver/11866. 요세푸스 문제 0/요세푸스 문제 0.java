import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.DelayQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> q = new LinkedList<Integer>();
		
		String[] nk = br.readLine().split(" ");
		
		
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		
		int[] nums = new int[n];
		for(int i=1; i<=n; i++) {
			q.add(i);
		}
		
		// 모든 값이 0이될 때까지 반복
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		while(q.size() > 1) {
			for(int i=0; i<k-1; i++) {
				int temp = q.poll(); // 맨 앞 값 빼기
				q.offer(temp); 		 // 맨 뒤에 값 추가
			}
			
			// k 번째가 되는 수 출력
			sb.append(q.poll()).append(", ");
		}
		
		sb.append(q.poll()).append(">");
		System.out.println(sb);
		
		
		
	}
}