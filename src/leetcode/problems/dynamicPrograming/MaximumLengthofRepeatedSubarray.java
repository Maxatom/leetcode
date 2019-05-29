package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-24 14:18:04
 **/
public class MaximumLengthofRepeatedSubarray {
    public static void main(String[] args) {
        MaximumLengthofRepeatedSubarray subarray=new MaximumLengthofRepeatedSubarray();
        int[] A=new int[]{1,2,3,2,1}, B= {3,2,1,4,7};
        System.out.println(subarray.findLength(A, B));
        System.out.println(subarray.findLength1(A, B));
    }
    //1D array
    public int findLength1(int[] A, int[] B) {
        int m=A.length, n=B.length, max=0;
        if(m<n) return findLength1(B, A);
        int[] dp=new int[n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = n; j > 0; j--) {
                dp[j]= A[i-1]==B[j-1]? dp[j-1]+1:0;
                max=Math.max(max, dp[j]);
            }
        }
        return max;
    }
    //2D Array
    public int findLength(int[] A, int[] B) {
        int m=A.length, n=B.length;
        int[][] dp=new int[m+1][n+1];
        int max=0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j]=A[i-1]==B[j-1]? dp[i-1][j-1]+1:0;
                max=Math.max(max, dp[i][j]);
            }
        }
//        PrintUtils.print2DIntArray(dp);
        return max;
    }
}
