import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        
        // 연결리스트 만들기
        List<Integer>[] graph = new ArrayList[n+1];
        
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<wires.length; i++){
            int a = wires[i][0];
            int b = wires[i][1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // 전선을 하나씩 끊어보며 완전탐색
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<wires.length; i++){
            int cutA = wires[i][0];
            int cutB = wires[i][1];
            
            boolean[] visited = new boolean[n + 1];
            
            // 한쪽 노드를 세면 반대쪽 노드의 개수도 알 수 있다.
            int countA = dfs(graph, visited, cutA, cutA, cutB);
            int countB = n - countA;
            
            // 정답 갱신
            int diff = Math.abs(countA - countB);
            answer = Math.min(answer, diff);
        }
        
        
        return answer;
    }
    
    // DFS
    static int dfs(List<Integer>[] graph, boolean[] visited, int node, int cutA, int cutB){
        visited[node] = true;
        int count = 1;
        
        // for
        for(int next: graph[node]){
            
            // 끊은 전선은 Jump
            if((node == cutA && next == cutB) || (node == cutB && next == cutA)) continue;
            
            // 방문 가능한 것만 coutn
            if(!visited[next]) count += dfs(graph, visited, next, cutA, cutB);
        }
        
        // return
        return count;
    }
}