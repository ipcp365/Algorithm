import java.io.*;
import java.util.*;

/**
 * [문제 읽기]
 * - N개의 시험장, 각각의 시험장마다 응시자들이 있음
 * - i번 시험장에 있는 응시자수는 Ai 명
 * - 총감독관 : 한시험장에서 B명 감시 / 부감독관 : 한 시험장에서 C명 감시
 * 
 * - 각 시험장에 총감독관은 오직 1명, 부감독관은 여러명 가능
 * - 각 시험장마다 응시생들을 모두 감시해야 할 때, 필요한 감독관 수의 최솟값을 구하여라
 */
public class Main {
	
	static int N, B, C;
	static int[] students;
	static int answer;

	public static void main(String[] args) throws Exception {
		
		// init & input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		students = new int[N];
		for(int i=0; i<N; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken()); // 총감독관이 한번에 관리할 수 있는 학생 수
		C = Integer.parseInt(st.nextToken()); // 부감독관이 한번에 관리할 수 있는 학생 수
		

		// Simulation
    	long answer = 0;
    	for(int i=0; i<N; i++) {
    		
    		// 시험장 하나당 총감독관은 최소 1명 필요
    		answer++;
    		
    		// 총감독관 한명으로 모두 감시할 수 없는 경우, 부감독관 필요
    		if(students[i] - B > 0) {
    			// 감독받지 못하는 사람이 생기지 않도록 + C
    			// 부감독관이 과하게 필요하지 않도록 - 1
    			answer += ((students[i] - B) + C - 1) / C;
    		}
    		
    	}
		
		
		// Result
		System.out.println(answer);
	}



}
