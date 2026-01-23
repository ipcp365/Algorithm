import java.io.*;
import java.util.*;

/**
 * [문제 읽기]
 * - N*M 크기 배열 미로
 * - 이동가능(1), 이동 불가능(0)
 * - (1, 1) 에서 출발해서 (N, M) 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하여라 (단, 서로 인접한 칸으로만 이동할 수 있음)
 */
public class Main {
	
	static int N, M, answer;
	static int[][] map;
	static int[] dxs = {-1, 0, 1, 0};
	static int[] dys = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		// init & input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		answer = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		
		// Simulation
		bfs(0, 0);

		
		// Result
		System.out.println(map[N-1][M-1]);
	}
	
	
	static class Node{
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	private static void bfs(int row, int col) {
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(row, col));
		
		
		// 반복
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			// 방향 탐색
			for(int dir=0; dir<4; dir++) {
				int nx = cur.x + dxs[dir];
				int ny = cur.y + dys[dir];
				
				// 탐색 불가 조건
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != 1) continue;
				
				map[nx][ny] = map[cur.x][cur.y] + 1;
				q.add(new Node(nx, ny));

			}
		}
	}


}
