import java.util.*;
import java.io.*;

/**
 * [문제읽기]
 * - 사과를 먹으면 뱀의 길이가 늘어난다.
 * - 벽 또는 자기 자신의 몸과 부딪히면 게임이 끝난다. (보드의 상하좌우 끝은 벽)
 * 
 * - 시작은 맨좌측 + 뱀의 길이는 1 + 처음에 오른쪽을 향함
 * 
 * - 뱀은 초마다 이동
 *   1. 몸길이를 늘려 머리를 다음칸에 위치
 *   2. 벽이나 자기자신의 몸과 부딪히면 게임 끝
 *   3. 이동한 칸에 사과가 있으면 사과는 없어지고 꼬리는 움직이지 않는다. (즉 추가로 길어짐)
 *   4. 이동한 칸에 사과가 없으면 몸길이를 줄여서 꼬리가 위치한 칸을 미워줌
 *   
 * - 사과의 위치와 뱀의 이동경로가 주어질 때, 이 게임이 몇 초에 끝나는지 계산해라.
 *   
 */
public class Main {
	
	static int N, K, L;
	static int answerTime;
	static boolean[][] apple;
	
	static int[] turnTime;
	static char[] turnDir;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
    	
    	// init & input
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	K = Integer.parseInt(br.readLine());
    	apple = new boolean[N][N];
    	
    	for(int i=0; i<K; i++) {
    		st = new StringTokenizer(br.readLine());
    		int row = Integer.parseInt(st.nextToken());
    		int col = Integer.parseInt(st.nextToken());
    		
    		// 사과 좌표는 1부터 시작하므로 값 보정 필요 ( -1 )
    		apple[row-1][col-1] = true;
    	}
    	
    	// 뱀의 방향 변환 횟수
    	L = Integer.parseInt(br.readLine());
    	turnTime = new int[L];
    	turnDir = new char[L];
    	for(int i=0; i<L; i++) {
    		st = new StringTokenizer(br.readLine());
    		turnTime[i] = Integer.parseInt(st.nextToken());
    		turnDir[i] = st.nextToken().charAt(0);
    	}
    	
    	// Simulation
    	// 방향 벡터 (0=오,1=하,2=좌,3=상)
    	int[] dx = {0, 1, 0, -1};
    	int[] dy = {1, 0, -1, 0};
    	int dir = 0; // 처음은 오른쪽
    	
    	// 뱀의 위치좌표 정보
    	Deque<int[]> snake = new ArrayDeque<>();
    	snake.addFirst(new int[]{0, 0});
    	
    	// 뱀이 위치한곳 빠르게 확인하기 위한 용도
    	boolean[][] snakeBody = new boolean[N][N];
    	snakeBody[0][0] = true; // 시작 위치 (0,0)
    	
    	//Queue<Integer> queue = new LinkedList<Integer>;
    	
    	answerTime = 0;
    	int idx = 0; // 방향전환 이벤트 인덱스
    	while(true) {
    		// 1. 시간 경과(1초 증가)
    		answerTime++;
    		
    		
    		// 2. 현재 바라보고 있는 방향으로 이동
    		int[] head = snake.peekFirst();
    		int nx = head[0] + dx[dir];
    		int ny = head[1] + dy[dir];
    		
    		// 3. 게임 종료 조건 검사 : 벽에 충돌
    		if(nx < 0 || ny < 0 || nx >= N || ny >= N || snakeBody[nx][ny]) break;
    		
//    		int[] tail = snake.peekLast();
//    		boolean isTail = (nx == tail[0] && ny == tail[1]);
//    		if (snakeBody[nx][ny] && !(isTail && !apple[nx][ny])) break;
    		
    		
    		// 4. 충돌하지 않았다면, 앞으로 나아갈 수 있으므로 갱신 (앞으로 이동한 머리만 추가)
    		snake.addFirst(new int[] {nx, ny});
    		snakeBody[nx][ny] = true;
    		
    		// 4. 사과가 있는 경우/없는 경우
    		if(apple[nx][ny]) {
    			// 사과만 제거한다. 뱀의 길이가 늘어났기 떄문에 꼬리는 유지 한다!
    			// 위에서 머리는 addFirst 로 추가해줬기 때문
    			apple[nx][ny] = false;
    		}else {
    			int[] tail = snake.removeLast();
    			snakeBody[tail[0]][tail[1]] = false;
    		}
    		
    		
    		// 4. 회전 이벤트가 있는 경우 조건이 맞을 때 사용 한다.
    		if(idx < L && answerTime == turnTime[idx]) {
    			// 1-1. 회전 이벤트 정의
    			if(turnDir[idx] == 'D') {
    				dir = (dir+1) % 4;
    			}else {
    				dir = (dir+3) % 4;
    			}
    			
    			
    			// 1-2. 다음 이벤트로 넘너가야 하므로 idx 값 증가
    			idx++;
    		}
    	}
    	
    	
    	// Result
    	System.out.println(answerTime);
    }


}