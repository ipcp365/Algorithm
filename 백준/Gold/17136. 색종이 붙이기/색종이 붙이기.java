import java.util.*;
import java.io.*;

/**
 * [문제읽기]
 * - 정사각형 모양을 한 색종이 다섯 종류 : 1*1, 2*2, 3*3, 4*4, 5*5
 * - 가로 세로 사이즈 10인 격자 위에 종이를 붙이려고 한다.
 *   0(색종이 부착 불가), 1(색종이를 붙여야 하는 영역)
 *   
 * [요구사항]
 * - 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수를 구하여라
 * - 불가능하면 -1
 */



public class Main {
	
	static class Node{
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	
	static int N = 10;
	static int[][] map = new int[N][N];
	static boolean[][] visited = new boolean[N][N];
	static int[] paperCount = {0, 5, 5, 5, 5, 5};
	
	static List<Node> papers = new ArrayList<>();
	
	static int answer = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException { 
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	for(int i=0; i<N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		
    		for(int j=0; j<N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			
    			if(map[i][j] == 1) {
    				papers.add(new Node(i, j));
    			}
    		}
    	}
    	

    	// Simulation
    	dfs(0, 0, 0);
    	
    	
    	// Result
    	System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }


	private static void dfs(int r, int c, int count) {
		// 끝까지 도달 ? 갱신 !
		if(r >= 9 && c>9) {
			answer = Math.min(answer, count);
			return;
		}
		
		// 초과한 경우 더이상 볼 필요 없음
		if(count >= answer) return;
		
		// 다음 줄(세로) 이동 하기
		if(c > 9) {
			dfs(r+1, 0, count);
			return;
		}
		
		// 색종이 붙여보기
		if(map[r][c] == 1) {
			
			// 큰 색종이 부터 붙여보기
			for (int size = 5; size >= 1; size--) {
                if (paperCount[size] > 0 && canAttach(r, c, size)) {
                    attach(r, c, size, 0); // 종이 붙이기 (0으로 만들기)
                    paperCount[size]--;
                    
                    dfs(r, c + 1, count + 1);
                    
                    attach(r, c, size, 1); // 원상 복구 (Backtracking)
                    paperCount[size]++;
                }
            }
		}else {
			dfs(r, c+1, count);
		}
		
	}
	
	// 붙일 수 있는지 체크
    private static boolean canAttach(int r, int c, int size) {
    	// 가지치기 : 앞으로 붙일 색종이 영역이 범위를 넘어서는 경우 사전 차단 !
        if (r + size > 10 || c + size > 10) return false;
        
        // 색종이 붙이기 (단, 도중에 0을 만날 경우에만 실패 처리)
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }
	
	// 종이를 붙이거나(state=0) 떼는(state=1) 함수
    private static void attach(int r, int c, int size, int state) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                map[i][j] = state;
            }
        }
    }



} 