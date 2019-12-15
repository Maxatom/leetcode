package leetcode.Mock.date20191214;

import utils.PrintUtils;

public class Q2 {
    public static void main(String[] args) {
        Q2 q2=new Q2();
        int[][] board={{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
//        board=new int[][]{{-1,-1},{4,-1}};
        board=new int[][]{{-1,7,-1},{-1,6,9},{-1,-1,2}};
        board=new int[][]{{-1,60,32,-1,-1,-1,59,-1},{34,1,15,9,13,25,63,26},{-1,-1,-1,-1,29,-1,-1,-1},{-1,-1,-1,27,-1,-1,-1,5},{6,59,-1,2,40,13,-1,-1},{-1,44,20,-1,-1,-1,58,-1},{-1,-1,9,-1,-1,23,-1,-1},{-1,-1,-1,46,27,6,-1,-1}};
        PrintUtils.print2DIntArray(board);
        System.out.println(q2.snakesAndLadders(board));
    }
    public int snakesAndLadders(int[][] board) {
        int m=board.length, n=board[0].length;
        int[] road=new int[m*n];
        int c=0;
        for(int i=m-1; i>=0; i--){
            if((i-m+1)%2==0){
                for(int j=0; j<n; j++){
                    road[c++]=board[i][j];
                }
            }else{
                for(int j=n-1; j>=0; j--){
                    road[c++]=board[i][j];
                }
            }
        }
        PrintUtils.printArray(road);
        int[] cache=new int[c];
        int res=dfs(road, 0, cache, new boolean[c]);
        PrintUtils.printArray(cache);
        return res<0?-1:res-1;
    }
    int dfs(int[] road, int p, int[] cache, boolean[] seen){
        if(p>=road.length) return -1;
        if(p==road.length-1) return 1;
        if(cache[p]!=0) return cache[p];
        if(seen[p]) return -1;
        seen[p]=true;
        int origp=p;
        if(road[p]!=-1){
            p=road[p]-1;
            if(p==road.length-1) {
                seen[origp]=false;
                cache[origp]=1;
                return 1;
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=1;i<=6;i++){
            int res=dfs(road,p+i,cache,seen);
            if(res!=-1)
                min=Math.min(min, res);
        }
        seen[origp]=false;
        return cache[origp] = min==Integer.MAX_VALUE?-1:min+1;

    }
}
