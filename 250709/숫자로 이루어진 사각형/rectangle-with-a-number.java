import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i=0; i<n*n; i++){
            System.out.print((i%9+1) + " ");

            if((i+1)%4 == 0) System.out.println("");
        }
    }
}