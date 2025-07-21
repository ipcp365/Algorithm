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
    

    	// 계산 초기 M 일의 합계 기본 셋팅
    	int sum = 0;
		for(int i = 0; i < M; i++) {
			sum += arr[i];
		}
		
		// 슬라이딩 윈도우 사용하기 : 한칸 씩 밀면서 값을 더하고, 빼준다.
		// 초기 M 일의합계를 구하였기 때문에 시작값은 M으로 한다.
		int max = sum;
		int count = 1;
		for(int i=M; i<N; i++) {
			// 슬라이딩 윈도우: 이전 구간에서 앞의 값은 빼고, 새로 들어온 값을 더함
			sum = sum - arr[i-M] + arr[i];
			
			if(sum > max) {
				max = sum;
				count = 1;
			}else if(sum == max) {
				count++;
			}
		}


    	// 출력
    	if(max == 0) System.out.println("SAD");
    	else System.out.println(max + "\n" + count);
    }
}
