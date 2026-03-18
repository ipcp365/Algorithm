import java.util.*;
import java.io.*;

/**
 * [문제읽기]
 * - 토마토는 격자 상자 한 칸에 하나씩 넣을 수 있으며, 상자는 수직으로 쌓아 올릴 수 있다.
 * - "익지 않은 토마토(0)"는 인접한 곳에 있는 "익은 토마토(1)"의 영향을 받아 익게 된다. (위, 아래, 왼쪽, 오른쪽, 앞, 뒤 = 6방향 ** 대각선은 불가능)
 *   토마토는 혼자 익을 수 없음
 * - 며칠이 지나면 모든 토마토가 익을 수 있는지 최소 일수 구하기 (토마토가 없을 수 도 있음(01)  )
 * - 처음부터 모두 익어있으면 return 0, 모두 익지 못하면 -1 출력
 *   
 */
public class Main {
	
	static class Tomato {
    	int h;
    	int n;
    	int m;
    	
    	Tomato(int h, int n, int m){
    		this.h = h;
    		this.n = n;
    		this.m = m;
    	}
    }
	
	static int M, N, H;
	static int answer;
	static int[][][] arr;
	
	static ArrayList<Integer>[] graph;
	static boolean visited[];
	
	static Queue<Tomato> queue;

    public static void main(String[] args) throws IOException {
    	
    	// init & input
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	M = Integer.parseInt(st.nextToken()); // 상자의 가로 칸 수
    	N = Integer.parseInt(st.nextToken()); // 상자의 세로 칸 수
    	H = Integer.parseInt(st.nextToken()); // 상자의 높이
    	
    	arr = new int[H][N][M];
    	
    	// 상자의 가장 밑에부터 위에까지
    	for(int i=0; i<H; i++) {
    		for(int j=0; j<N; j++) {
        		st = new StringTokenizer(br.readLine());
    			for(int k=0; k<M; k++) {
    				arr[i][j][k] = Integer.parseInt(st.nextToken());
    			}
    		}
    	}
    	
    	// Print
    	//print_arr(arr);
    	
    	// 모든 1을 큐에 넣기
    	queue = new LinkedList<>();
    	for(int i=0; i<H; i++) {
    		for(int j=0; j<N; j++) {
    			for(int k=0; k<M; k++) {    				
    				if(arr[i][j][k] == 1) {
    					queue.offer(new Tomato(i, j, k));
    				}
    			}
    		}
    	}
    	
    	/**
    	 * -1 : 토마토 없음
    	 * 0 : 토마토 안익음
    	 * 1 : 토마토 익음
    	 */
    	// 토마토 익히기
    	bfs();
    	
    	// 토마토 찾기
    	int max = 0;
    	for(int i=0; i<H; i++) {
    		for(int j=0; j<N; j++) {
    			for(int k=0; k<M; k++) {    		
    				
    				// 검사가 끝난 이후에 익지 않은 토마토가 하나라도 있으면? 모두 익지 못하는 상황 (-1)
    				if(arr[i][j][k] == 0) {
    					System.out.println(-1);
    					return;
    				}
    				
    				// max 갱신
    				max = Math.max(max, arr[i][j][k]);

    			}
    		}
    	}

    	// Result : 날짜 값 보정 (처음부터 익은 토마토는 1 이기 때문)
    	System.out.println(max - 1);
    }
    
    // 아래, 위, (앞, 오른쪽, 뒤, 왼쪽)
    static int[] dz = {-1, 1, 0, 0, 0, 0};  	// 층
    static int[] dx = {0, 0, -1, 0, 1, 0}; 	// 행
    static int[] dy = {0, 0, 0, 1, 0, -1};	// 열
    
    private static void bfs() {

    	while(!queue.isEmpty()) {
    		
    		Tomato current = queue.poll();
    		
    		for(int i=0; i<6; i++) {
    			int nz = current.h + dz[i];
    			int nx = current.n + dx[i];
    			int ny = current.m + dy[i];
    			
    			// 범위 체크
    			if(nz < 0 || nz >= H || nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
    			
    			if(arr[nz][nx][ny] == 0) {
    				arr[nz][nx][ny] = arr[current.h][current.n][current.m] + 1;
    				queue.offer(new Tomato(nz, nx, ny));
    				
    				answer ++;
    			}
    		}
    	}
	}



	private static void print_arr(int[][][] arr2) {
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					System.out.print(arr2[i][j][k]);
				}
				System.out.println();
			}
		}
		
	}


}