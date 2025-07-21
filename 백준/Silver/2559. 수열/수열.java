import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	int[] arr = new int[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<N; i++)
    		arr[i] = Integer.parseInt(st.nextToken());
    
    	// 슬라이딩 윈도우 계산하기
    	int sum = 0;

    	// 초기 구간 [0 ~ M-1]까지의 합
    	for (int i = 0; i < M; i++) {
    	    sum += arr[i];
    	}

    	int answer = sum;
    	for (int i = M; i < N; i++) {
    	    sum = sum - arr[i - M] + arr[i];  // 앞은 빠지고 뒤는 추가
    	    answer = Math.max(answer, sum);
    	}

    	// 출력
    	System.out.println(answer);
    }
}
