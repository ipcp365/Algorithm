
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 원형 연결 리스트 생성
        Node head = new Node(1);
        Node current = head;

        for (int i = 2; i <= n; i++) {
            current.next = new Node(i);
            current = current.next;
        }
        current.next = head; // 마지막 노드가 첫 번째 노드를 가리키게 설정 (원형 리스트)

        sb.append("<");
        Node prev = current; // 현재 노드의 이전 노드를 저장

        while (n > 1) {
            // k-1번 이동 (k번째 노드를 삭제할 예정)
            for (int i = 0; i < k - 1; i++) {
                prev = head;
                head = head.next;
            }
            sb.append(head.data).append(", ");

            // 현재 노드(head) 삭제
            prev.next = head.next;
            head = head.next; // 다음 노드로 이동
            n--;
        }

        // 마지막 노드 처리
        sb.append(head.data).append(">");
        System.out.println(sb);
    }
}
