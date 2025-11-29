import java.util.Scanner;

// https://www.acmicpc.net/problem/2839

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int answer = 0;
		
		// while 문으로 설탕봉지(N)가 0이 될 때 까지 (혹은 음수- 가 될 때 멈춤)
		while(N >= 0) {
			
			if(N%5 == 0) {
				// 5kg 봉지 챙기기
				answer += N/5;
				break;
			}else {
				// 3kg 봉지 챙기기
				N -= 3;
				answer++;
			}
		}
		
		// 배달이 불가능한 경우도 존재함에 유의
		if(N < 0) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
		
		
	}
}