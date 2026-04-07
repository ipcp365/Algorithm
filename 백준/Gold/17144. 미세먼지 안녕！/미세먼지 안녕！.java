import java.util.*;
import java.io.*;



public class Main {

	static int R, C, T;
	static int answer = 0;
	
	static int[][] map;
	
	// 공기청정기 위치 정보
	static int top = -1;
	static int bottom = -1;
	
	
    public static void main(String[] args) throws IOException { 
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	T = Integer.parseInt(st.nextToken());
    	
    	map = new int[R][C];
    	
    	for(int i=0; i<R; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<C; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
 
    			if(map[i][j] == -1) {
    				if(top == -1) {
    					top = i;
    				}else {
    					bottom = i;
    				}
    			}
    		}
    	}
    	
    	
    	// Simulation
    	for(int t = 0; t<T; t++) {
        	// 1. 먼지 확산
        	expandedDust();
        	
        	// 2. 공기청정
        	cleanDust();
    	}
    	

    	// Result
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			
    			if(map[i][j] != -1) answer += map[i][j];
    		}
    	}
    	System.out.println(answer);
    }
    
    
    static int[] dxs = {-1, 0, 1, 0};
    static int[] dys = {0, 1, 0, -1};
    
    private static void expandedDust() {
    	// 미세먼지 확산은 동시에 일어나야 한다.
    	// 참조는 원본 grid 기준으로 하되, 갱신정보를 copy 본에 담고, 마지막에 바꿔줘야함.
//    	int[][] copyMap = copyMap();
    	int[][] copyMap = new int[R][C];
    	
    	// 1. 먼지가 있는 곳을 찾는다.
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			
    			// 2. 미세먼지 발견 !
    			if(map[i][j] != -1 && map[i][j] != 0) {
    				int dust = map[i][j] / 5;
    				int cnt = 0;
    				
    				// 3. 확산 시키기
    				for(int dir=0; dir<4; dir++) {
    					int nx = i + dxs[dir];
    					int ny = j + dys[dir];
    					
    					// 3-1. 무시 조건 : 범위 밖 & 빈 공간이 아닌 경우
    					if(nx <0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == -1) continue;
    					
    					// 3-2. 확산
    					copyMap[nx][ny] += dust;
    					cnt++;
    				}
    				
    				// 4. 먼지 값 갱신
    				copyMap[i][j] += map[i][j] - (dust * cnt);
    			}
    		}
    	}
    	
    	
    	// 4. grid 정보갱신
    	copyMap[top][0] = -1;
    	copyMap[bottom][0] = -1;
    	map = copyMap;
    	
    }
    

	private static void cleanDust() {
    	// 1. 위쪽 공기 청정기
		// 아래 - 위
		for(int i=top; i>0; i--) {
			map[i][0] = map[i-1][0];
		}
		
		// 왼-오
		for(int i=0; i<C - 1; i++) {
			map[0][i] = map[0][i+1];
		}
		
		// 위- 아래
		for(int i=0; i<top; i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		
		// 오
		for(int i=C-1; i>0; i--) {
			map[top][i] = map[top][i-1];
		}
		map[top][1] = 0;

    	
    	// 2. 아래쪽 공기청정기
		// 아래
		for(int i=bottom; i<R-1; i++) {
			map[i][0] = map[i+1][0];
		}
		
		// 오른쪽으로
		for(int i=0; i<C-1; i++) {
			map[R-1][i] = map[R-1][i+1];
		}
		
		// 위로
		for(int i=R-1; i>bottom; i--) {
			map[i][C-1] = map[i-1][C-1]; 
		}
		
		// 왼쪽으로
		for(int i=C-1; i>0; i--) {
			map[bottom][i] = map[bottom][i-1];
		}
		map[bottom][1] = 0;
    	
    	
    	// 공기청정기 위치 초기화
    	map[top][0] = -1;
    	map[bottom][0] = -1;
    }




} 