import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        // init
    	int[] A = {1, 2, 3, 4, 5};
    	int[] B = {2, 1, 2, 3, 2, 4, 2, 5};
    	int[] C = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    	
    	int a = 0;
    	int b = 0;
    	int c = 0;
    	for (int i = 0; i < answers.length; i++) {
            if (answers[i] == A[i % A.length]) a++;
            if (answers[i] == B[i % B.length]) b++;
            if (answers[i] == C[i % C.length]) c++;
        }
        
        // a, b, c 중 최대값을 찾는다. 이후 이 값과 일치하는 경우만 list에 추가해준다.
        int max = Math.max(a, Math.max(b, c));
    	List<Integer> res = new ArrayList<>();
        if (a == max) res.add(1);
        if (b == max) res.add(2);
        if (c == max) res.add(3);
    	
    	// Result
    	return res.stream().mapToInt(Integer::intValue).toArray();
    }
}