import java.util.*;
import java.io.*;

/**
[문제읽기]
- 과일은 1~k 점까지 품질등급별 점수가 있음 (1점이 최하품)
- 한 상자에 사과를 m 개씩 담아 포장
- 상자에 담긴 사과 중, 가장 낮은 점수가 p 점인 경우, 사과 한상자의 가격은 p*m
- 가능한 많은 사과를 팔았을 때, 얻을 수 있는 최대 이익
  사과는 상자 단위로만 판매하며, 남은 사과는 버린다.
  이익이 발생하지 않은 경우에는 0을 return
*/
class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        // 1차 정렬 : 오름 차순
        Arrays.sort(score);
        
        // 2차 정렬 : 임시 변수를 활용하여 역순 정렬
        int j = score.length - 1;
        for (int i = 0; i < j; i++) {
            int tmp = score[i]; 
            score[i] = score[j]; 
            score[j] = tmp;
            j--;
        }
        
        int start = 0;
        while(start + m <= score.length){
            // 가능한 경우
            int lowScore = score[start+m - 1];
            answer += (lowScore * m * 1);
            
            // 포장 위치 갱신
            start += m;
        }
    
        return answer;
    }
}