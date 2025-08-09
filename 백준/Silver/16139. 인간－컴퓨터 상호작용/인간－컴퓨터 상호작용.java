import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * 문제읽기
 * 
 * 문자열 S 에서 특정 알파벳 a와 구간 [l, r] 이 주어졌을 때,
 * S의 l번째 문자열부터 r 번째 문자 사이에 a가 몇번 나타나는지 확인하기
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // 입출력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split("");
        int q = Integer.parseInt(br.readLine()); // 질문수
        
        while(q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String ch = st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            int answer = 0;
            for(int i=start; i<=end; i++) {
            	if(s[i].equals(ch)) answer++;
            }
            
            System.out.println(answer);
        	
        }

    
    }
}
