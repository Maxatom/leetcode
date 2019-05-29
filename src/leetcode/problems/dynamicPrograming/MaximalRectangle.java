package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-29 16:26:56
 **/
public class MaximalRectangle {
    public static void main(String[] args) {
        MaximalRectangle rectangle=new MaximalRectangle();
        char[][] matrix={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//        matrix=new char[][]{{'1','0','1'},{'1','0','1'},{'1','1','1'},{'1','0','0'}};
        System.out.println(rectangle.maximalRectangle(matrix));
    }


    //dp(i,j,r)  is the length of column of the rectangle whose bottom right is in (i,j) and row's length is r
    //O(N^3)
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0) return 0;
        int m=matrix.length, n=matrix[0].length, max=0;
        int[][][] dp=new int[m][n][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j]=='0') continue;
                for (int r = 1; r <= j+1; r++) {
                    if(matrix[i][j-r+1]=='0') break;
                    dp[i][j][r]=(i>0?dp[i-1][j][r]:0)+1;
                    max=Math.max(max, dp[i][j][r]*r);
                }
            }
        }
        return max;
    }
}
