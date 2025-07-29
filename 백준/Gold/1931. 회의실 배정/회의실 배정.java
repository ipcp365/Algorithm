import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
    	
    	int n = Integer.parseInt(br.readLine());
    	int[][] grid = new int[n][2];
    	
    	for(int i=0; i<n; i++) {
    		String[] value = br.readLine().split(" ");
    		grid[i][0] = Integer.parseInt(value[0]);
    		grid[i][1] = Integer.parseInt(value[1]);
    	}
    	
    	// 종료시간 기준으로 정렬 진행: 동일한 값이 있을 경우 시작시간을 기준으로 추가 조건
    	// 회의시간이 짧은 시간, 혹은 시작 시간이 작은 순서로 할 경우 최적의 답을 구할 수 없음
    	Arrays.sort(grid, (a, b) -> {
    		if(a[1] == b[1]) {
    			return a[0] - b[0];
    		}
    		return a[1] - b[1];
    	});
    	
    	
    	int cnt = 0;
    	int lastEndTime = 0;
    	
    	// 회의 선택
    	for(int i=0; i<n; i++) {
    		if(grid[i][0] >= lastEndTime) {
    			// 현재 회의 종료시간으로 업데이트
    			lastEndTime = grid[i][1];
    			cnt++;
    		}
    	}
    	
    	System.out.println(cnt);
    	

    }
}