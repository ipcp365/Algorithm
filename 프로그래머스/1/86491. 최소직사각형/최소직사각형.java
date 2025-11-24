import java.io.*;
import java.util.*;

/**
- 모든 명함을 수납할 수 있는 가장 작은 지갑의 크기 구하기 (가로*세로)
*/
class Solution {
    public int solution(int[][] sizes){
        
        int maxW = 0;
        int maxH = 0;
        
        for(int[] card: sizes){
            int w = Math.max(card[0], card[1]);
            int h = Math.min(card[0], card[1]);
            
            maxW = Math.max(maxW, w);
            maxH = Math.max(maxH, h);
        }
    
    
        return maxW * maxH;
    }
}