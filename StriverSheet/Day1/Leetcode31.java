package Day1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Leetcode31 {
    public static void main(String[] args) {
        String x = "abcd";
        int[] nums = {1,2,3};
        System.out.println(permutationHelper(nums,0,new ArrayList<>()));
        // System.out.println(permutation(x, ""));
    }

    private static ArrayList<String> permute(String str) {
        if(0==str.length()){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);
        ArrayList<String> rres = permute(ros);
        ArrayList<String> myList = permute(ros);
        for(int i = 0; i < rres.size(); i++){
            myList.add(ch + rres.get(i));
        }
        return myList;
    }

    private static ArrayList<String> permutation(String str, String asf) {
        if(str.length() == 0){
            ArrayList<String> base = new ArrayList<>();
            base.add(asf);
            return base;
        }
        ArrayList<String> myList = new ArrayList<>();
        for(int i =0 ; i < str.length(); i++){
           char ch = str.charAt(i);
           String ros = str.substring(0,i) + str.substring(i+1);
           ArrayList<String> rres = permutation(ros, asf+ch);
           myList.addAll(rres);
       }
       return myList;

    }

    private static ArrayList<ArrayList<Integer>> permutationHelper(int[] nums, int idx, ArrayList<Integer> asf) {
        if(idx >= nums.length-1){
            ArrayList<ArrayList<Integer>> base = new ArrayList<>();
            base.add(asf);
            return base;
        }
        ArrayList<ArrayList<Integer>> myList = new ArrayList<>();
        for(int i = 0; i <= idx; i++){
            int n = nums[i];
            ArrayList<Integer> ros = new ArrayList<>();
            for(int ele : nums){
                if(ele!= n) ros.add(ele);
            }
            asf.add(n);
            ArrayList<ArrayList<Integer>> rres = permutationHelper(nums,idx+1, asf);
            myList.addAll(rres);
        }
        return myList;
    }
}
