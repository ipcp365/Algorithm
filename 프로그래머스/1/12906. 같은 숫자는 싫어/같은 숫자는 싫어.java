import java.util.*;

/**
- 숫자구성 0~9
- 연속적으로 나타나는 숫자는 하나만 남기고 제거
- 단, 원소들의 순서를 유지해야 함
- stack 을 기준으로 했을때 직전 값과 동일한 경우만 제거하는 방식
*/
public class Solution {
    public int[] solution(int[] arr) {
        
        Stack<Integer> stack = new Stack<>();
        
        for(int num: arr){
            // 아무것도 없는 경우 무조건 넣기
            if(stack.isEmpty()){
                stack.push(num);
            }else if(stack.peek() != num){
                // 직전과 다른 경우에만 넣기
                stack.push(num);
            }
        }
        
        // 배열 만들어 넣기
        int[] answer = new int[stack.size()];
        for(int i=stack.size()-1; i>=0; i--){
            answer[i] = stack.pop();
        }
        

        return answer;
    }
}