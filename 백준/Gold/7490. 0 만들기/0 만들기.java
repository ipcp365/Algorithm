import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 풀이 전략 - BFS
 * 
 * 오답 과정 - 초반에 무한반복으로 인한 오류가 발생했는데, 함수재호출 과정에서 이미 방문한 층에 다시 가는 경우를 고려하지 못했음 예를
 * 들어 S=1, U=1, D=1 일 때, 1-> 2-> 1-> 2... 순서로 층 이동이 반복되겍 된다. - 정답을 갱신하기 위한 변수나
 * cnt++ -> cnt+1 과 같은 사소한 실수
 * 
 * - 초반에 DFS 로 풀었으나, 계속되는 오류로 찾아보니 무조건 BFS 로 풀어가 가능한 문제였음 ㅜㅜ - 01:30
 */

public class Main {


	static class Node {
        String expr; // 현재까지 만든 수식
        int idx;     // 현재 숫자 위치
        Node(String expr, int idx) {
            this.expr = expr;
            this.idx = idx;
        }
    }
	
	
	public static int T, N;
    static List<String> answers = new ArrayList<>();

    
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
            answers.clear();
			
			bfs();
			
			// 정답 출력
			Collections.sort(answers);
            for (String ans : answers) {
                System.out.println(ans);
            }
            

            System.out.println();
		}
		

	}

	static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("1", 1)); // 시작은 "1"

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            // 수식 문자열을 계산할 때에는 공백을 제거해서 정수로 인식하도록 함 !
            if (now.idx == N) {
                if (evaluate(now.expr.replaceAll(" ", "")) == 0) {
                    answers.add(now.expr);
                }
                continue;
            }

            int next = now.idx + 1;
            queue.offer(new Node(now.expr + " " + next, next));
            queue.offer(new Node(now.expr + "+" + next, next));
            queue.offer(new Node(now.expr + "-" + next, next));
        }
    } 
	
	// 수식 문자열을 계산
	// 이해 오래 걸림! 1+2-3 이라고 문자열이 주어질때, 실제 1+2 라는 식이 계산되는건 '-' 연산자가 나오는 시점이다 ! 바로바로 계산된다 생각해서 sign 초기화 순서 이해하는데 오래 걸림.
    static int evaluate(String expr) {
        int sum = 0;
        int num = 0;
        
        // 이전에 봤던 연산자 저장하는 용도
        char sign = '+';

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            // 0 ~ 9 : 숫자인지 판별
            // 주의 : 단순히 숫자만 주어진 경우엔 sun 이라는 결과에 값을 더하는게 아니라 '숫자 값만 갱신' 해주고, 다음 연산자가 보여질 때 계산해야 한다.
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            // 숫자거 아니거나 or 마지막 문자일 경우
            if (!Character.isDigit(ch) || i == expr.length() - 1) {
                if (sign == '+') sum += num;
                else if (sign == '-') sum -= num;

                sign = ch;
                num = 0;
            }
        }

        return sum;
    }
}