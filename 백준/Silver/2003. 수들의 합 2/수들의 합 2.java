import java.io.*;
import java.util.*;


class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// C
		long answer = 0;
		for (int i = 0; i < N; i++) {
		    long sum = arr[i];

		    if (sum == M) {
		        answer++;
		        continue;
		    }

		    for (int j = i + 1; j < N; j++) {
		        sum += arr[j];
		        if (sum == M) {
		            answer++;
		            break;
		        } else if (sum > M) {
		            break;
		        }
		    }
		}
		
		
		
		// Result
		System.out.println(answer);

	}
}