import java.io.*;
import java.util.*;


/**
 * 
 * 문제 읽기
 * 
 * - N이 주어졌을 때, 1 부터 N 까지의 수로 이루어진 순열을 사전순으로 출력
 * 
 */
public class Main {

	static int N;
	static int[] arr;
	static boolean[] visited;

    public static void main(String[] args) throws Exception {
    	
    	// init & input
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	arr = new int[N];
    	visited = new boolean[N];

    	// Simulation
    	dfs(0);
    }
    
    static void dfs(int idx){
    	
    	// 중단 조건 : 순열 조합 출력
        if (idx==N){ 
        	for(int i: arr) {
        		System.out.print(i + " ");
        	}
        	System.out.println();
        	return;
        }

        // 탐색 진행
        for (int i=0;i<N;i++){
        	if(!visited[i]) {
        		
        		// 방문처리 후, 배열 채우기 (0부터 시작하므로 +1 추가진행)
        		visited[i] = true;
        		arr[idx] = i + 1;
        		
        		// 재귀 호출
        		dfs(idx + 1);
        		
        		// 원복 : 방문 취소 (배열의 숫자는 덮어씌워지기 때문에 값을 바꿀 필요는 없다)
        		visited[i] = false;
        	}
        }
    }


}
