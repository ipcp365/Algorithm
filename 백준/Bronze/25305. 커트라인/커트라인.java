import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nk = br.readLine().split(" ");
		
		int N = Integer.parseInt(nk[0]);
		int K = Integer.parseInt(nk[1]);
		
		//int[] score = new int[N];
		List<Integer> score = new ArrayList<>();
		
		String[] arr = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			score.add(Integer.parseInt(arr[i]));
		}
		
		Collections.sort(score, Comparator.reverseOrder());
		
		
		System.out.println(score.get(K-1));

	}

}