package leetcode.problems.dynamicPrograming;

import utils.ArrayGenerator;
import utils.PrintUtils;
import utils.Utils;

import java.util.*;

/**
 * @author Shibing
 * @since 2019-05-20 17:22:01
 **/
public class DeleteandEarn {
    public static void main(String[] args) {
        DeleteandEarn earn=new DeleteandEarn();
        int[] nums = {3, 4, 2};
//        nums = new int[]{2, 2, 3, 3, 3, 4};
//        nums=new int[]{879, 280, 64, 253, 706, 552, 811, 856, 472, 375, 791, 733, 986, 370, 373, 683, 571, 552, 592, 268};
        nums=new int[]{1,2,2,2,2,2,2,2,2,3,4,5,5,5,5,5,5,5,5,5,6};
//        nums=new int[]{4};
//        nums=ArrayGenerator.intArray(10000, 1, 20);
        PrintUtils.printArray(nums);
        System.out.println(earn.deleteAndEarn2(nums));
        System.out.println(earn.deleteAndEarn3(nums));
    }

    public int deleteAndEarn3(int[] nums) {
        int[] sum=new int[10001];
        for (int i = 0; i < nums.length; i++)
            sum[nums[i]]+=nums[i];
        int skipi=0, takei=0;
//        PrintUtils.printArray(sum);
        for (int i = 1; i < 10001; i++) {
            int temp=skipi;
            skipi=Math.max(takei, skipi);
            takei=temp+sum[i];
//            System.out.println("i="+i+", skipi="+skipi+", takei="+takei);
        }
        return Math.max(skipi, takei);
    }
    //dp O(N)
    public int deleteAndEarn2(int[] nums) {
        int[] cnts=new int[10001];
        int n=nums.length, max=0;
        for (int i = 0; i < n; i++){
            cnts[nums[i]]++;
            max=Math.max(max, nums[i]);
        }
        PrintUtils.printArray(cnts);
        int[][] dp=new int[max+1][2];
        for (int i = 1; i <= max; i++) {
            dp[i][1]=dp[i-1][0]+cnts[i]*i;
            dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1]);
        }
        return Math.max(dp[max][0], dp[max][1]);
    }


    // Wrong
    // TLE dp top-down
    public int deleteAndEarn1(int[] nums) {
        Arrays.sort(nums);
        return recursive(nums, null, 0, new int[10000][nums.length]);
    }
    int recursive(int[] nums, Integer last, int idx, int[][] memo){
        int n=nums.length;
        if(idx==n) return 0;
        if(last!=null && memo[last][idx]>0) return memo[last][idx];
        int max=0;
        for (int i = idx; i < n; i++) {
            if(last !=null && (nums[i]-1==last || nums[i]+1==last)) continue;
            max=Math.max(recursive(nums, nums[i], i+1, memo)+nums[i], max);
        }
        if(last!=null) memo[last][idx]=max;
        return max;
    }

    //Wrong
    //TLE O(2^N)  brute force
    public int deleteAndEarn(int[] nums) {
        return recursive(nums, new HashMap<>(), 0);
    }
    int recursive(int[] nums, Map<Integer, Integer> set, int idx){
        int n=nums.length;
        if(idx==n) return 0;
        int max=0;
        for (int i = idx; i < n; i++) {
            if(set.containsKey(nums[i]-1) || set.containsKey(nums[i]+1)) continue;
            set.put(nums[i], set.getOrDefault(nums[i], 0)+1);
            max=Math.max(nums[i]+recursive(nums, set, i+1), max);
            set.put(nums[i], set.get(nums[i])-1);
            if(set.get(nums[i])==0) set.remove(nums[i]);
        }
        return max;
    }
}
