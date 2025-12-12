import java.io.*;
import java.util.*;

// 앞뒤로만 빌려주기 가능 + 여벌은 가지고 있으나 스스로 없을 수 있음
// 체육수업을 들을 수 있는 학생의 최대값 구하기
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 0. 순차 정렬
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        // 1. 잃어버린 사람, 여분을 가진 사람 각각 bool 타입 생성
        boolean[] isLost = new boolean[n+1];
        boolean[] isReserve = new boolean[n+1];
        
        for(int i=0; i<lost.length; i++) isLost[lost[i]] = true;
        for(int i=0; i<reserve.length; i++) isReserve[reserve[i]] = true;
        
        // 2. 교집합 지우기 : 본인꺼 안챙기고 여벌만 챙긴 사람은 남에게 빌려줄 수 없음
        int answer = n - lost.length;
        
        for(int i=0; i<lost.length; i++){
            int cur = lost[i];
            
            if(isReserve[cur]){
                isLost[cur] = false;
                isReserve[cur] = false;
                answer++;
            }
        }
        
        // 3. Simulation
        for(int i=0; i<lost.length; i++){
            
            // 앞 과정에서 필터링 된 사람은 jump
            int cur = lost[i];
            if(!isLost[cur]) continue;
            
            // 앞 번호에 여유가 있는 경우
            if(cur-1 >= 1 && isReserve[cur-1]) {
                System.out.println("앞사람꺼 뺏기");
                isReserve[cur-1] = false;
                isLost[cur] = false;
                answer++;
                continue;
            }
    
            // 뒷 사람한테서 빌릴 수 있는 경우
            if(cur+1 <= n && isReserve[cur+1]) {
                System.out.println("뒷사람꺼");
                isReserve[cur+1] = false;
                isLost[cur] = false;
                answer++;
                continue;
            }
            
        }
        
        
        
        return answer;
    }
}