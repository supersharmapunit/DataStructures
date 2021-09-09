public class Pattern {
    public static void main(String[] args) {
        patternHelper();
    }

    public static void patternHelper() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        pattern1(n);
    }

    public static void pattern1(int n) {
        int row = 1, nst = 1;

        while (row <= n) {

            for (int i = 1; i <= nst; i++)
                System.out.print("*" + "\t");

            System.out.println();

            nst++;
            row++;
        }
    }

    public static void pattern2(int n) {
        int row = 1, nst = n;

        while (row <= n) {

            for (int i = 1; i <= nst; i++)
                System.out.print("*" + "\t");

            System.out.println();

            nst--;
            row++;
        }
    }

    public static void pattern3(int n) {
        int row = 1, nst = 1, nsp = n - 1;
        while (row <= n) {
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");
            for (int i = 1; i <= nst; i++)
                System.out.print("*" + "\t");

            System.out.println();

            nsp--;
            nst++;
            row++;
        }

    }

    public static void pattern4(int n) {
        int row = 1, nst = n, nsp = 0;
        while (row <= n) {
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");
            for (int i = 1; i <= nst; i++)
                System.out.print("*" + "\t");

            System.out.println();

            nsp++;
            nst--;
            row++;
        }
    }

    public static void pattern5(int n) {
        int row = 1, nst = 1, nsp = (n / 2);

        while (row <= n) {
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");
            for (int i = 1; i <= nst; i++)
                System.out.print("*" + "\t");

            System.out.println();

            if (row <= (n / 2)) {
                nst += 2;
                nsp--;
            } else {
                nst -= 2;
                nsp++;
            }
            row++;
        }
    }

    public static void pattern6(int n) {
        int row = 1, nsp = 1, nst = (n / 2) + 1;

        while (row <= n) {
            for (int i = 1; i <= nst; i++)
                System.out.print("*\t");
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");
            for (int i = 1; i <= nst; i++)
                System.out.print("*\t");

            System.out.println();

            if (row <= n / 2) {
                nst--;
                nsp += 2;
            } else {
                nst++;
                nsp -= 2;
            }
            row++;

        }
    }

    public static void pattern7(int n) {
        int row = 1, nsp = 0;

        while (row <= n) {
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");
            System.out.println("*");

            row++;
            nsp++;
        }
    }

    public static void pattern8(int n) {
        int row = 1, nsp = n - 1;

        while (row <= n) {
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");
            System.out.println("*");

            row++;
            nsp--;
        }
    }

    public static void pattern9(int n) {
        int np = n + 1;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (row == col || ((row + col) == np)) {
                    System.out.print("*	");
                } else {
                    System.out.print("	");
                }

            }
            System.out.println();
        }
    }

    public static void pattern10(int n) {
        int row = 1, nsp = n / 2, nst = 1;

        while (row <= n) {
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");

            for (int i = 1; i <= nst; i++) {
                if (i == 1 || i == nst)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }

            System.out.println();

            if (row <= n / 2) {
                nst += 2;
                nsp--;
            } else {
                nst -= 2;
                nsp++;
            }
            row++;

        }
    }

    public static void pattern11(int n) {
        int row = 1, nst = 1, num = 1;

        while (row <= n) {

            for (int i = 1; i <= nst; i++)
                System.out.print("" + num++ + "\t");

            System.out.println();

            nst++;
            row++;
        }
    }

    public static void pattern12(int n) {
        int row = 1, nst = 1, a = 0, b = 1;

        while (row <= n) {

            for (int i = 1; i <= nst; i++) {
                System.out.print(a + "\t");
                int c = a + b;
                a = b;
                b = c;
            }

            System.out.println();

            nst++;
            row++;
        }
    }

    public static void pattern13(int n) { // PASCAL TRIANGLE
        for (int i = 0; i < n; i++) {
            int val = 1;
            for (int j = 0; j <= i; j++) {
                System.out.print(val + "\t");
                val = (val * (i - j)) / (j + 1);
                // val *= ((i-j)/(j+1)); this is wrong
            }
            System.out.println();
        }
    }

    public static void pattern14(int n) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " * " + i + " = " + n * i);
        }
    }

    public static void pattern15(int n) {
        int row = 1, nst = 1, nsp = (n / 2), oval = 1;

        while (row <= n) {
            int ival = oval;
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");
            for (int i = 1; i <= nst; i++) {
                System.out.print(ival + "\t");
                if (i <= (nst / 2))
                    ival++;
                else
                    ival--;

            }

            System.out.println();

            if (row <= (n / 2)) {
                nst += 2;
                nsp--;
                oval++;
            } else {
                nst -= 2;
                nsp++;
                oval--;
            }
            row++;
        }
    }

    public static void pattern16(int n) {
        int row = 1, nst = 1, nsp = 2 * n - 3;

        while (row <= n) {
            for (int i = 1; i <= nst; i++)
                System.out.print(i + "\t");
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");
            if (row == n)
                nst--;
            for (int i = nst; i >= 1; i--)
                System.out.print(i + "\t");

            System.out.println();

            nst++;
            nsp -= 2;
            row++;
        }
    }

    public static void pattern17(int n) {
        int row = 1, nsp = n / 2, nst = 1;

        while (row <= n) {
            // print space
            for (int i = 1; i <= nsp; i++) {
                if (row == (n / 2) + 1) {
                    System.out.print("*	");
                } else {

                    System.out.print("	");
                }
            }
            // print stars
            for (int i = 1; i <= nst; i++) {
                System.out.print("*	");
            }
            // next line
            System.out.println();
            // preparation
            if (row >= (n / 2) + 1) {
                nst--;
            } else {
                nst++;
            }
            row++;

        }
    }

    public static void pattern18(int n) {
        int row = 1, nst = n, nsp = 0;

        while (row <= n) {
            for (int i = 1; i <= nsp; i++)
                System.out.print("\t");

            for (int i = 1; i <= nst; i++) {
                if (i == 1 || i == nst || row == 1 || row == n)
                    System.out.print("*\t");
                else {
                    if (row <= n / 2)
                        System.out.print("\t");
                    else
                        System.out.print("*\t");
                }
            }

            System.out.println();

            if (row <= n / 2) {
                nst -= 2;
                nsp++;
            } else {
                nst += 2;
                nsp--;
            }

            row++;
        }
    }

    public static void pattern19(int n) {
        int row = 1, nst = n;

        while (row <= n) {
            for (int i = 1; i <= nst; i++) {
                if (row == 1) {
                    if (i > (n / 2) + 1 && i < n)
                        System.out.print("\t");
                    else
                        System.out.print("*\t");
                } else if (row > 1 && row < n / 2 + 1) {
                    if (i == (n / 2) + 1 || i == n)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else if (row == n / 2 + 1) {
                    System.out.print("*\t");
                } else if (row > n / 2 + 1 && row < n) {
                    if (i == 1 || i == (n / 2) + 1)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else {
                    if (i > 1 && i < (n / 2) + 1)
                        System.out.print("\t");
                    else
                        System.out.print("*\t");
                }

            }
            System.out.println();
            row++;
        }
    }

    public static void pattern20(int n) {
        int np = n + 1;

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {

                if (col == 1 || col == n) {
                    System.out.print("*	");
                } else if ((row == col || ((row + col) == np)) && (row > n / 2)) {
                    System.out.print("*	");

                } else {
                    System.out.print("	");
                }

            }
            System.out.println();
        }
    }

}