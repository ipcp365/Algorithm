import java.util.*;
import java.io.*;

/**
 * [문제읽기]
 * - 부모와 자식은 1촌 / 아버지-할아버지 1촌 / 아버지와 형제들 1촌
 *   나와 할아버지 2촌 / 나와 아버지의 형제들 3촌
 *   
 */
public class Main {
	
	static int humanA, humanB;
	static int N, M, answer;
	
	static ArrayList<Integer>[] graph;
	static boolean visited[];

    public static void main(String[] args) throws IOException {
    	
    	// init & input
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	
    	// 사촌 관계
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	humanA = Integer.parseInt(st.nextToken());
    	humanB = Integer.parseInt(st.nextToken());
    	
    	// 촌수 관계
    	M = Integer.parseInt(br.readLine());
    	
    	graph = new ArrayList[N + 1];
    	for(int i = 1; i <= N; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	for(int i=0; i<M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		graph[a].add(b);
    		graph[b].add(a);
    	}
    	

    	// Simulation : humanA와 humanB의 관계가 몇 촌인지?
    	answer = bfs(humanA, humanB);

    
    	// Result
    	System.out.println(answer);
    }

	static class Node{
    	int cur;
    	int dist;
    	
    	Node(int cur, int dist){
    		this.cur = cur;
    		this.dist = dist;
    	}
    }
    
    private static int bfs(int start, int target) {
		// Queue 초기화
    	Queue<Node> queue = new LinkedList<>();
    	visited = new boolean[N+1];
    	
    	// 초기 값
    	visited[start] = true;
    	queue.add(new Node(start, 0));
    	
    	while(!queue.isEmpty()) {
    		
    		Node current = queue.poll();
    		
    		// 정답을 찾은 경우 현제 촌수 반환
    		if(current.cur == target) {
    			return current.dist;
    		}
    		
    		// 추가탐색 조건 : start(humanA) 와 관계있는 촌수만 탐색할 수 있다.
    		for(int next : graph[current.cur]) {
    			if(!visited[next]) {
    				visited[next] = true;
    				queue.offer(new Node(next, current.dist+1));
    			}
    		}
    		
    		
    		
    	}
    	
    	
		return -1;
	}




}