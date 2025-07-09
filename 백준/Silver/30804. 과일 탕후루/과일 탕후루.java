import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M;
    static boolean[] visited;
    static int[] result;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		

		/**
		 * 기존에 완전탐색으로 풀었다가, 시간초과 문제로 도움 받아서 해결!
		 * 슬라이딩 윈도우 (투 포인터)로 해결해야 가능한 문제
		 * - left, right 라는 두개의 변수(==인덱스)를 이용해서 배열의 일정한 구간을 다루는 방식
		 * - 여기서 Map을 사용한 이유는 과일 종류와 개수를 기록하기 위함
		 * 
		 * 
		 * Q. 왜 이게 일반탐색보다 빨리 계산할 수 있는가?
		 * 6ㅐ
		 * 
		 */
		int left = 0;
		int right = 0;
		int answer = 0;
		Map<Integer, Integer> map = new HashMap<>();
		
		while (right < N) {
			
			// 처음 등장하는 과일의 경우 개수를 1로 해주기 위해 가장 먼저 등장
		    // 과일 추가 : 이미 Key가 존재하는 경우 value 값을 가져와서 1 증가(개수 증가)
		    map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
		    
		    // while문 사용 ! 종류가 3개 이상이면 왼쪽 포인터 이동 == 시작값을 변경 한다.
		    while (map.size() > 2) {
		        map.put(arr[left], map.get(arr[left]) - 1);
		        if (map.get(arr[left]) == 0) {
		            map.remove(arr[left]);
		        }
		        left++;
		    }
		    
		    // 조건 만족하는 구간이라면 최대값 갱신
		    answer = Math.max(answer, right - left + 1);
		    
		    right++;
		}
		
		System.out.println(answer);
	}
	

}


