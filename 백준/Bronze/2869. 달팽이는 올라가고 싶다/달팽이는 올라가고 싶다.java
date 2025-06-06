import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] abv = br.readLine().split(" ");
        
        int A = Integer.parseInt(abv[0]); // 올라가기
        int B = Integer.parseInt(abv[1]); // 내려오기
        int V = Integer.parseInt(abv[2]); // 나무높이
        

        // 꼭대기에 도달한 경우 내려오면 안된다.
        // 그 상황을 방지하기 위해 일부러 높이에서 내려가는 만큼 빼준다.
        int day = (V - B) / (A - B);
        
        
 
        // 정확히 나누어 떨어지지 않으면 하루가 더 필요
        if ((V - B) % (A - B) != 0) {
            day++;
        }

        
        System.out.println(day);
        
        
    }
}