package leetcode.realtest.realTest20191215_WC167;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

public class ShortestPathinaGrid {
    public static void main(String[] args) {
        ShortestPathinaGrid path=new ShortestPathinaGrid();
        int[][] grid = {{0,0,0}, {1,1,0}, {0,0,0}, {0,1,1}, {0,0,0}};int  k = 1;
        grid = new int[][]{{0,1,1}, {1,1,1}, {1,0,0}}; k = 1;
        grid=new int[][]{{0}}; k=3;
        grid=new int[][]{{0,0,1,0,0,0,0,1,0,1,1,0,0,1,1},{0,0,0,1,1,0,0,1,1,0,1,0,0,0,1},{1,1,0,0,0,0,0,1,0,1,0,0,1,0,0},{1,0,1,1,1,1,0,0,1,1,0,1,0,0,1},{1,0,0,0,1,1,0,1,1,0,0,1,1,1,1},{0,0,0,1,1,1,0,1,1,0,0,1,1,1,1},{0,0,0,1,0,1,0,0,0,0,1,1,0,1,1},{1,0,0,1,1,1,1,1,1,0,0,0,1,1,0},{0,0,1,0,0,1,1,1,1,1,0,1,0,0,0},{0,0,0,1,1,0,0,1,1,1,1,1,1,0,0},{0,0,0,0,1,1,1,0,0,1,1,1,0,1,0}};
        k=27;
        System.out.println(path.shortestPath(grid, k));
    }
    public int shortestPath(int[][] grid, int k) {
        int m=grid.length, n=grid[0].length;
        int res=dfs(grid, k, new int[]{0,0}, new boolean[m][n]);
        return res>=MAX?-1:res-1;
    }

    Map<String,Integer> map=new HashMap<>();
    int MAX=40*40;
    int[][] dire={{-1,0},{0,1},{1,0},{0,-1}};
    int dfs(int[][] grid, int k, int[] p, boolean[][] visited){
        int m=grid.length, n=grid[0].length;
        if(p[0]>=m || p[0]<0 || p[1]>=n || p[1]<0 || visited[p[0]][p[1]]) return MAX;
        if(p[0]==m-1 && p[1]==n-1) return 1;
        if(grid[p[0]][p[1]]==1){
            if(k<=0) return MAX;
            k--;
        }
        visited[p[0]][p[1]] =true;
        int min=MAX;
        for(int[] dr:dire){
            int x=p[0]+dr[0], y=p[1]+dr[1];
            int res=dfs(grid, k, new int[]{x, y}, visited);
            min=Math.min(min, res+1);
        }
        visited[p[0]][p[1]]= false;
        return min;
    }

}
