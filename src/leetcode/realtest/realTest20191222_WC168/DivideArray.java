package leetcode.realtest.realTest20191222_WC168;

import java.util.*;

public class DivideArray {
    public static void main(String[] args) {
        DivideArray divideArray=new DivideArray();
        int[] nums = {1,2,3,3,4,4,5,6}; int k = 4;
//        nums = new int[]{3,2,1,2,3,4,3,4,5,9,10,11}; k = 3;
        nums=new int[]{1,2,3,4}; k = 3;
        nums=new int[]{1}; k = 2;
        nums=new int[]{1,2}; k = 3;
        System.out.println(divideArray.isPossibleDivide(nums, k));
    }
    public boolean isPossibleDivide(int[] nums, int k) {
//        Arrays.sort(nums);
        TreeMap<Integer,Integer> map=new TreeMap<>();
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
            min=Math.min(min,nums[i]);
        }
        int cur=map.get(min);
        while (min!=0) {
            for (int i = 0; i < k; i++) {
                int recent = min + i;
                if (map.get(recent) == null || map.get(recent) < cur) return false;
                if (map.get(recent) == cur) map.remove(recent);
                else map.put(recent, map.get(recent) - cur);
            }
            if(map.isEmpty()) break;
            min = map.firstKey(); cur=map.get(min);
        }
        return true;
    }
}
