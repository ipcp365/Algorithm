import java.util.*;
import java.io.*;

/**
 * [문제읽기]
 * - 격자 크기는 R*C , 미세먼지 제거를 위해 공기청정기를 설치하는 문제
 * - 공기청정기는 항상 1번 열에 설치되어 있고, 두 행을 차지함
 *   설치되지 않은 칸에는 미세먼지가 있고, (r,c)에 있는 미세먼지 양은 Ar,c
 * - T초가 지난 후 방에 남아있는 미세먼지의 양을 구하여라
 * 
 * - 1초 동안 다음과 같이 순서대로 발생
 *   1. 미세먼지 확산 단계 (모든 칸에서 동시에 일어남)
 *      - (r,c) 에 있는 미세먼지가 인접한 네 방향으로 확산
 *      - 인접 방향에 공청기가 있거나 칸이 없으면 확산하지 않음
 *      - 확산되는 양은 (Ar,c / 5) 이고, 소수점은 버린다.
 *      - (r, c)에 남은 미세먼지의 양은 Ar,c - |Ar,c / 5| x (확산된 방향의 개수) 이다.
 *   
 *   2. 공기청정기 동작 단계
 *      - 바람이 나온다 !
 *      - 위쪽 공기청정기의 바람은 반시계방향으로 순환, 아래쪽 공기청정기 방향은 시계방향으로 순환
 *      - 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동
 *      - 공기청정기에서 부는 바람은 미세먼지가 없는 바람 + 공기청정기로 들어간 미세먼지는 모두 정화됨
 */



public class Main {
	
	static int answer = 0;
	static int R, C, T;
	static int[][] map;
	
	// 공기청정기 위치는 0열에 고정되어있으므로 행 값만 있으면 된다.
	static int top = -1;
	static int bottom = -1;
	
	static class Dust {
		int r, c, value;
		
		Dust(int r, int c, int value){
			this.r = r;
			this.c = c;
			this.value = value;
		}
	}
	

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
    	            if(top == -1) top = i;
    	            else bottom = i;
    	        }
    		}
    	}
    	
    	// Simulation
    	for(int i=0; i<T; i++) {
    		// 미세먼지 동시 확산
    		spread();
    		
    		// 공기청정기 작동
    		cleanAir();
    	}
    	
    	
    	// 남은 미세먼지 계산하기
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			
    			if(map[i][j] != -1 && map[i][j] != 0) answer += map[i][j];
    		}
    	}
    	
    	// Result
    	System.out.println(answer);
    }


    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

	private static void spread() {
		
		int[][] temp = new int[R][C];
		
		// 공기청정기 위치 설정
		temp[top][0] = -1;
		temp[bottom][0] = -1;
		
		// 미세먼지 확산
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				
				// 원본 map 에서 미세먼지가 있는 칸을 발견할 경우
				if(map[i][j] > 0) {
					
					int dust = map[i][j];
					int amount = dust / 5;
					int count = 0;
					
					for(int d=0; d<4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						
						// 탐색 불가
						if(nr<0 || nc <0 || nr >= R || nc >= C || map[nr][nc] == -1) continue;
						
						// 확산 가능
						temp[nr][nc] += amount;
						count++;
					}
					
					// 남은 미세먼지양 갱신 : 확산이 끝난 후, 해당 위치 미세먼지 양도 바뀌어야 함
					temp[i][j] += dust - (amount * count);
					
				}
				
			}
		}
		
		// map 구조 변경
		map = temp;
	}
	
	

	// 미세먼지 이동은 한칸씩이니까 역으로 계산하는게 편함 !
	private static void cleanAir() {
	    // 위쪽 공기청정기 (반시계)
		
	    // 아래 -> 위
	    for (int i = top - 1; i > 0; i--) {
	    	map[i][0] = map[i - 1][0];
	    }
	    
	    // 왼 -> 오
	    for (int j = 0; j < C - 1; j++) {
	    	map[0][j] = map[0][j + 1];
	    }
	    
	    // 위 -> 아래
	    for (int i = 0; i < top; i++) {
	    	map[i][C - 1] = map[i + 1][C - 1];
	    }
	    
	    // 오 -> 왼
	    for (int j = C - 1; j > 1; j--) {
	        map[top][j] = map[top][j - 1];
	    }
	    map[top][1] = 0;


	    // 아래쪽 공기청정기 (시계)
	    // 위 -> 아래
	    for (int i = bottom + 1; i < R - 1; i++) {
	        map[i][0] = map[i + 1][0];
	    }

	    // 왼 -> 오
	    for (int j = 0; j < C - 1; j++) {
	        map[R - 1][j] = map[R - 1][j + 1];
	    }

	    // 아래 -> 위
	    for (int i = R - 1; i > bottom; i--) {
	        map[i][C - 1] = map[i - 1][C - 1];
	    }

	    // 오 -> 왼
	    for (int j = C - 1; j > 1; j--) {
	        map[bottom][j] = map[bottom][j - 1];
	    }
	    map[bottom][1] = 0;

	    // 공기청정기 위치 유지
	    map[top][0] = -1;
	    map[bottom][0] = -1;
	}


    
} 