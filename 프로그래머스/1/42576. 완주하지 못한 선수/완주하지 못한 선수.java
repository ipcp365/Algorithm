import java.io.*;
import java.util.*;

/**
[문제읽기]
- participant(전체 참가자), completion(완주자)
- 완주하지 못한 선수들 이름을 return
- 동명이인이 있을 수 있다.
*/
class Solution {
    public String solution(String[] participant, String[] completion) {
        
        // 1. 모든 참가자 기록
        HashMap<String, Integer> map = new HashMap<>();
        for(String people: participant){
            map.put(people, map.getOrDefault(people, 0) + 1);
        }
        
        // 2. 완주자 제거
        for(String people: completion){
            if(map.get(people) == 1){
                map.remove(people);
            }else{
                map.put(people, map.get(people)-1);
            }
        }
        
        // 3. 완주하지 못한 선수
        String answer = "";
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            answer += entry.getKey();
        }
        
        return answer;
    }
}