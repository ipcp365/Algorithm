import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 1. 현재 보유량
		int chicken = Integer.parseInt(st.nextToken());
		int beef = Integer.parseInt(st.nextToken());
		int pasta = Integer.parseInt(st.nextToken());
		
		// 2. 주문량
		st = new StringTokenizer(br.readLine());
		int orderChicken = Integer.parseInt(st.nextToken());
		int orderBeef = Integer.parseInt(st.nextToken());
		int orderPasta = Integer.parseInt(st.nextToken());
		
		// 3. 계산하기
		int answer = 0;
		if(chicken-orderChicken < 0) {
			answer += orderChicken-chicken;
		}
		
		if(beef-orderBeef < 0) {
			answer += orderBeef-beef;
		}
		
		if(pasta-orderPasta < 0) {
			answer += orderPasta-pasta;
		}
		
		
		// 4. 결과 출력
		System.out.println(answer);
	}
}


