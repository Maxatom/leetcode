package leetcode.realtest.realTest20190616;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/6/16 11:56
 */
public class ShortestPathinBinaryMatrix {
    public static void main(String[] args) {
        ShortestPathinBinaryMatrix matrix=new ShortestPathinBinaryMatrix();
        int[][] grid={{0,1},{1,0}};
        grid=new int[][]{{0,0,0},{1,1,0},{1,1,0}};
//       grid=new int[][]{{0,0,1,0,0,0,0},{0,1,0,0,0,0,1},{0,0,1,0,1,0,0},{0,0,0,1,1,1,0},{1,0,0,1,1,0,0},{1,1,1,1,1,0,1},{0,0,1,0,0,0,0}};
        System.out.println(matrix.shortestPathBinaryMatrix(grid));
    }

    int[][] dires={{-1,0}, {0, 1}, {1, 0}, {0,-1}, {-1,-1}, {-1,1},{1, 1}, {1, -1}};
    //dfs
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m=grid.length, n=grid[0].length;
        int res=dfs(grid, new boolean[grid.length][grid[0].length], 0, 0, 1);
        return res>=m*n?-1:res;
    }
    public int dfs(int[][] grid, boolean[][] used, int x, int y, int cnt){
        int m=grid.length, n=grid[0].length;
        if(x<0 || x>=m || y<0 || y>=n || grid[x][y]==1 || used[x][y]) return m*n+1;
        if(x==m-1 && y==n-1) return cnt;
        int min=m*n;
        used[x][y]=true;
        for(int[] dire:dires){
            min=Math.min(min, dfs(grid, used, x+dire[0], y+dire[1], cnt+1));
        }
        used[x][y]=false;
        return min;
    }
}
