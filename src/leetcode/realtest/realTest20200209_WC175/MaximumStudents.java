package leetcode.realtest.realTest20200209_WC175;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumStudents {
    public static void main(String[] args) {
        MaximumStudents stu=new MaximumStudents();
        char[][] seats = {{'#','.','#','#','.','#'}, {'.','#','#','#','#','.'}, {'#','.','#','#','.','#'}};
//        seats=new char[][]{{'.','.','.','.','#','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','#','.'},{'.','.','.','.','.','.','.','.'},{'.','.','#','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','#','.','.','#','.'}};
//        seats= new char[][]{{'.','#'},{'#','.'}};
//        seats=new char[][]{{'.','#'},{'#','#'},{'#','.'},{'#','#'},{'.','#'}};
//        seats=new char[][]{{'#','.','.','.','#'},{'.','#','.','#','.'},{'.','.','#','.','.'},{'.','#','.','#','.'},{'#','.','.','.','#'}};
        System.out.println(stu.maxStudents(seats));
        System.out.println(stu.maxStudents1(seats));

    }

    //bitmasks
    public int maxStudents1(char[][] seats) {
        int m=seats.length, n=seats[0].length;
        int[] validatiy=new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                validatiy[i]= (validatiy[i]<<1) + (seats[i][j]=='#'?1:0);
            }
        }

        int max=0;
        int[][] dp=new int[m][1<<n];
        for (int j = 0; j < (1<<n); j++) {
            if((j&validatiy[0])==0 && (j&(j<<1))==0)
                dp[0][j] = Math.max(dp[0][j],  Integer.bitCount(j));
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < (1<<n); j++) {
                if((j&validatiy[i])==0 && (j&(j<<1))==0){
                    int bc=Integer.bitCount(j);
                    for (int k = 0; k < (1<<n); k++) {
                        if((k&validatiy[i-1])==0 && (j&(k<<1))==0 && (j&(k>>1))==0)
                            dp[i][j]=Math.max(dp[i][j], dp[i-1][k]+bc);
                    }
                }

                if(i==m-1) max=Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
    //top down dp
    public int maxStudents(char[][] seats) {
        int m=seats.length, n=seats[0].length;
        int[][][] cache=new int[m][n][1<<(n+1)];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(cache[i][j],-1);
            }
        }
        int res=dfs(seats, 0, 0, cache,0);
        return res;
    }
    int dfs(char[][] seats, int x, int y, int[][][] cache, int state){
        int m=seats.length, n=seats[0].length;
        if(x>=m) return 0;
        int bit=(1<<(n+1))-1;
        state = y==0?(state&(bit>>1)):state;
        if(cache[x][y][state]>=0) return cache[x][y][state];
        boolean empty=true;
        if(seats[x][y]=='#') empty = false;
        if(y-1>=0){
            if(x-1>=0 && (state&(1<<n))>0) empty=false;
            if((state&1)>0) empty=false;
        }
        if(y+1<n && x-1>=0 && (state&(1<<(n-2)))>0 ) empty=false;
        int ny=y==n-1?0:y+1, nx=y==n-1?x+1:x;
        int em=dfs(seats, nx, ny, cache, (state<<1)&bit);
        if(!empty) return em;
        int nem=1+dfs(seats, nx, ny, cache, ((state<<1)|1)&bit);
        return cache[x][y][state]=Math.max(em,nem);
    }

}
