import java.io.*;
import java.util.*;

public class FibonacciDP{

public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    System.out.println(fibonacci(n, new int[n + 1]));
 }

 public static int fibonacci(int n, int[] qb){
    if (n == 0 || n == 1){
        return n;
    }

    if (qb[n] != 0){
        return qb[n];
    }

    int fibnm1 = fibonacci(n-1, qb);
    int fibnm2 = fibonacci(n - 2, qb);
    int fibn = fibnm1 + fibnm2;
    qb[n] = fibn;
    
    return qb[n];
 }

}