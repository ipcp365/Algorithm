import java.io.*;
import java.util.*;


class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<Long, Integer> map = new HashMap<>();
		
		// 값이 있을 경우 +1 증가, 없을 경우 기본값 0 셋팅
		for (int i = 0; i < N; i++) {
			long num = Long.parseLong(br.readLine());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		// 최대 찾기
		long answer = 0;
		int maxCount = 0;
		for (Map.Entry<Long, Integer> entry : map.entrySet()) {
			long key = entry.getKey();
			int count = entry.getValue();
			
			if (count > maxCount) {
				maxCount = count;
				answer = key;
			} else if (count == maxCount && key < answer) {
				answer = key;
			}
		}
		
		System.out.println(answer);

	}
}