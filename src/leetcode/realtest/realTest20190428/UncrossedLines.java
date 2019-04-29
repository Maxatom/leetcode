package leetcode.realtest.realTest20190428;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-04-29 16:38:18
 **/
public class UncrossedLines {
    public static void main(String[] args) {
        UncrossedLines lines=new UncrossedLines();
        int[] A = {1,4,2}, B = {1,2,4};
        A = new int[]{2,5,1,2,5}; B =new int[]{10,5,2,1,5,2};
        A = new int[]{1,3,7,1,7,5}; B =new int[]{1,9,2,5,1};
        A=new int[]{1}; B=new int[]{2};
        System.out.println(lines.maxUncrossedLines(A,B ));
        System.out.println(lines.maxUncrossedLines1(A,B ));
    }

    //1D Array, O(MN)
    public int maxUncrossedLines1(int[] A, int[] B) {
        if(A.length<B.length) maxUncrossedLines1(B, A);
        int[] dp=new int[B.length+1], dpn=new int[B.length+1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                dpn[j]= A[i-1]==B[j-1] ? dp[j-1] +1 : Math.max(dp[j], dpn[j-1]);
            }
            int[] temp=dp; dp=dpn; dpn=temp;
        }
//        PrintUtils.printArray(dpn);
//        PrintUtils.printArray(dp);
        return dp[B.length];
    }

    // dp(i,j) is the longest common sub sequences of A[0~i] and B[0~j]
    public int maxUncrossedLines(int[] A, int[] B) {
        int[][] dp=new int[A.length+1][B.length+1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                    dp[i][j]= A[i-1]==B[j-1] ? dp[i-1][j-1] +1 : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        PrintUtils.print2DIntArray(dp);
        return dp[A.length][B.length];
    }
}
