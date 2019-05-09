package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shibing
 * @since 2019-05-09 11:26:57
 **/
public class LargestDivisibleSubset {
    public static void main(String[] args) {
        LargestDivisibleSubset divisible=new LargestDivisibleSubset();
        int[] nums={1,2,3};
//        nums=new int[]{1,2,4,8};
//        nums=new int[]{1,2,3,6,4,8};
//        nums=new int[]{546,669};
        nums=new int[0];
        PrintUtils.printList(divisible.largestDivisibleSubset(nums), p->String.valueOf(p));
    }
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums.length==0) return new ArrayList<>();
        Arrays.sort(nums);
        int n=nums.length, max=0;
        int[] dp=new int[n];
        int[] pre=new int[n];
        for (int i = 0; i < n; i++) {
            dp[i]=1; pre[i]=i;
            for (int j = i-1; j >=0 ; j--) {
                if(nums[i]%nums[j]==0 && dp[j]+1>dp[i]){
                    dp[i]=dp[j]+1;
                    pre[i]=j;
                }
            }
            if(dp[i]>dp[max]) max = i;
        }
//        PrintUtils.printArray(pre);
        List<Integer> list=new ArrayList<>();
        for (int i = max; i >= 0 ; i=pre[i]) {
            list.add(nums[i]);
            if(i==pre[i]) break;
        }
        return list;
    }
}
