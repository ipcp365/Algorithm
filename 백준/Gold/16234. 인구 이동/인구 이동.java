import java.io.*;
import java.util.*;

/*
  백준 16234 인구 이동
  핵심
  - 하루 단위로 전체 칸을 돌며 연합을 BFS로 찾는다
  - 연합 크기가 2 이상이면 평균 인구로 갱신한다
  - 하루 동안 한 번이라도 갱신이 발생하면 다음 날도 진행한다
*/
public class Main {

    static int N, L, R;
    static int[][] A;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Pos {
        int r, c;
        Pos(int r, int c) { this.r = r; this.c = c; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];

        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;

        // 하루 단위 시뮬레이션 반복
        while (true) {
            visited = new boolean[N][N];
            boolean moved = false;

            // 전체 칸을 돌면서 아직 방문하지 않은 칸에서 연합 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        // 연합을 찾고, 인구 이동이 실제로 발생했는지 확인
                        if (bfsAndMove(i, j)) {
                            moved = true;
                        }
                    }
                }
            }

            // 하루 동안 이동이 없으면 종료
            if (!moved) break;

            days++;
        }

        System.out.println(days);
    }

    /*
      (sr, sc)에서 시작해서 BFS로 연합을 구성
      - 연합 칸 목록과 인구 합을 구한다
      - 연합 크기가 2 이상이면 평균으로 갱신하고 true 반환
      - 연합 크기가 1이면 갱신 없으므로 false 반환
    */
    static boolean bfsAndMove(int sr, int sc) {
        ArrayDeque<Pos> q = new ArrayDeque<>();
        ArrayList<Pos> union = new ArrayList<>();

        visited[sr][sc] = true;
        q.add(new Pos(sr, sc));
        union.add(new Pos(sr, sc));

        int sum = A[sr][sc];

        // BFS로 연합 확장
        while (!q.isEmpty()) {
            Pos cur = q.poll();

            // 4방향 탐색
            for (int k = 0; k < 4; k++) {
                int nr = cur.r + dr[k];
                int nc = cur.c + dc[k];

                if (!inRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;

                int diff = Math.abs(A[cur.r][cur.c] - A[nr][nc]);

                // L 이상 R 이하이면 국경이 열려 같은 연합
                if (diff >= L && diff <= R) {
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                    union.add(new Pos(nr, nc));
                    sum += A[nr][nc];
                }
            }
        }

        // 연합이 1칸이면 이동 없음
        if (union.size() == 1) return false;

        // 평균 인구로 갱신
        int avg = sum / union.size();
        for (int i = 0; i < union.size(); i++) {
            Pos p = union.get(i);
            A[p.r][p.c] = avg;
        }

        return true;
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
