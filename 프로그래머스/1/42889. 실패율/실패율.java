import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        /**
    	 * 1. N 번째 스테이지에서 멈춘 사람 구하기
    	 * - 스처 지나간 모든 사용자가 아닌 특정 스테이지에 멈춘 사람들만 계산한다.
    	 */
    	int[] fail = new int[N+1];
    	for(int s: stages) {
    		if(1 <= s && s <= N) fail[s]++;
    	}
    	
    	/**
    	 * 2. 실패율 계산
    	 * - 사용자는 최소 stage1 이상 위치에 머물러 있다.
    	 *   그렇기 때문에 초기에는 players 를 참여자 max 값으로 설정해도 문제가 되지 않는다.
    	 *   (최소한 다들 stage1을 통과했거나, 멈춰 있기 때문에)
    	 */
    	double[] rate = new double[N + 1];
    	int players = stages.length;
    	for(int i=1; i<=N; i++) {
    		rate[i] = (players == 0) ? 0.0 : (double) fail[i] / players;
    		players -= fail[i];
    	}
    	
    	/**
    	 * 3. 정렬
    	 * - 실패율은 내림차순으로, 동률이면 스테이지 번호를 오름차순으로 정렬하기
    	 * - order[] : 인덱스배열로만 활용하기 위한 것
    	 */
    	Integer[] order = new Integer[N];
    	for(int i=0; i<N; i++) {
    		order[i] = i+1;
    	}
    	
    	Arrays.sort(order, new Comparator<Integer>() {
    		@Override
    		public int compare(Integer a, Integer b) {
    			int cmp = Double.compare(rate[b], rate[a]);
    			
    			// 1. 실패율 기준 내림차순
    			if(cmp != 0) return cmp;
    			
    			// 2. 스테이지 번호 오름차순
    			return Integer.compare(a, b);
    		}
    	});
    	
    	/**
    	 * 4. 정답 옮겨 적기
    	 */
    	int[] answer = new int[N];
    	for(int i=0; i<N; i++) {
    		answer[i] = order[i];
    	}
        
        // Result
        // System.out.println(answer);
        return answer;
    }
}