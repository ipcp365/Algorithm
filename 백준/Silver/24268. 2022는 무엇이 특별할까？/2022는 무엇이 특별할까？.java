import java.util.*;

public class Main {

    static int N, d;
    static List<Integer> candidates = new ArrayList<>();
    static boolean[] used = new boolean[10];
    static int[] perm;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        d = sc.nextInt();
        perm = new int[d];
        
        dfs(0);
        
        if (candidates.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(candidates);
            System.out.println(candidates.get(0));
        }
    }

    // 순열 생성 (0~d-1)
    static void dfs(int depth) {
        if (depth == d) {
            if (perm[0] == 0) return; // 앞자리 0 금지

            // 진법 변환
            int value = 0;
            for (int i = 0; i < d; i++) {
                value = value * d + perm[i];
            }

            if (value > N) {
                candidates.add(value);
            }
            return;
        }

        for (int i = 0; i < d; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[depth] = i;
                dfs(depth + 1);
                used[i] = false;
            }
        }
    }
}
