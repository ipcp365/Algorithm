class Solution {
    public int solution(String[] babbling) {
    	int answer = 0;
    	String[] talks = {"aya", "ye", "woo", "ma"};
    	
    	for(String word: babbling) {
    		
    		String cur = word;
    		for(String talk: talks)  cur = cur.replace(talk, " "); 
    		
    	    // 허용 음절만 있었다면 다 지워져서 빈 문자열이어야 함
    	    if (cur.replace(" ", "").isEmpty()) answer++;
    	}

		// Result
    	return answer;
    	// System.out.println(answer);
    }
}