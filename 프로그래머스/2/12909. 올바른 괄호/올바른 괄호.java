import java.io.*;
import java.util.*;

class Solution {
    boolean solution(String s) {
        
        
        Stack<Character> stack = new Stack<>();
        for(char ch: s.toCharArray()){
            
            // [조건1] ( 는 순서에 상관없이 무조건 삽입
            if(ch == '('){
                stack.push(ch);
                continue;
            }
            
            // [조건2] 비어있는 상태에서 닫는괄호가 주어질 경우 무조건 false
            if(stack.isEmpty() && ch == ')') return false;
            
            // [조건3] 직전괄호가 여는 괄호이며, 이번 괄호가 닫히는 경우, 직전 괄호를 제거한다.
            if(stack.peek() == '(' && ch == ')') {
                stack.pop();
            }else{
                // [조건4] 여는 괄호 없이 닫힘괄호가 또 오는 경우 올바른 괄호가 아님 
                return false;
            }

        }
        
        
        return stack.size() == 0 ? true : false;
    }
}