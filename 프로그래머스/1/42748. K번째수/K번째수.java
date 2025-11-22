import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int cnt=0; cnt<commands.length; cnt++){
            int i = commands[cnt][0];
            int j = commands[cnt][1];
            int k = commands[cnt][2];
            
            // i ~ j 까지 자르기
            List<Integer> list = new ArrayList<>();
            for(int idx=0; idx<=j-i; idx++){
                list.add(array[idx+i-1]);
            }
            
            // 정렬하기
            Collections.sort(list);
            
            // k 번째 수 뽑아 answer 에 넣기
            answer[cnt] = list.get(k-1);
        }
        
        return answer;
    }
}