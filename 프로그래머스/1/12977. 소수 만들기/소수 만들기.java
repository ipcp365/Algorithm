import java.io.*;
import java.util.*;

class Solution {
        
    public int solution(int[] nums) {
        
        int n = nums.length;
        int ans = 0;
        
        // 3중 for 문으로 중복없이 선택하기
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (isPrime(sum)) ans++;
                }
            }
        }
        
        // Result
        return ans;
    }
    
    
    // 소수 판단하기
    private boolean isPrime(int x) {
        // 2보다 작은 경우는 무조건 패스 (1, 2 는 누가봐도 소수)
        if (x < 2) return false;
        
        // 합성수가 있을 수도 있기 때문에 %2==0, %3==0 조건만으로는 부족함
        for (int d = 2; d * d <= x; d++) {
            if (x % d == 0) return false;
        }
        return true;
    }
    
}