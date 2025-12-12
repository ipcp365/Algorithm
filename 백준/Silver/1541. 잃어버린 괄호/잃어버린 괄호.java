
import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1541
// 이 문제의 핵심은 최적의 해를 구하기 위한 규칙을 찾는 것에 달렸다.
// 주어진 연산에서 적절히 괄호() 를 사용하여 최소값을 만들기 위해선... => 덧셈을 먼저 수행하면 된다.
public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int answer = Integer.MAX_VALUE; // 최소값을 얻기 위한 초기화 변
		
		// 1. 우선 "-" 연산 기준으로 문자열은 분해한다.
		String[] subString = br.readLine().split("-");
		
		// 쪼개진 문자열 만큼 값을 연산해야 하므로, 반복문 정의
		for(int i=0; i<subString.length; i++) {
			
			
			// 2. 더하기 연산이 있을 경우 이를 수행하기 위해 한 번더 split 을 수행한다.
			int temp = 0;
			String[] addition = subString[i].split("\\+");
			for(int j=0; j<addition.length; j++) {
				temp += Integer.parseInt(addition[j]);
			}
			
			// 3. "-" 연산을 수행한다. 처음 한 번은 무조건 결과값을 반영하기 위해 if 문 작성
			// 이렇게 해야 연산이 하나 밖에 없더라도 수행 가능 !
			if(answer == Integer.MAX_VALUE) {
				answer = temp;
			} else {
				answer -= temp;
			}
			
			
		}
		
		System.out.println(answer);
		br.close();
	}
}
