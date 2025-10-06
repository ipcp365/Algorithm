import java.io.*;
import java.util.*;

/**
[문제 읽기]
- 연속적으로 같은 발음 불가 ex) yeye 불가 !
*/
class Solution {
    
    static String[] talk = {"aya", "ye", "woo", "ma"};
    
    public int solution(String[] babbling) {
        
        int answer = 0;       
        
        for (String s : babbling) {
            if (isPronounceable(s)) answer++;
        }
        return answer;
    }
    
    boolean isPronounceable(String str){
        int idx = 0;
        String pre = "";
        
        while(idx < str.length()){
            boolean matched = false;
            
            for(String word: talk){
                // 같은 단어는 연속으로 사용 금지
                if(word.equals(pre)) continue;
                
                // 일치 여부
                if(str.startsWith(word, idx)){
                    idx += word.length();
                    pre = word;
                    matched = true;
                    break;
                }
            }
            if(!matched) return false;
        }
        
        return true;
    } // ... isPronounceable
        
}