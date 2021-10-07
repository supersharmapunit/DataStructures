import java.util.*;

public class Basic {

    // Highest Frequency Character
    public static void hfc(String str) {
        HashMap<Character, Integer> fmap = new HashMap<>();

        // mapping in map
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            if (fmap.containsKey(ch)) {
                fmap.put(ch, fmap.get(ch) + 1);
            } else {
                fmap.put(ch, 1);
            }
        }

        // frequency calculation
        int max = 0;
        Character mkey = null;
        ArrayList<Character> keys = new ArrayList<>(fmap.keySet());

        for (Character key : keys) {
            if (max <= fmap.get(key)) {

                max = fmap.get(key);
                mkey = key;
            }
        }
        System.out.println(mkey);
    }

    // Get Common Elements - 1(with Hashmap)
    public static void gce(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> fmap = new HashMap<>();

        for (int i = 0; i < arr1.length; i++) {
            if (fmap.containsKey(arr1[i]))
                fmap.put(arr1[i], fmap.get(arr1[i]) + 1);
            else
                fmap.put(arr1[i], 1);
        }

        for (int i = 0; i < arr2.length; i++) {
            if (fmap.containsKey(arr2[i])) {
                fmap.remove(arr2[i]);
                System.out.println(arr2[i]);
            }
        }
    }

    // gce(with hashset) as we dont require to maintain freq.
    public static void gcehs(int[] arr1, int[] arr2) {
        HashSet<Integer> hs = new HashSet<>();

        for (int i = 0; i < arr1.length; i++) {
            if (!hs.contains(arr1[i]))
                hs.add(arr1[i]);
        }

        for (int i = 0; i < arr2.length; i++) {
            if (hs.contains(arr2[i])) {
                hs.remove(arr2[i]);
                System.out.println(arr2[i]);
            }
        }

    }

    public static void gce2(int[] arr1, int[] arr2) { // duplicacy allowed -> need to maintain freq.
        HashMap<Integer, Integer> fmap = new HashMap<>();

        for (int i = 0; i < arr1.length; i++) {
            if (fmap.containsKey(arr1[i]))
                fmap.put(arr1[i], fmap.get(arr1[i]) + 1);
            else
                fmap.put(arr1[i], 1);
        }

        for (int i = 0; i < arr2.length; i++) {
            if (fmap.containsKey(arr2[i])) {
                if (fmap.get(arr2[i]) >= 1) {
                    System.out.println(arr2[i]);
                    fmap.put(arr2[i], fmap.get(arr2[i]) - 1);
                    if (fmap.get(arr2[i]) == 0)
                        fmap.remove(arr2[i]);
                    // reason for last condition -> either it's not present in map
                    // so remove fn. will not do anything otherwise it's present but
                    // has freq. == 0 so it'll remove it beacuse there is no more value
                    // in arr1 in correspondance to arr2[i]
                }
            }
        }
    }

    // Longest Consecutive Sequence Of Elements
    public static void lcse(int[] arr) {
        HashMap<Integer, Boolean> fmap = new HashMap<>();
        // 1. consider every element as starting point
        for (int i = 0; i < arr.length; i++) {
            fmap.put(arr[i], true);
        }

        // 2. discard invalid starting point elements
        for (int i = 0; i < arr.length; i++) {
            if (fmap.containsKey(arr[i] - 1)) {
                fmap.put(arr[i], false);
            }
        }

        // 3. consider longest sequence with every valid element
        int sp = 0;
        int maxlen = 0;

        for (int i = 0; i < arr.length; i++) {
            if (fmap.get(arr[i])) {
                int curr = arr[i];
                int len = 1;

                while (fmap.containsKey(curr + 1) == true) {
                    curr++;
                    len++;
                }

                if (len > maxlen) {
                    sp = arr[i];
                    maxlen = len;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(sp + i);
        }
    }

    public static void KthLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if (pq.size() > k) {
                while (pq.size() > k)
                    pq.remove();
            }
        }

        while (pq.size() != 0) {
            System.out.println(pq.remove());
        }
    }

    // sort k-sorted array
    public static void kSorted(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int i = 0;
        for (; i < k; i++) { // k elements in pq
            pq.add(arr[i]);
        }

        for (; i < arr.length; i++) { // add a element then remove an element and place that into right place
            pq.add(arr[i]);
            arr[i - k] = pq.remove();
        }

        while (pq.size() != 0) { // when arr is iterated but pq still have some elements
            arr[i - k] = pq.remove();
            i++;
        }

        for (i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }
}