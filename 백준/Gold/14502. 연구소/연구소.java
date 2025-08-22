import java.io.*;
import java.util.*;


/**
 * 문제 읽기
 * - 바이러스 확산을 위해 연구소에 벽을 세우는 문제
 * - 연구소 크기는 N*M, 각 직사각형은 1*1 사이즈, 빈칸 or 벽으로 구성(벽은 칸 하나를 가득 차지)
 * - !! 일부칸에 바이러스가 있으며, 상하좌우 - 인접한 빈칸으로 이동 가능
 * - 3개의 벽을 세워야 함
 * 
 * 
 * - 안전영역 크기의 최대값을 구하여라 (집합 x, 0의 개수)
 * - 빈칸(0), 벽(1), 바이러스(2)
 * 
 */
public class Main {
	
	static class Node {
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
    static int N, M, answer, totalEmpty;
    static int[][] grid;
    static List<Node> empties = new ArrayList<>();   // 0 위치 목록
    static List<Node> viruses = new ArrayList<>();   // 2 위치 목록
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
    	
    	// init & input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        answer = 0;
        totalEmpty = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                
                // 빈 공간 or 바이러스 위치를 각각의 리스트에 추가
                if (grid[i][j] == 0) {
                    empties.add(new Node(i, j));
                    totalEmpty++;
                } else if (grid[i][j] == 2) {
                    viruses.add(new Node(i, j));
                }
            }
        }

        /**
         * Simulation
         * 
         * - 완전탐색 : 빈 칸 3곳을 골라 가벽을 세운다. (i < j < k 조합)
         *			  가벽을 세운 후, 원상복구 하는 과정을 반복하며 가능한 모든 경우를 탐색한다.
         *
         * - 가벽을 세우고, 감염 시뮬레이션을 진행 후 다시 안전한 영역을 count 하는게 아니라 감염된 영역을 기록하고
         *   기존에 기록해뒀던 안전구역(totalEmpty) 에서 제거하는 방식으로 최적화를 진행 한다. (매번 2중 for문으로 완전탐색을 할 경우 비효율)
         */
        // 완전탐색 : 빈 칸 중 3곳을 골라 가벽 세우기 (i < j < k 조합)
        // 가벽을 세우고, 원상복구 하는 과정을 반복하며 가능한 모든 경우를 탐색
        for (int a = 0; a < empties.size(); a++) {
            for (int b = a + 1; b < empties.size(); b++) {
                for (int c = b + 1; c < empties.size(); c++) {
                	// 가벽 3곳의 위치 선정
                    Node A = empties.get(a);
                    Node B = empties.get(b);
                    Node C = empties.get(c);

                    // 가벽 세우기 (원본 격자에 잠깐 1로 표시)
                    grid[A.x][A.y] = 1;
                    grid[B.x][B.y] = 1;
                    grid[C.x][C.y] = 1;

                    // 시뮬레이션
                    int infected = spread();

                    // 안전구역 = 전체 빈칸 - 3(세운벽) - 감염된 칸
                    int safe = totalEmpty - 3 - infected;
                    if (safe > answer) answer = safe;

                    // 가벽 원복
                    grid[A.x][A.y] = 0;
                    grid[B.x][B.y] = 0;
                    grid[C.x][C.y] = 0;
                }
            }
        }

        System.out.println(answer);
    }

    // 바이러스 다중 시작 BFS → 감염된 빈칸 수를 반환
    static int spread() {
        boolean[][] visited = new boolean[N][M];
        ArrayDeque<Node> q = new ArrayDeque<>();

        // 시작: 바이러스 위치들
        for (Node v : viruses) {
            q.add(v);
            visited[v.x][v.y] = true;
        }

        int infected = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 바이러스 확장 불가 조건
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;

                // 빈 칸(0)만 감염 가능
                if (grid[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    infected++;              // 빈칸 하나 감염됨
                    q.add(new Node(nx, ny));
                }
            }
        }
        
        // 최종적으로 감염된 지역 수 반환
        return infected;
    }

}
