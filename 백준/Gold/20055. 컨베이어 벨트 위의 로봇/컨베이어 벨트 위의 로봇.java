import java.io.*;
import java.util.*;

/**
 * 문제 읽기
 * 
 * - 총 길이가 2N인 컨베이어 벨트(위아래로 감싸며 돌고 있음), 한쪽면의 길이는 N
 * - 벨트 길이 1 간격으로 총 2N 칸 (번호부여)
 * - 벨트가 회전하면 자리가 하나씩 밀려 2N번 벨트는 1번 벨트 자리로 이동하는 구조
 * - 각 칸에는 Ai 내구도가 있음
 * - 로봇은 올리는 위치에만 박스를 올릴 수 있음. 언제든지 로봇이 내리는 위치에 도달하면 즉시 내림. 로봇은 컨베이어 벨트 위에서 스스로 이동할 수 있음.
 *   로봇을 올리거나, 로봇이 이동하면 그 칸의 내구도는 1만큼 감소
 * 
 * - 컨베이어 벨트를 이용해 로봇들을 건너편으로 옮기려고 한다.
 *   1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
 *   2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동. 이동 못하면 가만히
 *   2-1. 로봇이 이동하기 위해선 비어있는 칸(로봇 없어야 함) & 내구도가 1 이상 남아 있어야 함
 *   3. 올리는 위치 칸의 내구도가 0이 아닐 경우에만 로봇을 올릴 수 있음
 *   4. 내구도가 0인 칸의 개수가 k 개 이상이면 종료. 아니면 1번으로 (반복)
 * 
 * ** 종료되었을 때 몇 번재 단계가 진행중이었나? 가장 처음 수행되는 단계는 1번째 단계이다.
 */

public class Main {
	
    static int N, K;
    static int[] powers;
    static boolean[] robots;
    static int shutdown, answer;
    static int idxUp, idxDown;

    static int dec(int i) { // 원형에서 1칸 뒤로(인덱스 감소)
        int M = 2 * N;
        return (i - 1 + M) % M;
    }

    static int inc(int i) { // 원형에서 1칸 앞으로(인덱스 증가)
        int M = 2 * N;
        return (i + 1) % M;
    }

    public static void main(String[] args) throws IOException {
        // init & input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        powers = new int[2 * N];
        robots = new boolean[2 * N];
        shutdown = 0;
        answer = 0;
        idxUp = 0;
        idxDown = N - 1;  // ★ 내리는 위치는 N-1 (0-based)

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            powers[i] = Integer.parseInt(st.nextToken());
            if (powers[i] == 0) shutdown++;
        }

        // Simulation
        while (true) {
            answer++;
            
            // 1. 회전
            moveBelt();
            
            // 2. 로봇이동
            moveRobot();
            
            // 3. 로봇 올리기
            loadRobot();

            // 4. 내구도가 0인 칸이 K개 이상일 경우 중단
            if (shutdown >= K) break;
        }

        // Result
        System.out.println(answer);
    }

    // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
    private static void moveBelt() {
        idxUp = dec(idxUp);
        idxDown = dec(idxDown);

        // 회전 후 내리는 위치에 로봇이 있으면 즉시 내림
        if (robots[idxDown]) robots[idxDown] = false;
    }

    // 2. 가장 먼저 벨트에 올라간 로봇부터 한 칸 이동 (내리는 위치에 가까운 순서)
    private static void moveRobot() {
        // i를 내리는 위치 직전부터 올리는 위치 직전까지 역방향으로 순회
        int i = dec(idxDown);
        while (true) {
            if (robots[i]) {
                int j = inc(i); // 다음 칸
                if (!robots[j] && powers[j] > 0) {
                    // 이동
                    robots[i] = false;

                    // 다음 칸이 내리는 위치라면 올리지 않고 즉시 내림
                    if (j == idxDown) {
                        // do not set robots[j]
                    } else {
                        robots[j] = true;
                    }

                    // 내구도 감소
                    powers[j]--;
                    if (powers[j] == 0) shutdown++;
                }
            }
            if (i == idxUp) break; // 올리는 위치까지 검사했으면 종료
            i = dec(i);
        }

        // 이동 후에도 내리는 위치에 로봇이 있으면 내리기
        if (robots[idxDown]) robots[idxDown] = false;
    }

    // 3. 올리는 위치에 로봇을 올린다 (내구도 0이 아니고, 로봇이 비어있을 때)
    private static void loadRobot() {
        if (!robots[idxUp] && powers[idxUp] > 0) {
            robots[idxUp] = true;
            powers[idxUp]--;
            if (powers[idxUp] == 0) shutdown++;
        }
    }
}