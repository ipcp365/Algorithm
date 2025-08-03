import java.io.*;
import java.util.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());
		long[] nums = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(st.nextToken());
		}

		// 정렬
		Arrays.sort(nums);

		int left = 0;
		int right = N - 1;
		long minDiff = Long.MAX_VALUE;
		long ans1 = 0;
		long ans2 = 0;

		while (left < right) {
			long sum = nums[left] + nums[right];
			if (Math.abs(sum) < minDiff) {
				minDiff = Math.abs(sum);
				ans1 = nums[left];
				ans2 = nums[right];
			}

			if (sum < 0) {
				left++;
			} else {
				right--;
			}
		}

		System.out.println(ans1 + " " + ans2);
	}
}