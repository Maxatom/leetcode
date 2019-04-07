package leetcode.problems.dynamicPrograming;

import utils.RandomGenerator;
import utils.PrintUtils;
import utils.Utils;

/**
 * @author shibing
 * @since 2018/12/22 12:22
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence subsequence=new LongestIncreasingSubsequence();
        RandomGenerator.String generator=new RandomGenerator.String(10);
        generator.setCharBound('a','x');
        String s=generator.next();
        s="";
        s="a";
        int[] nums=new int[]{};
//        nums=new int[]{5};
        nums=new int[]{10,9,2,5,3,7,101,18};
        nums=new int[]{2,2};
        System.out.println(s+", result=" +subsequence.lengthOfLIS(nums));


    }
    //DP   dp(i)=max(1, Max(dp[j]))  j=(1,i);
    //dp[i] indicates the length of the maximum increasing subsequence  which ends with s[i]
    public int lengthOfLIS(String s){
        if(s.length()==0) return 0;
        int[] dp=new int[s.length()];
        dp[0]=1;
        for (int i = 1; i <dp.length ; i++) {
            dp[i]=1;
            for (int j = 0; j < i; j++) {
                if(s.charAt(j)<=s.charAt(i)) dp[i]=Math.max(dp[i],dp[j]+1);
            }
        }
        PrintUtils.printIntArray(dp);
        return Utils.max(dp);
    }
    //for Integer O(N^2) dp
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0) return 0;
        int[] dp=new int[nums.length];
        int max=1;
        for (int i = 0; i <dp.length ; i++) {
            dp[i]=1;
            for (int j = 0; j < i; j++)
                if(nums[j]<nums[i]) dp[i]=Math.max(dp[i],dp[j]+1);
            max=Math.max(max, dp[i]);
        }
//        PrintUtils.printIntArray(dp);
        return max;
    }
}
