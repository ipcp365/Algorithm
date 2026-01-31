import java.io.*;
import java.util.*;

/**
 * [문제 읽기] : 마법사 상어와 비바라기
 * - 바구니에 저장할 수 있는 물의 양에는 제한이 없다.
 * - 격자의 가장위는(1, 1), 가장 오른쪽 아래는 (N, N) 칸
 * - 마법사 상어는 연습을 위해 1번 행과 N번행을 연결했고, 1번 열과 N번 열도 연결 함 (무한 연결 상태)
 * 
 * - '비바라기' 시전 : (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다. 구름에 이동을 M번 명령
 *                  i번째 이동 명령은 방향 di과 거리 si 로 이루어짐
 *                  총 8개의 방향이 있으며 1부터 8까지 8개의 정수로 표햔 (왼쪽 방향이 1번, 좌상대각선 2번, 위쪽 3번...)
 *                  
 * - 이동을 명령하면 순서대로 진행된다.
 */
public class Main {

    static class Cloud {
        int r, c;
        Cloud(int r, int c) { this.r = r; this.c = c; }
    }

    static int N, M;
    static int[][] water;

    // 문제의 방향(1~8)에 맞춘 이동 벡터 (0-indexed 격자 기준)
    // 1: ←, 2: ↖, 3: ↑, 4: ↗, 5: →, 6: ↘, 7: ↓, 8: ↙
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    // 대각선 방향만 사용(2,4,6,8)
    static int[] diagDir = {2, 4, 6, 8};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        water = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 구름 위치(문제 조건)
        List<Cloud> clouds = new ArrayList<>();
        clouds.add(new Cloud(N - 1, 0));
        clouds.add(new Cloud(N - 1, 1));
        clouds.add(new Cloud(N - 2, 0));
        clouds.add(new Cloud(N - 2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            // 1) 구름 이동 + 2) 비 내리기(물+1)
            boolean[][] rained = new boolean[N][N]; // 이번 턴에 "비가 내린 칸" 표시
            moveAndRain(clouds, d, s, rained);

            // 4) 물복사버그
            waterCopyBug(rained);

            // 5) 새 구름 생성(이때 기존 구름은 이미 사라진 상태)
            clouds = makeNewClouds(rained);
        }

        // 최종 물의 총합
        long sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += water[i][j];
            }
        }
        System.out.println(sum);
    }

    /**
     * 모든 구름을 (d, s)만큼 이동시키고, 이동한 칸에 비를 내려 물을 +1 한다.
     * 이동은 격자 밖으로 나가면 반대편으로 돌아오는 순환 처리.
     */
    static void moveAndRain(List<Cloud> clouds, int d, int s, boolean[][] rained) {
        // s가 N보다 클 수 있으니 미리 줄여도 됨(최적화/가독성)
        int move = s % N;

        for (Cloud cloud : clouds) {
            // 이동
            int nr = cloud.r + dr[d] * move;
            int nc = cloud.c + dc[d] * move;

            // 음수 포함 모듈러 안전 처리
            nr = ((nr % N) + N) % N;
            nc = ((nc % N) + N) % N;

            // 이동 결과를 cloud에 반영
            cloud.r = nr;
            cloud.c = nc;

            // 비 내리기: 구름이 있는 칸 물 +1
            water[nr][nc] += 1;
            rained[nr][nc] = true;
        }
    }

    /**
     * 물복사버그:
     * "이번 턴에 비가 내린 칸" 각각에 대해,
     * 대각선 4방향(↖↗↘↙)으로 인접한 칸 중 물이 1 이상인 칸의 개수만큼 물을 증가시킨다.
     */
    static void waterCopyBug(boolean[][] rained) {
        // 동시에 증가하는 것처럼 보이지만,
        // 각 칸에서 "대각선 이웃의 물이 1 이상인지"만 체크하므로
        // 순회하며 바로 water[r][c]에 더해도 문제 없음.
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!rained[r][c]) continue; // 비 내린 칸만 대상

                int cnt = 0;
                for (int dir : diagDir) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    // 격자 밖은 무시
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    if (water[nr][nc] > 0) cnt++;
                }

                water[r][c] += cnt;
            }
        }
    }

    /**
     * 새 구름 생성:
     * - 이번 턴에 비가 내린 칸(rained == true)은 구름 생성 금지
     * - 나머지 칸 중 물의 양이 2 이상이면 구름 생성, 물 2 감소
     */
    static List<Cloud> makeNewClouds(boolean[][] rained) {
        List<Cloud> newClouds = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (rained[r][c]) continue;       // 방금 비 내린 칸 제외
                if (water[r][c] >= 2) {
                    water[r][c] -= 2;
                    newClouds.add(new Cloud(r, c));
                }
            }
        }
        return newClouds;
    }
}