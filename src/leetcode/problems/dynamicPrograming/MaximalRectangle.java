package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author Shibing
 * @since 2019-05-29 16:26:56
 **/
public class MaximalRectangle {
    public static void main(String[] args) {
        MaximalRectangle rectangle=new MaximalRectangle();
        char[][] matrix={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        matrix=new char[][]{{'1','0','1'},{'1','0','1'},{'1','1','1'},{'1','0','0'}};
        matrix=new char[][]{{'1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','0'},{'1','1','1','1','1','1','1','0'},{'1','1','1','1','1','0','0','0'},{'0','1','1','1','1','0','0','0'}};
        matrix=new char[][]{{'0','1','1','0','1'},{'1','1','0','1','0'},{'0','1','1','1','0'},{'1','1','1','1','0'},{'1','1','1','1','1'},{'0','0','0','0','0'}};
//        System.out.println(rectangle.maximalRectangle(matrix));
        System.out.println(rectangle.maximalRectangle1(matrix));
    }

    //stack
    public int maximalRectangle2(char[][] matrix) {
        int m=matrix.length, n, max=0;
        if(m==0 || (n=matrix[0].length)==0) return 0;
        int[] h=new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                h[j]=matrix[i][j]=='1'?h[j]+1:0;
            for (int j = 0; j < n; j++) {

            }
        }
        return 0;
    }

    public int maximalRectangle1(char[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0) return 0;
        int m=matrix.length, n=matrix[0].length, max=0;
        int[] left=new int[n], right=new int[n], h=new int[n];
        Arrays.fill(left, 0); Arrays.fill(right, n); Arrays.fill(h, 0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                h[j]=matrix[i][j]=='1'?h[j]+1:0;
            }
            int cur_left=0, cur_right=n;
            for (int j = 0; j < n; j++) {
                if(matrix[i][j]=='1') left[j]=Math.max(left[j], cur_left);
                else { left[j]=0; cur_left=j+1;}
            }

            for (int j=n-1; j>=0; j--){
                if(matrix[i][j]=='1') right[j]=Math.min(right[j], cur_right);
                else {right[j]=n; cur_right=j;}
            }
//            System.out.println("----------------------i="+i);
//            PrintUtils.printArray(h);
//            PrintUtils.printArray(left);
//            PrintUtils.printArray(right);

            for (int j = 0; j < n; j++)
                max=Math.max(max, (right[j]-left[j])*h[j]);
        }
        return max;
    }

    //dp(i,j,r)  is the length of the rectangle whose bottom right is in (i,j) and width is r
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
