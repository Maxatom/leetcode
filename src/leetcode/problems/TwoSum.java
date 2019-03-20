package leetcode.problems;

import utils.ArrayUtils;
import utils.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2018/11/14 22:34
 */
public class TwoSum {
    public static void main(String[] args) {
//        int[] ints={ 2, 7, 11, 15};
        int[] ints={ 3,2,4};
        int tar=6;
        TwoSum twoSum=new TwoSum();
        int[] result=twoSum.twoSum3(ints,tar);
        ArrayUtils.printIntArray(result);
    }

    //O(N) O(N)
    public int[] twoSum3(int[] nums, int target){
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complete=target-nums[i];
            if(map.containsKey(complete)) return new int[]{map.get(complete),i};
            else map.put(nums[i],i);
        }
        throw new IllegalArgumentException("no solution!");
    }
    //O(N^2)
    public int[] twoSum2(int[] nums, int target){
        int[] result=new int[2];
        for (int i = 0; i < nums.length; i++) {
            int x=nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    break;
                }
            }
        }
        return result;
    }
    //排序 O(NlogN)
    public int[] twoSum(int[] nums, int target) {
        int[] orig=Arrays.copyOf(nums,nums.length);
        Arrays.sort(nums);
        int i=0, j=nums.length-1;
        while (i<j){
            if(nums[i]+nums[j]>target) j--;
            else if(nums[i]+nums[j]<target) i++;
            else break;
        }
        int[] result=new int[2];
        for (int k = 0; k < orig.length; k++) {
            if(orig[k]==nums[i]) {result[0]=k;break;}
        }
        for (int k = 0; k < orig.length; k++) {
            if(orig[k]==nums[j]&&k!=result[0]) {result[1]=k; break;}
        }
        if(result[0]>result[1]) {
            int temp=result[0];
            result[0]=result[1];
            result[1]=temp;
        }
        return result;
    }
}
