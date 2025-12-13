import java.io.*;
import java.util.*;

/*
[문제읽기]
- 무게제한 있는(limit) 구명보트 + 최대 2명 탑승 가능
- 구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 한다.
*/
class Solution {
    public int solution(int[] people, int limit) {
        
        // 정렬
        Arrays.sort(people);
        
        // 포인터 사용하기
        int answer = 0;
        int left = 0;
        int right = people.length - 1;
        
        while(left <= right){
            
            // 보트 하나에 두명을 태울 수 있는 경우
            if(people[left] + people[right] <= limit){
                left++;
                right--;
            }else{
                // 보트 하나에 다 태울 수 없는 경우
                right--;
            }
            
            // 가장 무거운 사람과 가장 가벼운 사람을 비교 했기 때문에, 위에서 2명을 동시에 태우지 못했다면
            // 그 사람은 혼자 타야하는 운명!
            answer++;
        }
        
        return answer;
    }
}