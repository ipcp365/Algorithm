import java.io.*;
import java.util.*;

/**
[문제읽기]
- N마리중 N/2 마리를 가져갈 수 있다.
- 같은 종류의 포켓몬은 같은 번호를 가지고 있다.
- 최대한 많은 종류의 포켓몬을 포함하여 N/2마리를 선택했을 때, 최종적으로 몇가지 종류의 포켓몬을 가질 수 있는가?
*/
class Solution {
    public int solution(int[] nums) {
        
        // 1. 데이터 누적
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // 2. map 의 사이즈가 nums/2 이상이면 바로 정답
        int answer = 0;
        if(map.size() >= nums.length/2){
            answer = nums.length/2;
            return answer;
        }else{
            return map.size();
        }
    }
}