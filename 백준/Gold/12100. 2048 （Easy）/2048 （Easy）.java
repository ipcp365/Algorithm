import java.io.*;
import java.util.*;

/**
 * [문제 읽기]
 * - 2048 게임(4 * 4)
 * - 보드 위 전체 블록을 상하좌우 네 방향 중 하나로 이동
 *   이때, 같은 값을 가지는 두 블록이 충돌하면 두 블록은 하나로 합쳐짐
 *   한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다. (블록이 추가 되는 경우도 없다.)
 *   똑같은 수 세 개가 있는 경우, 이동하려고 하는 쪽의 칸이 먼저 합쳐진다. (ex. 위쪽으로 움직일 때, 가장 위에 있는 블록이 먼저 합쳐짐)
 * - 보드의 크기는 N*N
 * - 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하기
 */
public class Main {
	
	static int N, answer;
	static int[] dxs = {-1, 0, 1, 0};
	static int[] dys = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		// init & input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// Simulation : 첫 시작 상/하/좌/우
		simulation(0, board);
		
		// Result
		System.out.println(answer);
	}

	private static void simulation(int cnt, int[][] board) {
		
		// 갱신
		answer = Math.max(answer, getMax(board)); // 매 단계 갱신
		
		// 종료
		if (cnt == 5) return;
		
		
		// 4방향 추가 탐색
		for(int dir=0; dir<4; dir++) {
			simulation(cnt+1, move(board, dir));
		}
	}

	private static int getMax(int[][] board2) {
		int tempMax = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tempMax = Math.max(tempMax, board2[i][j]);
			}
		}
		
		return tempMax;
	}

	private static int[][] move(int[][] board2, int dir) {
		int[][] next = new int[N][N];
		
		// 방향대로 이동하기
		if(dir == 0) {
			// 위로
			for (int c = 0; c < N; c++) {
				int[] line = new int[N];
				for(int r=0; r<N; r++) line[r] = board2[r][c];
				
				// 0으로 몰고 합치기
				int[] merged = mergeLineToFront(line);
				for(int r=0; r<N; r++) next[r][c] = merged[r];
			}
		}else if(dir == 2) {
			// 아래로 : 뒤집어서 처리하기
	        for (int c = 0; c < N; c++) {
	            int[] line = new int[N];
	            for (int r = 0; r < N; r++) line[r] = board2[N - 1 - r][c]; // 아래→위로 읽기

	            int[] merged = mergeLineToFront(line);
	            for (int r = 0; r < N; r++) next[N - 1 - r][c] = merged[r]; // 다시 아래로 채우기
	        }
		}else if(dir == 3) {
			// 왼쪽
		     for (int r = 0; r < N; r++) {
		            int[] line = new int[N];
		            for (int c = 0; c < N; c++) line[c] = board2[r][c];

		            int[] merged = mergeLineToFront(line);
		            for (int c = 0; c < N; c++) next[r][c] = merged[c];
		        }
		}else if(dir == 1) {
			// 오른쪽 : 뒤집어서 처리하기
		    for (int r = 0; r < N; r++) {
	            int[] line = new int[N];
	            for (int c = 0; c < N; c++) line[c] = board2[r][N - 1 - c]; // 오른쪽→왼쪽으로 읽기

	            int[] merged = mergeLineToFront(line);
	            for (int c = 0; c < N; c++) next[r][N - 1 - c] = merged[c]; // 다시 오른쪽으로 채우기
	        }
		}
		
		
		return next;
	}

	private static int[] mergeLineToFront(int[] line) {
		// 0 제거하기 + 앞에서부터 같으면 한 번만 합치기, 나머지 0으로 채워서 반환하기
		ArrayList<Integer> temp = new ArrayList<>();
		for(int v: line) if(v != 0) temp.add(v);
		
		// 앞에서부터 합치기
		int[] res = new int[N];
		int idx = 0;
		for(int i=0; i<temp.size(); ) {
			int cur = temp.get(i);
			if(i+1 <temp.size() && cur == temp.get(i+1)) {
				res[idx++] = cur * 2;
				i += 2;
			}else {
				res[idx++] = cur;
				i += 1;
			}
		}
		
		return res;
	}
	
	



}
