import java.util.*;
import java.io.*;

/**
 * [문제읽기]
 * 바이러스를 유출하려고함
 * 
 * - 바이러스는 활성/비활성 상태가 있음 (초기 모든 바이러스는 '비활성')
 * - 활성 상태 바이러스는 상하좌우 인접한 모든 '빈 칸(0)' 으로 '동시에 복제' => 1초
 *   연구소의 바이러스 M개를 활성상태로 변경하려고 함
 *   빈칸(0), 벽(1), 바이러스(2)
 *   
 *   
 * [요구사항]
 * - 주어진 map 에서 비활성화 상태의 바이러스 x 개를 활성화상태로 M개만큼 변경!
 * - 모든 빈칸에 바이러스를 퍼뜨리는 최소 시간 구하기
 * - 단, 모든 칸에 바이러스를 퍼뜨려야 하지만, 벽으로 막힌 빈 공간의 경우 바이러스가 도달할 수 없는 위치임
 */



public class Main {
	
	static int N, M;
	static int answer = Integer.MAX_VALUE ;
	
	static int[][] map;
	static int emptySpaces = 0;
	
	static List<Node> activeVirus = new ArrayList<>();
	static List<Node> virusLocation = new ArrayList<>();
	

    static int[] dxs = {-1, 0, 1, 0};
    static int[] dys = {0, 1, 0, -1};

	static class Node {
		int x, y, time;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		Node(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

    public static void main(String[] args) throws IOException { 
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	map = new int[N][N];
    	
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());

    			if(map[i][j] == 0) emptySpaces++;
    			if(map[i][j] == 2) virusLocation.add(new Node(i, j));
    		}
    	}

    	// Simulation
    	dfs(0, 0);
    	
    	// Result
    	System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
    
    
    // 감염시킬 바이러스 선택하기
    private static void dfs(int idx, int cnt) {
    	if(cnt == M) {
    		// 감염시킬 바이러스를 모두 선택했다면, 동시 감염 시작
    		bfs(activeVirus);
    		return;
    	}
    	
    	
    	// 바이러스 선택하기 n개 뽑기
    	for(int i=idx; i<virusLocation.size(); i++) {
    		activeVirus.add(virusLocation.get(i));
    		dfs(i+1, cnt + 1);
    		activeVirus.remove(activeVirus.size() - 1);
    	}
    }

    // 감염시키기
	private static void bfs(List<Node> selectedViruses) {
		
		Queue<Node> queue = new LinkedList<Main.Node>();
		boolean[][] visited = new boolean[N][N];
		int cnt = 0;
		int time = 0;
		
		// 선택된 바이러스들을 모두 보기위해 큐에 넣고, 방문처리 함
		for(Node node: selectedViruses) {
			queue.add(new Node(node.x, node.y));
			visited[node.x][node.y] = true;
		}
		
		
		// 감염시키기
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dxs[i];
				int ny = cur.y + dys[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == 1) continue;
				
				// 이동 가능한 곳
				visited[nx][ny] = true;
				
				// 빈칸 감염
				if(map[nx][ny] == 0) {
					cnt++;
					time = cur.time + 1;
				}
				
				// 비활성 바이러스, 빈칸 모두 큐에 넣어 전파
				queue.add(new Node(nx, ny, cur.time + 1));
			}
		}
		
		
		// 감염시킨 칸이 초기에 세두었던 빈칸을 충족시키는지?
		if(cnt == emptySpaces) {
			answer = Math.min(answer, time);
		}
		
	}
    
 

} 