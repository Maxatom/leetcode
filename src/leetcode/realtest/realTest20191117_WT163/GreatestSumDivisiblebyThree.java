package leetcode.realtest.realTest20191117_WT163;

import utils.PrintUtils;

public class GreatestSumDivisiblebyThree {
    public static void main(String[] args) {
        GreatestSumDivisiblebyThree three=new GreatestSumDivisiblebyThree();
        int[] nums = {3,6,5,1,8};
        nums= new int[]{3};
//        nums = new int[]{1,2,3,4,4};
        nums=new int[]{2,19,6,16,5,10,7,4,11,6};
        System.out.println(three.maxSumDivThree(nums));

    }
    //dp[pos][mod] the maximum possible sum starting in the position "pos" int the array
    //dp[pos][mod] = dp[pos+1][mod-A[pos]%3]
    public int maxSumDivThree(int[] nums) {
        int N = nums.length;
        int[][] dp=new int[N][3];
        dp[0][nums[0]%3]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mod=nums[i]%3;
            for (int j = 0; j < 3; j++) {
                if(j==0)
                    dp[i][(mod+j)%3]=Math.max(dp[i-1][(mod+j)%3], (dp[i-1][j]!=0?dp[i-1][j]:0)+nums[i]);
                else
                   dp[i][(mod+j)%3]=Math.max(dp[i-1][(mod+j)%3], dp[i-1][j]!=0?dp[i-1][j]+nums[i]:0);

            }
        }
        PrintUtils.print2DIntArray(dp);
        return dp[N-1][0]!=-1?dp[N-1][0]:0;
    }
}
