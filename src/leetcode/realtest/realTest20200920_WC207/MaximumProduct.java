package leetcode.realtest.realTest20200920_WC207;

import utils.PrintUtils;

public class MaximumProduct {
    public static void main(String[] args) {
        MaximumProduct prod = new MaximumProduct();
        int[][] grid = {{-1,-2,-3}, {-2,-3,-3}, {-3,-3,-2}};
        grid = new int[][]{{1,-2,1}, {1,-2,1}, {3,-4,1}};
        grid = new int[][]{{1,-1,2,1,-1,0,0,4,3,2,0,-2,-2},{-2,3,3,-1,-1,0,0,-2,4,-3,3,0,0},{-4,-1,-1,-2,2,-1,-2,-2,0,3,-1,-4,1},{-3,4,-3,0,-3,1,-3,1,4,4,-4,-4,-2},{3,-3,1,0,-1,-4,-4,-4,3,2,2,3,3},{2,-1,-1,-4,-3,-3,4,2,3,4,4,-4,0},{4,-1,2,-3,-1,-1,-3,-4,4,4,4,-3,-1},{-3,-4,4,-2,-1,2,3,-1,2,3,4,4,-4},{-3,-1,-2,1,1,-1,-3,-4,-3,1,-3,3,-4},{2,4,4,4,-3,-3,1,-1,3,4,-1,1,4},{2,-2,0,4,-1,0,-2,4,-4,0,0,2,-3},{1,1,-3,0,-4,-4,-4,-4,0,-1,-4,-1,0},{3,-1,-3,-3,-3,-2,-1,4,-1,-2,4,2,3}};
        PrintUtils.print2DIntArray(grid);
        System.out.println("-------------");
        System.out.println(prod.maxProductPath(grid));
    }
    int MOD=(int)1e9+7;
    public int maxProductPath(int[][] grid) {
        int m=grid.length, n=grid[0].length;
        long[][] pos = new long[m][n];
        long[][] neg = new long[m][n];
        pos[0][0]=grid[0][0];
        neg[0][0]=grid[0][0];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==0){
                    pos[i][j]=neg[i][j]=0;
                    continue;
                }
                if(i==0&&j==0) continue;
                else if(i==0){
                    if(grid[i][j]>0){
                        pos[i][j] = pos[i][j-1]*grid[i][j];
                        neg[i][j] = neg[i][j-1]*grid[i][j];
                    }else{
                        pos[i][j] = neg[i][j-1]*grid[i][j];
                        neg[i][j] = pos[i][j-1]*grid[i][j];
                    }

                }else if(j==0){
                    if(grid[i][j]>0){
                        pos[i][j] = pos[i-1][j]*grid[i][j];
                        neg[i][j] = neg[i-1][j]*grid[i][j];
                    }else{
                        pos[i][j] = neg[i-1][j]*grid[i][j];
                        neg[i][j] = pos[i-1][j]*grid[i][j];
                    }
                }else{
                    if(grid[i][j]>0){
                        pos[i][j] = Math.max(pos[i-1][j], pos[i][j-1])*grid[i][j];
                        neg[i][j] = Math.min(neg[i-1][j], neg[i][j-1])*grid[i][j];
                    }else{
                        pos[i][j] = Math.min(neg[i-1][j], neg[i][j-1])*grid[i][j];
                        neg[i][j] = Math.max(pos[i-1][j], pos[i][j-1])*grid[i][j];
                    }
                }
//                pos[i][j]%=MOD;
//                neg[i][j]%=MOD;
            }
        }
//        PrintUtils.print2DIntArray(pos);
//        PrintUtils.print2DIntArray(neg);
        return (int)(pos[m-1][n-1]<0? -1 : pos[m-1][n-1]%MOD);
    }
}
