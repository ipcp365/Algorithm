import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		String[] temp = br.readLine().split(" ");
		for(int i=0; i<n; i++) arr[i] = Integer.parseInt(temp[i]);
		
		// 가장 인출금액이 적은 사람 부터 뽑으 된다.
		Arrays.sort(arr);
		
		int time = 0;
		int ans = 0;
		for(int i=0; i<n; i++) {
			time += arr[i];
			ans += time;
			
		}
		
		System.out.println(ans);
		
	}
}