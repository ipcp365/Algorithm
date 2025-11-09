import java.io.*;
import java.util.*;

import javax.security.auth.callback.CallbackHandler;


/**
 * 크기 N(3, 9, 27.. 3의 거듭제곱) 의 정사각형을 별로 채운다
 * 가운데 정사각형은 비워둔다. 비워든 칸 중 나머지 칸은 다시 같은 규칙으로 별을 그린다.
 * 
 */
public class Main {

    static char[][] map; // 별을 저장할 2차원 배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 입력값 N (3의 거듭제곱)

        map = new char[N][N]; // NxN 배열 생성

        // 초기화: 전체를 공백으로 채움
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = ' ';
            }
        }

        // 재귀 시작 (0,0) 위치부터 N 크기 패턴을 그림
        drawStar(0, 0, N);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map[i]).append('\n');
        }
        System.out.print(sb);
    }

    // 재귀 함수: (x, y)부터 n크기의 패턴을 그림
    static void drawStar(int x, int y, int n) {

        // base case: 크기가 1이면 별 하나 찍기
        if (n == 1) {
            map[x][y] = '*';
            return;
        }

        // 블록 하나의 크기 (현재 크기 n을 3으로 나눈 값)
        int size = n / 3;
        int count = 0; // 9칸 중 몇 번째 칸인지 체크

        // 3x3 블록을 순서대로 탐색
        for (int i = x; i < x + n; i += size) {
            for (int j = y; j < y + n; j += size) {
                count++;

                // 가운데(5번째 블록)는 비움 (공백 유지)
                if (count == 5) continue;

                // 나머지 8칸은 다시 같은 규칙 적용 (재귀 호출)
                drawStar(i, j, size);
            }
        }
    }
}