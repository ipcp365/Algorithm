class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {

    	String[] answer = new String[n];
    	
    	// 정수를 이진수로 변환
    	String[] first = new String[n];
    	String[] second = new String[n];
    	for(int i=0; i<n; i++) {
    		String format = "%0" + n + "d";
    		
    		String binary = Integer.toBinaryString(arr1[i]);
    		first[i] = String.format("%" + n + "s", binary).replace(' ', '0');
    		
    		binary = Integer.toBinaryString(arr2[i]);
    		second[i] = String.format("%" + n + "s", binary).replace(' ', '0');
    	}
    	
    	// 지도 그리기
    	for(int i=0; i<n; i++) {
    		String cur = "";
    		
    		for(int j=0; j<n; j++) {
    			if(first[i].charAt(j) == '1' || second[i].charAt(j) == '1') {
    				cur += "#";
    			}else {
    				cur += " ";
    			}
    		}
    	
    		// 넣기
    		answer[i] = cur;
    	}
        
        // Result
        return answer;
    }
}