import java.util.*;
import java.io.*;

/**
 * [문제읽기]
 * - 판의 가장자리에는 X 표시에는 치즈가 없다.
 * - 그 외에는 치즈가 있을 수도 없을 수도 !
 * - 공기와 접촉한 칸의 치즈는, 한 시간이 지나면 녹는다.
 *   치즈의 구멍 속에는 공기가 없지만 둘러쌓 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어감
 *   (즉 치즈 덩어리 안에 공기구멍은 외부와 연결된게 아니라면 영향력이 없음)
 *   
 *   문제 특성상 테두리에는 치즈가 없기 때문제 무조건 치즈가 녺에되는 구조임
 *   
 * [요구사항]
 * - 치즈가 모두 녹아 없어지는 데 걸리는 시간, 모두 녹기 한 시간 전에 남아있는 치즈 고각이 놓여있는 칸의 개수
 */



public class Main {
	
	static int N, M;
	static int[][] grid;
	
	static int time = 0;
	static int cheese = 0;
	
	static class Node {
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

    public static void main(String[] args) throws IOException { 
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	grid = new int[N][M];
    	
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<M; j++) {
    			grid[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	
    	// Simulation
    	while (true) {
    		// 1. 치즈 녹이기 수행
    	    int melted = melt();
    	    
    	    // 2. 마지막 치즈가 사라졌을 땐 값을 변경하지 않고 끝냄
    	    if (melted == 0) break;
    	    
    	    // 3. 갱신
    	    time++;
    	    cheese = melted;
    	}
    	
    	// Result
    	System.out.println(time);
    	System.out.println(cheese);
    }
    
    static int[] dxs = {-1, 1, 0, 0};
    static int[] dys = {0, 0, -1, 1};
    
    private static int melt() {
    	boolean[][] visited = new boolean[N][M];
    	Queue<Node> queue = new LinkedList<>();
    	List<Node> meltList = new ArrayList<>();
    	
    	// 시작값 설정
    	queue.offer(new Node(0, 0));
    	visited[0][0] = true;
    	
    	// 탐색
    	while(!queue.isEmpty()) {
    		Node cur = queue.poll();
    		int x = cur.x;
    		int y = cur.y;
    		
    		for(int dir=0; dir<4; dir++) {
    			int nx = x + dxs[dir];
    			int ny = y + dys[dir];
    			
    			// 탐색 불가 조건
    			if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
    			
    			// 탐색 : 공기를 발견 !
    			if(grid[nx][ny] == 0) {
    				visited[nx][ny] = true;
    				queue.offer(new Node(nx, ny));
    			}else if(grid[nx][ny] == 1) {
    				// 치즈를 발견 !
    				visited[nx][ny] = true;
    				meltList.add(new Node(nx, ny));
    			}
    		}
    	}
    	
    	// 치즈 녹이기
    	for(Node pos : meltList) {
    		grid[pos.x][pos.y] = 0;
    	}
    	
    	// meltList 는 마지막에 녹이기 위한 치즈의 수 == 즉 마지막까지 살아남은 치즈 수를 의미함
    	return meltList.size();
    	
    }


} 