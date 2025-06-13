import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 세 각의 크기가 모두 60이면, Equilateral
세 각의 합이 180이고, 두 각이 같은 경우에는 Isosceles
세 각의 합이 180이고, 같은 각이 없는 경우에는 Scalene
세 각의 합이 180이 아닌 경우에는 Error
 * 
 */
public class Main {
	
    public static void main(String[] args) throws Exception{
     
    	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	   
    	   int a = Integer.parseInt(br.readLine());
    	   int b = Integer.parseInt(br.readLine());
    	   int c = Integer.parseInt(br.readLine());
    	   
    	   int sum = a+b+c;
    	   
    	   if(sum != 180) {
    		   System.out.println("Error");
    	   }else if(a==60 && b==60 && c==60) {
    		   System.out.println("Equilateral");
    	   }else if(a==b || a==c || b==c) {
    		   System.out.println("Isosceles");
    	   }else {
    		   System.out.println("Scalene");
    	   }
    	  
    	   
    }
}
