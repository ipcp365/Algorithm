import java.io.*;
import java.util.*;

class Solution{
    public int solution(String s){
        
        Deque<Character> st = new ArrayDeque<>();
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            
            if(!st.isEmpty() && st.peekLast() == c){
                // 둘이 같으면 제거
                st.pollLast();
            }else{
                // 둘이 다르면 추가
                st.addLast(c);
            }
        }
        
        // Result
        return st.isEmpty() ? 1 : 0;
    }
}