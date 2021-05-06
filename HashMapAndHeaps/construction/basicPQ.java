import java.util.PriorityQueue;

public class basicPQ{

public static void test1_MinPQ(int[] arr) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(); //Min PQ

    // PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
    //     return a - b; // default behaviour i.e MinPQ
    // });

    for (int ele : arr) pq.add(ele);

    while(pq.size() != 0) System.out.print(pq.remove()+ " "); //.remove() == .poll()

}

public static void test1_MaxPQ(int[] arr) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
        return b - a; // reverse of default i.e MaxPQ
    });

    for (int ele : arr) pq.add(ele);

    while(pq.size() != 0) System.out.print(pq.remove()+ " "); //.remove() == .poll()

}

public static void main(String[] args) {
    int[] arr = {2,5,1,-1,0,-4,-6,3,6,9,9,40};
    test1_MaxPQ(arr);
}

}