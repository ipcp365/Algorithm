import java.io.*;
import java.util.*;


/**
 * 문제 읽기
 * - 모든 스위치는 0, 1 구성
 * - 남학생 : 스위치 번호가 자기가 받은 수의 배수면 그 스위치 상태 변경 (on - off)
 * - 여학생 : 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로, 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아 해당 구간의 스위치 상태를 모두 변경
            이때, 구간에 속한 스위치 개수는 항상 홀수
 * 
 * input
 * x, y
 * x = 성별 1(남자), 2(여자)
 * y = 스위치
 */
class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[] switchs = new boolean[N+1]; // 0번 스위치는 사용하지 않음
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			switchs[i] = Integer.parseInt(st.nextToken()) == 1 ? true: false;
		}
		
		// 사람
		int num = Integer.parseInt(br.readLine());
		while(num-- > 0) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			
			if(gender == 1) {
			    for(int i = idx; i <= N; i += idx) {
			        switchs[i] = !switchs[i];
			    }
			}else {
				// 양쪽으로 범위를 1씩 늘려나가며 대칭구조를 이루는지 확인
				int sum = 1;
				while(true) {
					int left = idx - sum;
					int right = idx + sum;
					
					// 조건 범위를 벗어나면 while 문 중단
					if((left <= 0  || right > N) || switchs[left] != switchs[right]) break;
					
					// 통과할 경우 범위 확장시키고 한번 더 검사 (단, 범위가 확장되었을 때 대칭이 되는 경우에만 스위치 변경에 포함시켜야하므로 sum-- 필요)
					sum++;
				}
				
				// while 문 탈출 직전까지가 유효한 대칭 범위이기 때문에 sum 값을 하나 줄인다.
				sum--;
				
				// 범위 만쿰
				for (int i = idx - sum; i <= idx + sum; i++) {
					switchs[i] = !switchs[i];
				}
			}
		}
		
		// Result
		for(int i=1; i<=N; i++) {
			System.out.print(switchs[i] ? 1 : 0);
			System.out.print(" ");
			if (i % 20 == 0) System.out.println();
		}
		
		

	}
}