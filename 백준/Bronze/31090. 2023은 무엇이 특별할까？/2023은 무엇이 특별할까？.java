import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	
		
		for(int i=0; i<N; i++) {
			// 나눌 값
			String prevYear = br.readLine();
			int lastTwoNumber = Integer.parseInt(prevYear.substring(prevYear.length()-2));
			
			// 내년 값 
			int nextYear = Integer.parseInt(prevYear)+1;
			
			
			if(nextYear % lastTwoNumber == 0) {
				System.out.println("Good");
			}else {
				System.out.println("Bye");
			}	
		}

	}
}


