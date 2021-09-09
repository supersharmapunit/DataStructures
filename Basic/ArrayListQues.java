public class ArrayListQues{
    // isPrime
    public static void solution(ArrayList<Integer> al){
		for (int i = 0; i < al.size(); i++){
		    if (isPrime(al.get(i)){
		        al.remove(i);
		        i--;
		    }
		}
		
	}
	
	public static boolean isPrime(int n){
           for (int div = 2; div*div <= n; div++){
               if (n % div == 0){
                   return false;
               }
           }
           return true;
	}
}