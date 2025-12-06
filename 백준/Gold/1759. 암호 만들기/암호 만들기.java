import java.io.*;
import java.util.*;



public class Main {
	
	static int L, C;
	static char[] words;
	static char[] answer;
	static StringBuilder sb = new StringBuilder();

	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        L = Integer.parseInt(st.nextToken()); // 길이
        C = Integer.parseInt(st.nextToken()); //문자의 수
        words = new char[C];
        answer = new char[L];
        
        String[] str = br.readLine().split(" ");
        for(int i=0; i<C; i++) {
        	words[i] = str[i].charAt(0);
        }
        Arrays.sort(words);
        
        /**
         * Simulation 조건
         * - 최소 한 개의 모음(a, e, i, o, u) & 최소 두 개의 자음
         * - 정렬되어 있어야 함
         */
        dfs(0, 0);

        // Result
        System.out.println(sb.toString());
    }

	private static void dfs(int depth, int start) {
		
		// 종료조건
		if(depth == L) {
			int vowel = 0;
			int consonant = 0;
			
			for(int i=0; i<L; i++) {
				if (isVowel(answer[i])) vowel++;
				else consonant++;
			}
			
			if(vowel >= 1 && consonant >= 2) {
				sb.append(new String(answer)).append("\n");
			}
			
			return;
		}
		
		// 탐색
		for(int i=start; i<C; i++) {
			answer[depth] = words[i];
			dfs(depth+1, i+1);
		}
	}// ... dfs
	
	private static boolean isVowel(char c) {
		return c=='a' || c=='e' || c == 'i' || c=='o' || c=='u';
	}
}
