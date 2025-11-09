import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;

    public static void main(String[] args) throws NumberFormatException, IOException{
    	
    	N = Integer.parseInt(br.readLine());

    	System.out.println(fac(N));
    }

	private static int fac(int n) {
		if(n <= 1) return 1;
		return n * fac(n-1);
	}
    
}