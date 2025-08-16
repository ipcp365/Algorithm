import java.io.*;
import java.util.*;

public class Main {

    static final long INF = Long.MAX_VALUE / 4;
    static int W, H;
    static long[][] cost;     // 각 칸 진입 비용
    static long[][] dist;     // 최단 누적 비용
    static int sr, sc;        // 시작(E)

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        int r, c;
        long w;
        
        Node(int r, int c, long w) { 
        	this.r = r; 
        	this.c = c; 
        	this.w = w; 
        }
        
        public int compareTo(Node o){ 
        	return Long.compare(this.w, o.w); 
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            // 전투선 클래스 → 비용
            Map<Character, Long> costMap = new HashMap<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char ch = st.nextToken().charAt(0);
                long w = Long.parseLong(st.nextToken());
                costMap.put(ch, w);
            }
            costMap.put('E', 0L); // 시작 지점 비용 0

            cost = new long[H][W];
            dist = new long[H][W];
            for (int r = 0; r < H; r++) Arrays.fill(dist[r], INF);

            // 지도 입력
            for (int r = 0; r < H; r++) {
                String line = br.readLine();
                for (int c = 0; c < W; c++) {
                    char ch = line.charAt(c);
                    if (ch == 'E') { sr = r; sc = c; }
                    cost[r][c] = costMap.get(ch); // 클래스 문자에 대응하는 비용
                }
            }

            out.append(dijkstra()).append('\n');
        }
        
        // Result
        System.out.print(out);
    }

    // PQ에서 꺼낸 칸이 가장자리면 그 누적비용이 최소 → 즉시 반환
    static long dijkstra() {
    	
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[sr][sc] = 0;
        pq.offer(new Node(sr, sc, 0));

        while (!pq.isEmpty()) {
        	
            Node cur = pq.poll();
            
            if (cur.w != dist[cur.r][cur.c]) continue;

            // 현재가 가장자리면 종료
            if (cur.r == 0 || cur.r == H - 1 || cur.c == 0 || cur.c == W - 1) return cur.w;

            // 탐색
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d], nc = cur.c + dc[d];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                long nw = cur.w + cost[nr][nc];
                if (nw < dist[nr][nc]) {
                    dist[nr][nc] = nw;
                    pq.offer(new Node(nr, nc, nw));
                }
            }
        }
        
     // 도달 불가인 경우는 문제 조건상 없음
        return -1; 
    }
}
