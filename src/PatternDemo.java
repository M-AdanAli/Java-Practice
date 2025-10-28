import java.util.Scanner;

public class PatternDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter the scaling number : ");
//        int n = scan.nextInt();
        int n=1;
        for (int i = 1; i<=(n*4)+1 ; i++){
            for (int j=1; j<=(5*n)-(i-1);j++){
                System.out.print(" ");
            }
            for (int j=1; j<=(10*n)+(2*(i-1)); j++){
                System.out.print("*");
            }
            for (int j=1; j<=(5*n)-(i-1); j++){
                System.out.print(" ");
            }
            for(int j=1; j<=(10*n)-(2*(i-1)); j++){
                System.out.print(" ");
            }
            for(int j=(10*n)+(2*(i-1)); j>=(10*n); j--){
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i=1; i<=31; i++){
            System.out.print("*");
        }
        System.out.println();

    }
}
