import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt(); // A의 크기
            int m = sc.nextInt(); // B의 크기

            int[] A = new int[n];
            int[] B = new int[m];

            for (int i = 0; i < n; i++) A[i] = sc.nextInt();
            for (int i = 0; i < m; i++) B[i] = sc.nextInt();

            Arrays.sort(B); // 이분 탐색을 위해 B 정렬

            int answer = 0;
            for (int a : A) {
                answer += lowerBound(B, a);
            }

            System.out.println(answer);
        }
    }

    // B에서 x보다 작은 수가 몇 개인지 반환
    static int lowerBound(int[] arr, int x) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left; // x보다 작은 원소의 개수
    }
}
