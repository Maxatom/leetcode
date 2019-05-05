package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/2/8 13:39
 */
public class NumberofLongestIncreasingSubsequence {
    public static void main(String[] args) {
        NumberofLongestIncreasingSubsequence subsequence=new NumberofLongestIncreasingSubsequence();
        int[] nums={1,3,5,4,7};
//        nums=new int[]{2,2,2,2,2};
        nums=new int[]{1,2,3,5,9,4,2,34,5};
//        nums=new int[0];
//        nums=new int[]{2};
//        nums=new int[]{2,5,2,5,9};
//        nums=new int[]{2,4,2,43,5,3,4,3,3,4};
        System.out.println(subsequence.findNumberOfLIS(nums));
    }
    public int findNumberOfLIS(int[] nums) {
        if(nums.length<2) return nums.length;
        // dp(i,0) the length of LIS
        // dp(i,1) the num of LIS ends with i
        int[][] dp=new int[nums.length][2];
        int max=1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i-1; j >=0 ; j--) {
                if(nums[i]>nums[j]){
                    if(dp[j][0]+1>dp[i][0]){
                        dp[i][1]=dp[j][1];
                        dp[i][0]=dp[j][0]+1;
                    }else if(dp[j][0]+1==dp[i][0]){
                        dp[i][1]+=dp[j][1];
                    }
                    if(dp[i][0]>max) max=dp[i][0];
                }
            }
            if(dp[i][0]==0) {
                dp[i][0]=1;  dp[i][1]=1;
            }
        }
        int res=0;
        for (int[] p:dp)
            if(p[0]==max) res+=p[1];
        PrintUtils.print2DIntArray(dp);
        return res;
    }
}
