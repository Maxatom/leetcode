package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-29 13:50:03
 **/
public class MaximalSquare {
    public static void main(String[] args) {
        MaximalSquare square=new MaximalSquare();
        char[][] matrix={{'1', '0', '1', '0', '0'},{ '1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1' ,'0' ,'0', '1' ,'0'}};
        matrix=new char[][]{{'1'}};
        matrix=new char[][]{{'0'}};
        System.out.println(square.maximalSquare(matrix));
    }


    //dp O(N^2)
    public int maximalSquare(char[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0) return 0;
        int m=matrix.length, n=matrix[0].length, max=0;
        if(m==0 || n==0) return 0;
        int[][] grid=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j]=Math.min(i+1,j+1);
            }
        }
        for (int i = 0; i < m; i++) {
            int l=0;
            for (int j = 0; j < n; j++)
                grid[i][j] = Math.min(grid[i][j], l = matrix[i][j] == '0' ? 0 : l + 1);
        }
        for (int i = 0; i < n; i++) {
            int u=0;
            for (int j = 0; j < m; j++)
                grid[j][i] = Math.min(grid[j][i], u = matrix[j][i] == '0' ? 0 : u + 1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = i>0 && j>0 ? Math.min(grid[i][j], grid[i-1][j-1]+1) : grid[i][j];
                max=Math.max(max, grid[i][j]);
            }
        }
        return max*max;
    }
}
