import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
    public static void main(String[] args) throws Exception{
     
    	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	   String n = br.readLine();
    	   
    	   int[] arr = new int[10];
    	   
    	   // 각 숫자 인덱스에 필요한 개수 count
    	   for(int i=0; i<n.length(); i++) {
    		   int num = n.charAt(i) - '0';
    		   arr[num]++;
    	   }
    	   
    	   // 6과 9의 경우, 같은 수를 공유하기 때문에 둘중 하나만 남겨두기
    	   int sixAndNine = arr[6] + arr[9];
    	   arr[6] = (sixAndNine + 1) / 2;
    	   arr[9] = 0;
    	   
    	   // 최대값 찾기
    	   int answer = 0;
    	   for(int j=0; j<arr.length; j++) {
    		   answer = Math.max(arr[j], answer);
    	   }
    	   
    	   System.out.println(answer);
    }
}
