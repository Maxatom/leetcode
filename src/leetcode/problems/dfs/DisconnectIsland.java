package leetcode.problems.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class DisconnectIsland {
    public static void main(String[] args) {
        DisconnectIsland island = new DisconnectIsland();
        int[][] grid = {{0,1,1,0},{0,1,1,0},{0,0,0,0}};
        System.out.println(island.minDays(grid));
    }
    public int minDays(int[][] grid) {
        int m=grid.length, n=grid[0].length;
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1) queue.add(new int[]{i,j});
            }
        }
        int total = queue.size();
        // System.out.printf("%d\n",queue.size());

        if(total<2) return total;
        int[] first = queue.peek();
        int cnt = dfs(grid, first[0], first[1]);
        // System.out.printf("%d,%d",cnt,total);
        if(cnt!=total) return 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            grid[cur[0]][cur[1]]=-1;
            int[] next = queue.peek();
            if(next==null) next=first;
            int res=dfs(grid, next[0], next[1]);
            System.out.printf("%d,%d,--%d,%d,%d\n",cur[0],cur[1],next[0],next[1],res);
            if(res!=total-1) return 1;
            recover(grid);
            grid[cur[0]][cur[1]]=1;
        }
        return 2;
    }
    int[][] dires = {{0,1},{1,0},{0,-1},{-1,0}};
    int dfs(int[][] grid, int i,int j){
        System.out.printf("%d,%d\n",i,j);
        int m=grid.length, n=grid[0].length;
        if(i<0 || i>=m || j<0 || j>=n || grid[i][j]!=1) return 0;
        grid[i][j]=-1;
        int sum=0;
        for(int[] dire:dires){
            int nx=i+dire[0], ny=j+dire[1];
            sum+=dfs(grid,nx,ny);
        }
        return sum+1;
    }
    void recover(int[][] grid){
        int m=grid.length, n=grid[0].length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==-1) grid[i][j]=1;
            }
        }
    }
}
