import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

// https://www.acmicpc.net/problem/1978


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<n; i++) {
			int temp = Integer.parseInt(br.readLine());
			
			if(temp != 0) {
				stack.push(temp);
			}else {
				stack.pop();
			}
		}
		
		// 합 구하기
		if(stack.isEmpty() || stack.size() == 1) {
			System.out.println(0);
		}else {
			int ans = 0;
			
			while(stack.size() > 0) {
				ans += stack.pop();
			}

			System.out.println(ans);
		}

		
	}


}