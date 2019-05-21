package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-21 14:17:54
 **/
public class UniquePathsII {
    public static void main(String[] args) {
        UniquePathsII pathsII=new UniquePathsII();
        int[][] grid={ {0,0,0}, {0,1,0}, {0,0,0} };
         grid=new int[][]{{0,0,1}, {0,0,0}, {0,0,0}};
         grid=new int[][]{{1}};
        System.out.println(pathsII.uniquePathsWithObstacles(grid));
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length, n=obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1 || obstacleGrid[m-1][n-1]==1) return 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i==0 && j==0){ obstacleGrid[0][0]=1; continue;}
                if(obstacleGrid[i][j]==1) obstacleGrid[i][j]=0;
                else obstacleGrid[i][j]= (i>0?obstacleGrid[i-1][j]:0) + (j>0?obstacleGrid[i][j-1]:0);
            }
        }
//        PrintUtils.print2DIntArray(obstacleGrid);
        return obstacleGrid[m-1][n-1];
    }
}
