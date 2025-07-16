import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N 명의 학생은 자신의 예상 등수를 적어서 제출
 * 
 * 1~N등 까지 동석차 없이 등수를 매겨야 하는 상황
 * 학생들이 제출한 예상등수 => 임의 등수로 해결 하기로 함
 * 
 * 등수를 A 로 예상했으나, B등이 된경우 => |A-B| 불만도를 가진다.
 * 
 * 불만도의 총합을 최소화 하며 학생들의 등수를 매기고자 한다.
 */
public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 학생의 수
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        for(int i=1; i<=N; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        // 정렬
        Arrays.sort(arr);
        
        // 불만도 절대값 계산
        long answer = 0;
        for(int i=1; i<=N; i++) {
        	answer += Math.abs((i - arr[i]));
        }
        
        System.out.println(answer);
	}


}
