import java.util.*;

public class Main {
    static int[] arr;
    static List<List<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        int size = (int) Math.pow(2, K) - 1;

        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < K; i++) {
            tree.add(new ArrayList<>());
        }

        buildTree(0, size - 1, 0);

        for (List<Integer> level : tree) {
            for (int val : level) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    static void buildTree(int start, int end, int depth) {
        if (start > end) return;

        int mid = (start + end) / 2;
        tree.get(depth).add(arr[mid]);

        buildTree(start, mid - 1, depth + 1); // 왼쪽 서브트리
        buildTree(mid + 1, end, depth + 1);   // 오른쪽 서브트리
    }
}
