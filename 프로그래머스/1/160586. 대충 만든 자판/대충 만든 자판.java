class Solution {
    
    public int[] solution(String[] keymap, String[] targets) {
    	int[] answer = new int[targets.length];
    	
    	// 1. 입력할 '문자열' 불러오기
    	for(int i=0; i<targets.length; i++) {
    		String target = targets[i];
    		
    		// 2. 문자열의 '문자' 하나씩 불러오기 (해당 문자를 입력하기 위해선 얼마나 입력해야하는가 ?)
    		int cnt = 0;
    		for(char ch: target.toCharArray()) {
    			
    			// 최소한의 입력으로 글자를 만들 수 있는 경우
    			int press = isPush(ch, keymap);
    			
    			// 예외: 한글자라도 입력할 수 없는 경우 입력하지 못하는 문자 판단
    			if (press == -1) {    // 한 글자라도 불가능하면 -1
    		        cnt = -1;
    		        break;
    		    }
    			
    			// 입력 가능한 경우 문자열을 모두 입력할 때 까지 누적하기
    			cnt += press;
    		}
            
    		// 갱신
    		answer[i] = cnt;
    	}
    	
    	// Result
    	return answer;
    	//System.out.println(answer[0] + " " + answer[1]);
    }

    // ch : 입력할 문자
    // 주어진 keymap을 모두 순회하며 최소한 몇번의 입력으로 완성할 수 있는 문자인지 찾아야 한다.
	private static int isPush(char ch, String[] keymap) {
		
	    int min = Integer.MAX_VALUE;
		for (String key : keymap) {
	        int idx = key.indexOf(ch);   // 못 찾으면 -1
	        if (idx != -1) {
	            // 실제 누르는 횟수는 인덱스 + 1
	            min = Math.min(min, idx + 1);
	        }
	    }
		
		return (min == Integer.MAX_VALUE) ? -1 : min;
	}
    
}