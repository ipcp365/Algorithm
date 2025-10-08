import java.io.*;
import java.util.*;
/**
 * [문제 읽기]
 * - 점 네 개의 좌표를 담은 이차원 배열 dots[][] 가 주어질 때
 *   [[x1, y1], [x2, y2], ...]
 * 
 * - 네 개의 점을 두 개씩 이었을 때, 두 직선이 평행이 되는 경우가 있으면 1, 없으면 0 return
 * 
 * 
 * - 가능한 조합은 총 3가지 : ab-cd, ac-bd, ad-bc
 *   가능한 평행은 총 4가지 경우 : 수직, 수평, 대각선상(2)
 *   '평행' 에 해당하는 규칙을 찾아 적용하는게 관건
 */

class Solution {
    public int solution(int[][] dots) {
        
        int[][] pairs = {
            {0, 1, 2, 3},
            {0, 2, 1, 3},
            {0, 3, 1, 2}
        };
        
       for (int[] p : pairs) {
            if (isParallel(dots[p[0]], dots[p[1]], dots[p[2]], dots[p[3]])) {
                return 1; // 하나라도 평행이면 1
            }
        }
        
        return 0; // 전부 평행 아님
    }
    
    private boolean isParallel(int[] a, int[] b, int[] c, int[] d) {
        long dx1 = (long)b[0] - a[0];
        long dy1 = (long)b[1] - a[1];
        long dx2 = (long)d[0] - c[0];
        long dy2 = (long)d[1] - c[1];

        // 외적 z성분 == 0 이면 평행
        return dx1 * dy2 - dy1 * dx2 == 0;
    }
}