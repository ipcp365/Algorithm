import java.util.*;
import java.io.*;

/**
 * [문제읽기]
 * 뿌요뿌요! PUYO PUYO !
 * - 뿌요는 중력의 영향을 받아서 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
 * - 1. 뿌요를 놓고난 후, 같은 색 뿌요가 4개 이상 상-하-좌-우 연결되어 있으면 뿌요들이 한꺼번에 없어진다. => 1연쇄 시작
 *   2. 뿌요들이 없어지고 나서 위에 다른 부요들이 있다면 중력의 영향 받아 아래로 떨어짐 (차례로)
 *   
 *   1번 과정을 다시 반복 => 터진 뿌요들이 내려오고 다시 터짐을 반복하는 것이 1연쇄
 *   터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고, 여러 그룹이 터지더라도 한번의 연쇄!
 *   
 *   
 * [요구사항]
 * - 몇번의 연쇄가 이루어지는지 출력하기
 * - R 빨강, G 초록, B 파랑, P보라, Y 노랑
 */



public class Main {
	
	static int N = 12;
	static int M = 6;
	static int answer = 0;
	static char[][] map = new char[N][M];
	
	static char R = 'R';
	static char G = 'G';
	static char B = 'B';
	static char P = 'P';
	static char Y = 'Y';
	static char DOT = '.';
	
	static class Node {
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

    public static void main(String[] args) throws IOException { 
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	for(int i=0; i<N; i++) {
    		String row = br.readLine();
    		for(int j=0; j<M; j++) {
    			map[i][j] = row.charAt(j);
    		}
    	}
    	
    	//helper_printMap(map);

    	
    	// Simulation
    	while(true) {
    		boolean[][] visited = new boolean[N][M];
    		List<Node> allDelete = new ArrayList<>();
    		
    		// 1. 이번에 터질 부요
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<M; j++) {
        			
        			// 건너 뛰는 경우
        			if(map[i][j] == '.' || visited[i][j]) continue;
        			
        			// 그외의 경우 : 제거할지 말지 기준이 되는 색상을 찾은 것! 탐색으로 제거 대상에 포함되는지 봐야함
        			List<Node> group = bfs(i, j, visited);
        			
        			// 4개 이상이면 펑 !
        			if(group.size() >= 4) {
        				allDelete.addAll(group);
        			}
        		}
        	}
        	
        	// 2. 터질게 없다면 종료
        	if(allDelete.isEmpty()) break;
        	
        	// 3. 터트리기
        	for(Node node : allDelete) {
        		map[node.x][node.y] = DOT; 
        	}
        	
        	// 4. 내리기
        	downPuyo();
    		
        	// 5. 증가
        	answer++;
    	}
    	
    	
    	// Result
    	System.out.println(answer);
    }

	
    private static List<Node> bfs(int sx, int sy, boolean[][] visited){
    	Queue<Node> queue = new LinkedList<>();
    	List<Node> group = new ArrayList<>();
    	
    	queue.offer(new Node(sx, sy));
    	visited[sx][sy] = true;
    	group.add(new Node(sx, sy));
    	
    	char color = map[sx][sy];
    	
    	// 탐색하기 
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for(int dir=0; dir<4; dir++) {
				
				int nx = cur.x + dxs[dir];
				int ny = cur.y + dys[dir];
				
				// 일치하지 않음 or 탐색불가
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || color != map[nx][ny]) continue;
				
				// 같은 친구 발견
				visited[nx][ny] = true;
				queue.add(new Node(nx, ny));
				group.add(new Node(nx, ny));
			}
		}
		
		// return
		return group;
    }




    static int[] dxs = {-1, 0, 1, 0};
    static int[] dys = {0, 1, 0, -1};

	private static void downPuyo() {
		
		for(int col=0; col<M; col++) {
			// 세로줄을 기준으로 내릴 뿌요를, 큐에 넣어 하나로 묶어서 동시에 이동시킨다.
			Queue<Character> queue = new LinkedList<Character>();
			
			for(int row = N-1; row>=0; row--) {
				if(map[row][col] != DOT) {
					queue.offer(map[row][col]);
				}
			}
			
			// 새로 갱신하기 위해 col을 전부 초기화
			for(int row=0; row<N; row++) {
				map[row][col] = DOT;
			}
			
			// 채우기
			int row = N-1;
			while(!queue.isEmpty()) {
				map[row][col] = queue.poll();
				row--;
			}
		}
		
	}
    


} 