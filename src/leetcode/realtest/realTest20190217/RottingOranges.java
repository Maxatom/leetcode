package leetcode.realtest.realTest20190217;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/2/17 11:18
 */
public class RottingOranges {
    public static void main(String[] args) {
        RottingOranges oranges=new RottingOranges();
        int[][] grid=new int[][]{{2,1,1},{1,1,0},{0,1,1}};
//        grid=new int[][]{{2,1,1},{0,1,1},{1,0,1}};
//        grid=new int[][]{{0,2}};
        PrintUtils.print2DIntArray(grid);
        System.out.println(oranges.orangesRotting(grid));
    }
    public int orangesRotting(int[][] grid) {
        int M=grid.length, N=grid[0].length;
        int[][] gridnew=new int[M][N];
        int count=0;
        boolean flag=true, all=true;
        while (flag) {
            flag=false; all=true;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if(gridnew[i][j]!=2) gridnew[i][j]=grid[i][j];
                    if (grid[i][j] ==2){
                        if (i > 0 && grid[i - 1][j] == 1){gridnew[i-1][j] = 2; flag=true;}
                        if (j > 0 && grid[i][j - 1] == 1){gridnew[i][j-1] = 2; flag=true;}
                        if (i < M - 1 && grid[i + 1][j] == 1){gridnew[i+1][j] = 2; flag=true;}
                        if (j < N - 1 && grid[i][j + 1] == 1){gridnew[i][j+1] = 2; flag=true;}
                    }
                    if(gridnew[i][j]==1) all=false;
                }
            }
            int[][] temp=grid; grid=gridnew; gridnew=temp;
            PrintUtils.print2DIntArray(gridnew);
            System.out.println("count+"+count+", all="+all);
            count++;
        }
        PrintUtils.print2DIntArray(grid);
        return  all?count-1:-1;
    }
}
