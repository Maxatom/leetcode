package leetcode.realtest.realTest20190428;

import utils.PrintUtils;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Shibing
 * @since 2019-04-28 17:15:56
 **/
public class ColoringABorder {
    public static void main(String[] args) {
        ColoringABorder order=new ColoringABorder();
        int[][] grid = new int[][]{{1,1},{1,2}}; int  r0 = 0, c0 = 0, color = 3;
        grid = new int[][]{{1,2,2},{2,3,2}}; r0 = 0; c0 = 1; color = 3;
//        grid = new int[][]{{1,1,1},{1,1,1},{1,1,1}}; r0 = 1; c0 = 1; color = 2;
        grid = new int[][]{{1,1,1},{1,2,1},{1,1,1}}; r0 = 0; c0 = 1; color = 3;
        grid=new int[][]{{1,2,1,2,1,2},{2,2,2,2,1,2},{1,2,2,2,1,2}};r0= 1; c0= 3; color= 1;
//        PrintUtils.print2DIntArray(order.colorBorder(grid, r0, c0, color));
//        System.out.println("********* **********");
        PrintUtils.print2DIntArray(order.colorBorder1(grid, r0, c0, color));
    }

    //find border then color
    public int[][] colorBorder1(int[][] grid, int r0, int c0, int color) {
        int M=grid.length, N=grid[0].length;
        boolean[][] visited=new boolean[M][N];
        Queue<int[]> squares=new ArrayDeque<>();
        Queue<int[]> border=new ArrayDeque<>();
        squares.add(new int[]{r0,c0});
        int[] cell;
        while ((cell=squares.poll())!=null){
            visited[cell[0]][cell[1]]=true;
            boolean isBorder=false;
            for(int[] d:dire){
                int x=cell[0]+d[0], y=cell[1]+d[1];
                if(x<0 || x>=M || y<0 || y>=N || grid[x][y]!=grid[r0][c0]) isBorder=true;
                else if(!visited[x][y]) squares.add(new int[]{x,y});
            }
            if(isBorder) border.add(cell);
        }
        while ((cell=border.poll())!=null)
            grid[cell[0]][cell[1]]=color;
        return grid;
    }


    //BFS
    int[][] dire=new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        int M=grid.length, N=grid[0].length, curColor=grid[r0][c0];
        boolean[][] visited=new boolean[M][N];
        int cache=color; color=1001;
        Queue<int[]> squares=new ArrayDeque<>();
        squares.add(new int[]{r0,c0});
        int[] cell;
        while ((cell=squares.poll())!=null){
            visited[cell[0]][cell[1]]=true;
            boolean isborder=false;
            for(int[] d:dire){
                int x=cell[0]+d[0], y=cell[1]+d[1];
                if(x<0 || x>=M || y<0 || y>=N || grid[x][y]!=color && grid[x][y]!=curColor) isborder=true;
                else if(!visited[x][y]) squares.add(new int[]{x,y});
            }
            if(isborder)
                grid[cell[0]][cell[1]]=color;
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(grid[i][j]==color) grid[i][j]=cache;
            }
        }
        return grid;
    }
}
