public class FuntionAndArrays {
    public static void main(String args[]) {
        
    }

    public static int digitFrequency(int n, int digit) {
        int count = 0;
        while (n != 0) {
            int ldigit = n % 10;
            if (digit == ldigit)
                count++;
            n = n / 10;
        }
        return count;
    }

    public static int getValueInBase(int n, int b) {
        int ans = 0, power = 1;
        while (n != 0) {
            int remainder = n % b;
            n /= b;

            ans *= (remainder * power);
            power *= 10;
        }
        return ans;
    }

    public static int getValueIndecimal(int n, int b) {
        int ans = 0, power = 1;
        while (n != 0) {
            int remainder = n % 10;
            n /= 10;

            ans += remainder * power;
            power *= b;
        }
        return ans;
    }

    public static int anyBaseAddition(int b, int n1, int n2) {
        int myans = 0, power = 1, carry = 0;
        while (n1 != 0 || n2 != 0 || carry != 0) {
            // last digit from num1
            int ld1 = n1 % 10;
            n1 /= 10;

            // last digit from num
            int ld2 = n2 % 10;
            n2 /= 10;

            // sum of last digits
            int sum = ld1 + ld2 + carry;

            // q -> if sum is > than base then value will carry that value with help of
            // quotient
            // r -> if sum is < than base then value will add that value to ans with help of
            // remainder
            int r = sum % b;
            int q = sum / b;

            myans += (r * power);
            carry = q;
            power *= 10;
        }

        return myans;
    }

    public static int anyBaseSubtraction(int base, int n1, int n2){
        int ans = 0, pow = 0, borrow = 0;
        
        while(n2 != 0){
            int ld1 = n1 % 10;
            n1 /= 10;
            
            int ld2 = n2 % 10;
            n2 /= 10;
            
            int diff = 0;
            ld2 += borrow;
            
            if(ld2 >= ld1){
                borrow = 0;
                diff = ld2 - ld1;
            } else {
                borrow = -1;
                diff = ld2 + base - ld1;
            }
            
            ans += diff * (int)Math.pow(10,pow++);
        }
        
        return ans;
    }

    public static int getProduct(int b, int n1, int n2){
        int ans = 0, power = 1;
        while(n2 != 0){
            int ld = n2 % 10;
            n2 /= 10;

            int sdm = getProductWithSingleDigit(n1, ld, b);
            
            ans = getSum(b, ans, sdm * power) ;

            power *= 10;
        }
        return ans;
    }

    public static int getProductWithSingleDigit(int n1, int n2, int b){
        int myans = 0, power = 1, carry = 0;
        while (n1 != 0 || carry != 0) {
            
            int ld1 = n1 % 10;
            n1 /= 10;
            
            int product = (n2*ld1) + carry;

            
            int r = product % b;
            int q = product / b;

            myans += (r * power);
            carry = q;
            power *= 10;
        }

        return myans;
    }
    
    public static int getSum(int b, int n1, int n2){
        int myans = 0, power = 1, carry = 0;
        while(n1 != 0 || n2 != 0 || carry != 0 ){
            // last digit from num1
            int ld1 = n1 % 10;
            n1 /= 10;

            // last digit from num
            int ld2 = n2 % 10;
            n2 /= 10;

            // sum of last digits
            int sum =  ld1 + ld2 + carry;

            //q -> if sum is > than base then value will carry that value with help of quotient
            // r -> if sum is < than base then value will add that value to ans with help of remainder
            int r = sum % b;
            int q = sum / b;

            myans += (r * power);
            carry = q;
            power *= 10;
        }

        return myans;
    }

    public static void firstIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while (si <= ei) {
            int mid = (si + ei) / 2;

            if (arr[mid] >= data)
                ei = mid - 1;
            else
                si = mid + 1;
        }

        if (arr[si] != data)
            System.out.println(-1);
        else
            System.out.println(si);

    }

    public static void lastIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while (si <= ei) {
            int mid = (si + ei) / 2;

            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        if (arr[ei] != data) {
            System.out.println(-1);
        } else {
            System.out.println(ei);
        }
    }

    /*---------------------------------------------------------ARRAYS---------------------------------------------------------*/

    public static int span(int[] arr, int max, int min) {
        for (int ele : arr) {
            if (ele > max)
                max = ele;
            else if (ele < min)
                min = ele;
            else
                continue;
        }
        return max - min;
    }

    public static int findEle(int[] arr, int ele) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == ele)
                return i;
        return -1;
    }

    public static int findMax(int[] arr) {
        int maxv = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= maxv)
                maxv = arr[i];
        }

        return maxv;
    }

    public static int findMin(int[] arr) {
        int minv = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= minv)
                minv = arr[i];
        }

        return minv;
    }

    static void printBar(int[] arr, int max) {
        for (int floor = max; floor >= 1; floor--) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= floor) {
                    System.out.print("*\t");
                } else
                    System.out.print("\t");
            }
            System.out.println();
        }

    }

    // exit point of a matrix
    public static void exitPoint(int[][] arr){
        int dir = 0, i = 0, j = 0;
        while(true){
            // cal. dir
            dir = (dir + arr[i][j]) % 4;
            
            // handle dir of movements
            if (dir == 0){
                // east(starting point)
                j++;
            } else if (dir == 1){
                // south 
                i++;
            } else if (dir == 2){
                // west
                j--;
            } else if (dir == 3){
                // north
                i--;
            }
            
            
            // handle cases when it wil be out of the matrix i.e give ans
            // we'll -1 beacuse we want exit point which is inside the matrix
            if(i < 0){
                i++;
                break;
            } else if(j < 0){
                j++;
                break;
            } else if(i == arr.length){
                i--;
                break;
            } else if(j == arr[0].length){
                j--;
                break;
            }
        }
        
        System.out.println(i);
        System.out.println(j);
    }
}