import java.util.Scanner;

public class GettingStarted {
    public void printZ() {
        System.out.println("*****");
        System.out.println("   *");
        System.out.println("  *");
        System.out.println(" *");
        System.out.println("*****");
    }

    public boolean isPrime(int n) {

        boolean flag = false;
        for (int div = 2; div * div <= n; div++) {
            if (n % div == 0) {
                flag = true;
                break;
            }
        }

        if (flag == true) {
            return false; // not prime
        } else {
            return true; // prime
        }
    }

    public void printFibTillN() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = 0, b = 1;

        while (n-- != 0) {
            System.out.println(a);
            int c = a + b;
            a = b;
            b = c;
        }
    }

    public void printPrimeTillN() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = n; i <= m; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    public int countDigitsInNumber(int n) {

        int count = 0;

        while (n != 0) {
            n /= 10;
            count++;
        }

        return count;
    }

    public void printReverse() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (n != 0) {
            int temp = n % 10;
            System.out.println(temp);
            n /= 10;
        }
    }

    public void printDigitsOfNumber() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int temp = n;

        int divisor = Math.pow(10, countDigitsInNumber(temp));
        divisor /= 10;

        while (n != 0) {
            ans = n / div;
            System.out.println(ans);
            div /= 10;
            n /= 10;
        }
    }

    public void rotateA_Number() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        // 1. find number of digits
        int num = n;
        int nod = 0;
        while (num != 0) {
            num /= 10;
            nod++;
        }

        // 2. bring value of k in range
        k = k % nod;
        if (k < 0) {
            k = k + nod;
        }

        // 3. set value of div and mult
        int div = (int) Math.pow(10, k);
        int mult = (int) Math.pow(10, nod - k);

        // 4. find q and r
        int q = n / div;
        int r = n % div;

        // 5. update ans and print it
        int ans = (r * mult) + q;
        System.out.println(ans);

    }

    public void printGCD_LCM() {
        Scanner sc = new Scanner(System.in);
        int divisor = sc.nextInt();
        int dividend = sc.nextInt();

        int dividend1 = dividend;
        int divisor1 = divisor;

        int gcd = 0;
        while (dividend % divisor != 0) {
            int remainder = dividend % divisor;
            gcd = remainder;
            if (remainder == 0)
                break;
            dividend = divisor;
            divisor = remainder;

        }

        int lcm = (divisor1 * dividend1) / gcd;

        System.out.println(gcd);
        System.out.println(lcm);
    }

    public void primeFactorization() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (n != 1) {
            for (int div = 2; div <= n; div++) {
                if (n % div == 0) {
                    n /= div;
                    System.out.print(div + " ");
                    break;

                }
            }
        }
    }

    public void inverseOfDigit() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int ians = 0;
        int op = 1;

        while (n != 0) {
            int od = n % 10;
            int ip = od;
            int id = op;

            ians += id * (int) Math.pow(10, ip - 1);

            n /= 10;
            op++;
        }

        System.out.println(ians);
    }

    public void inverseOfDigit1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int ians = 0;
        int i = 1;

        while (n != 0) {
            int ld = n % 10;
            n /= 10;

            ians += i * (int) Math.pow(10, ld - 1);
            i++;
        }

        System.out.println(ians);
    }
}