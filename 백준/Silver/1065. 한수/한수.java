import java.util.Scanner;

public class Main {

    public static boolean isHansu(int num) {
        if (num < 100) return true;

        int a = num / 100;         // 백의 자리
        int b = (num / 10) % 10;   // 십의 자리
        int c = num % 10;          // 일의 자리

        return (a - b) == (b - c);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int count = 0;

        for (int i = 1; i <= N; i++) {
            if (isHansu(i)) count++;
        }

        System.out.println(count);
    }
}
