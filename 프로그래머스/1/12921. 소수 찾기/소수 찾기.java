class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i=1; i<=n; i++){
            if(isPrime(i)) answer++;
        }
        return answer;
    }
    
    boolean isPrime(int num){
        
        // 0, 1 은 소수가 아님
        if(num < 2) return false;
        
        // 소수인지 판단
        for(int d=2; d*d<=num; d++){
            if(num%d == 0) return false;
        }
        
        // 위에서 걸리지 않은 경우 소수
        return true;
    }
}