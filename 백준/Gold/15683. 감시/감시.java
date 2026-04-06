import java.util.*;
import java.io.*;

/**
 * [문제읽기]
 * - N*M 크기의 사무실
 * - 총 K개의 CCTV 가 있으며, 감시할 수 있는 방향이 다른 총 5 종류의 CCTV 가 존재함
 * - CCTV 는 감시할 수 있는 방향에 있는 칸 '전체'를 감시할 수 있음. 단 벽은 통과할 수 없음
 * - CCTV가 감시할 수 없는 영역 => 사각지대로 표현
 * - CCTV 는 회전시킬 수 있는데, 회전은 항상 90도 방향으로만 해야하며, 감시하려 하는 방향이 가로 또는 세로 방향이어야 함(대각선은 불가 함을 강조)
 * - CCTV 끼리는 통과할 수 있다.
 * - 벽(6), 빈 공간(0)
 * 
 * - CCTV 는 종류에 따라 1~5번 번호를 가진다.
 *   한 방향만 감시
 *   수평 or 수직 형태 감시
 *   두 방향이 직각형태만 감시 가능한 형태
 *   세 방향 감시
 *   네 방향 감시
 * 
 * - CCTV 방향을 적절히 정하여 사각 지대의 최소 크기를 구하여라
 * 
 */



public class Main {
	
	static int answer = Integer.MAX_VALUE;
	static int N, M;
	static int[][] grid;
	static List<Cctv> cctvs = new ArrayList<>();
	
	static int[] dxs = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dys = {0, 1, 0, -1};
	static int[][][] dirs = {
			{},
			{{0}, {1}, {2}, {3}},
			{{0, 2}, {1, 3}},
			{{0, 1}, {1, 2}, {2, 3}, {3, 0}},
			{{0,1,3}, {0,1,2}, {1,2,3}, {0,2,3}},
			{{0,1,2,3}}
	};
	
	static class Cctv {
		int x, y, type;
		
		Cctv(int x, int y, int type){
			this.x = x;
			this.y = y;
			this.type = type;
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
    			
    			// CCTV 를 발견한 경우 넣어주기
    			if(grid[i][j] != 0 && grid[i][j] != 6) {
    				cctvs.add(new Cctv(i, j, grid[i][j]));
    			}
    			
    		}
    	}
    	
    	// Simulation
    	dfs(0, grid);
    	
    	
    	// Result
    	System.out.println(answer);
    }

    private static void dfs(int idx, int[][] curMap) {
    	
    	// 종료조건
    	if(idx == cctvs.size()) {
    		int temp = 0;
    		for(int i=0; i<N; i++) {
    			for(int j=0; j<M; j++) {
    				if(curMap[i][j] == 0) temp++;
    			}
    		}
    		
    		answer = Math.min(answer, temp);
    		return;
    	}
    	
    	// 탐색
    	Cctv cctv = cctvs.get(idx);
    	int x = cctv.x;
    	int y = cctv.y;
    	int type = cctv.type;
    	
    	for(int[] dirSet : dirs[type]) {
    		int[][] copy = copyMap(curMap);
    		
    		for(int dir : dirSet) {
    			watch(x, y, dir, copy);
    		}
    		
    		dfs(idx + 1, copy);
    	}
    	
    	
    }
    
    // 배열 복사
    private static int[][] copyMap(int[][] map){
    	int[][] newMap = new int[N][M];
    	for(int i=0; i<N; i++) {
    		newMap[i] = map[i].clone();
    	}
    	
    	return newMap;
    }
    
    // 감시한곳 표시하기
    private static void watch(int x, int y, int dir, int[][] map) {
    	int nx = x;
    	int ny = y;
    	
    	// 벽을 만나기 전까지는 계속 감시할 수 있으므로, dir 의 방향을 바꾸지 않아도 된다.
    	// 그래서 while(true) 조건을 사용해서 벽을 만나거나 범위를 벗어나 끝나는 시점에서 탐색을 종료하기 된다.
    	while(true) {
    		nx += dxs[dir];
    		ny += dys[dir];
    		
    		// 감시 불가 영역
    		if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 6) break;
    		
    		// 감시영역 표시하기
    		if(map[nx][ny] == 0) map[nx][ny] = -1;
 
    	}
    }
    
} 