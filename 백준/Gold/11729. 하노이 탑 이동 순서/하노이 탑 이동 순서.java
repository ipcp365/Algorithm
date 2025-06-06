import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws NumberFormatException, IOException{
    	
    	// 원판의 개수
    	int N = Integer.parseInt(br.readLine());

    	
    	// 수행 과정 출력
    	// K줄에 걸쳐 두정수 A B 를 빈칸에 두고
    	// -> A번째 탑의 가장 위에 있는 원판을 B탑의 가장 위로 옮긴다는 뜻이라고 합.
    	
    	// 출력 : 옮김 횟수래
    	sb.append((int)Math.pow(2, N)-1).append('\n');
    	
    	
    	Hanoi(N, 1, 2, 3);
    	
    	System.out.println(sb);
    }

	private static void Hanoi(int N, int start, int mid, int to) {
		
		// 함수호출 중단 조건 : 이동할 워반의 수가 1 일 경우
		if(N == 1) {
			sb.append(start).append(" ").append(to).append('\n');
			return;
		}
		
		// A -> C 이동
		// STEP1 : N-1개를 A에서 B로 이동(= start 지점의 N-1 개의원판을 mid 지점으로 옮긴당)
		Hanoi(N-1, start, to, mid);
		
		// STEP 2 : 1개를 A 에서 C로 이동 (start 지점의 N번째 원판을 to 지점으로 옮긴다)
		sb.append(start).append(" ").append(to).append('\n');
		
		// STEP3 : N-1 개를 B에서 C로 이동 (mid 지점의 N-1 개의 원판을 to 지점으로 옮긴다.)
		Hanoi(N-1, mid, start, to);
		
	}


    
}