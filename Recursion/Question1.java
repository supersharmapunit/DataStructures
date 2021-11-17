public class Question1 {
    public static void main(String[] args) {

    }

    // n boxes me i items in which i < n so some places will be empty
    public static void permutations(int[] boxes, int ci, int ti) {
        if (ci > ti) {
            for (int i = 0; i < boxes.length; i++) {
                System.out.print(boxes[i]);
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] == 0) {
                boxes[i] = ci;
                permutations(boxes, ci + 1, ti);
                boxes[i] = 0;
            }
        }
    }

    public static void permutations2(int cb, int tb, int[] items, int ssf, int ts, String asf){
        if(cb > tb){
            if(ssf == ts){
                System.out.println(asf);
            }
            return;
        }
        
        // yes call -> multiple
        for(int i = 0; i < items.length; i++){
            if(items[i] == 0){
                items[i] = cb;
                permutations2(cb+1, tb, items, ssf+1, ts, asf+(i+1));
                items[i] = 0;
            }
        }

        // no call-> single
        permutations2(cb+1, tb, items, ssf, ts, asf+"0");
      }
}