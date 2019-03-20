package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;
import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/1/19 10:31
 */
public class RangeSumQuery2DImmutable {
    int[][] sum;
    public static void main(String[] args) {
       int[][] test=new int[][] {{3, 0, 1, 4, 2},
  {5, 6, 3, 2, 1},
  {1, 2, 0, 1, 5},
  {4, 1, 0, 1, 7},
  {1, 0, 3, 0, 5}};
//       test=new int[][]{};
        test=new int[][]{{}};
       RangeSumQuery2DImmutable rangeSum2D=new RangeSumQuery2DImmutable(test);
        PrintUtils.print2DIntArray(rangeSum2D.sum);
       System.out.println(rangeSum2D.sumRegion(2,1,4,3));
        System.out.println(rangeSum2D.sumRegion(1,2,2,4));
        System.out.println(rangeSum2D.sumRegion(1,1,2,2));
        System.out.println(rangeSum2D.sumRegion(0,0,0,0));

    }
    public RangeSumQuery2DImmutable(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return;
        int M=matrix.length;
        int N=matrix[0].length;
        sum=new int[M+1][N+1];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N ; j++)
                sum[i+1][j+1]=sum[i][j+1]-sum[i][j]+sum[i+1][j]+matrix[i][j];
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(sum==null) return 0;
        return sum[row2+1][col2+1]-sum[row1][col2+1]-sum[row2+1][col1]+sum[row1][col1];
    }
}
