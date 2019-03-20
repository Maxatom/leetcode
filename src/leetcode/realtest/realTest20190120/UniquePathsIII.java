package leetcode.realtest.realTest20190120;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Shibing
 * @since 2019-01-22 15:22:47
 **/
public class UniquePathsIII {
    public static void main(String[] args) {
        UniquePathsIII pathsIII=new UniquePathsIII();
        int[][] grid=new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        grid=new int[][]{{1,0,0,0},{0,0,0,0},{0,0,0,2}};
        grid=new int[][]{{0,1},{2,0}};
        grid=new int[][]{{0}};
        grid=new int[][]{{-1,1},{2,0}};
        grid=new int[][]{{1,0},{2,0}};
        grid=new int[][]{{1,-1,0},{0,0,0},{0,0,2}};
        grid=new int[][]{{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,2}};
        grid=new int[][]{{1,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,2,-1}};
        grid=new int[6][5]; grid[0][0]=1; grid[grid.length-1][grid[0].length-1]=2;
//        System.out.println(pathsIII.uniquePathsIII(grid));

        long start=System.currentTimeMillis();
        System.out.println(pathsIII.uniquePathsIII2(grid)+", map.size="+pathsIII.map.size()+", time="+(System.currentTimeMillis()-start));

        PrintUtils.print2DIntArray(grid);
        start=System.currentTimeMillis();
        System.out.println(pathsIII.uniquePathsIII1(grid)+", time="+(System.currentTimeMillis()-start));
    }

    class State{
        int x;
        int y;
        int state;

        public State(int x, int y, int state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state1 = (State) o;
            return x == state1.x &&
                    y == state1.y &&
                    state == state1.state;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, state);
        }
    }
    Map<State, Integer> map;
    int[][] ds=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public int uniquePathsIII2(int[][] grid) {
        int M=grid.length;
        int N=grid[0].length;
        if(M==1&&N==1&&grid[0][0]==2) return 1;
        int sx=0, sy=0;
        int full=0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(grid[i][j]==1) {
                    sx=i; sy=j;
                }
                if(grid[i][j]!=-1) full |= (1<<(i*N+j));
            }
        }
        map=new HashMap<>();
        return dp(sx, sy, full, grid);
    }

    //slower than brute force
    //Let dp(r, c, todo) be the number of paths starting from where we are (r, c), and given that todo is the set of empty squares we've yet to walk on.
    public Integer dp(int r, int c, int todo, int[][] grid){
        int M=grid.length, N=grid[0].length;
        if(r<0 || r>=M || c<0 || c>=N || grid[r][c]==-1 || (todo & (1<<(r*N+c)))==0) return 0;
        todo ^= (1<<(r*N+c));
        State key=new State(r,c,todo);
        if(map.containsKey(key)) return map.get(key);
        if(grid[r][c]==2) {
//            if(todo==0) PrintUtils.print2DIntArray(grid);
            return todo==0?1:0;
        }
//        grid[r][c]= StringUtils.countOccurrencesOf(Integer.toBinaryString(todo),"0");
        int routes=0;
        for (int i = 0; i < ds.length; i++) {
            int x=r+ds[i][0], y=c+ds[i][1];
            routes+=dp(x, y, todo, grid);
        }
//        grid[r][c]= 0;
        map.put(key, routes);
        return routes;
    }


    //Optimizing code
     public int uniquePathsIII1(int[][] grid) {
        int M=grid.length;
        int N=grid[0].length;
        if(M==1&&N==1&&grid[0][0]==2) return 1;
        int num=0;
        int x=0,y=0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(grid[i][j]==2) {x=i; y=j;}
                else if(grid[i][j]==0) num++;
            }
        }
        grid[x][y]=0;
        return dfs1(grid, x, y, num+1, 1);
    }

    public int dfs1(int[][] grid, int x, int y, int num, int lastCell){
        if(x<0||x>=grid.length||y<0||y>=grid[0].length) return 0;
        if(grid[x][y]==1&&num==0) {
//            PrintUtils.print2DIntArray(grid);
            return 1;
        }
        if(grid[x][y]!=0) return 0;

        grid[x][y]=++lastCell;
        int d1, d2, d3, d4;
        d1 = dfs1(grid, x - 1, y, num-1,lastCell);
        d2 = dfs1(grid, x + 1, y,num-1,lastCell);
        d3 = dfs1(grid, x, y - 1, num-1,lastCell);
        d4 = dfs1(grid, x, y + 1,num-1,lastCell);
        grid[x][y]=0;
        return d1+d2+d3+d4;
    }



    public int uniquePathsIII(int[][] grid) {
        int M=grid.length;
        int N=grid[0].length;
        if(M==1&&N==1&&grid[0][0]==2) return 1;
        int num=0;
        int[] start=new int[2];
        int[] end=new int[2];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(grid[i][j]==1) {start[0]=i; start[1]=j;}
                else if(grid[i][j]==2) {end[0]=i; end[1]=j;}
                else if(grid[i][j]==0) num++;
            }
        }
        return dfs(grid, end, M, N, num);
    }


    //we start from the end square, the number of paths is the numbers of paths
    // to the second-to-end squares those near the end square, ans so on
    //backtrace
    //brute force, recursive, O(3^N)
    public int dfs(int[][] grid, int[] end, int M, int N, int num){
        int d1=0, d2=0, d3=0, d4=0;
        int x=end[0], y=end[1];
        boolean reach=false, hasNext=false;
        //up
        if(x>0){
            if(grid[x-1][y]==0) {
                hasNext=true;
                grid[x - 1][y] = grid[x][y]+1;
                d1 = dfs(grid, new int[]{x - 1, y}, M, N, num-1);
                grid[x - 1][y] = 0;
            }else if(grid[x-1][y]==1) reach=true;
        }
        //down
        if(x<M-1){
            if(grid[x+1][y]==0) {
                hasNext=true;
                grid[x + 1][y] = grid[x][y]+1;
                d2 = dfs(grid, new int[]{x + 1, y}, M, N, num-1);
                grid[x + 1][y] = 0;
            }else if(grid[x+1][y]==1) reach=true;
        }
        //left
        if(y>0){
            if(grid[x][y-1]==0) {
                hasNext=true;
                grid[x][y - 1] = grid[x][y]+1;
                d3 = dfs(grid, new int[]{x, y - 1}, M, N, num-1);
                grid[x][y - 1] = 0;
            }else if (grid[x][y-1]==1) reach=true;
        }
        //right
        if(y<N-1){
            if(grid[x][y+1]==0) {
                hasNext=true;
                grid[x][y + 1] =grid[x][y]+1;
                d4 = dfs(grid, new int[]{x, y + 1}, M, N, num-1);
                grid[x][y + 1] = 0;
            }else if(grid[x][y+1]==1) reach=true;
        }
        if(!hasNext&&!reach) return 0;
        if(!hasNext&&reach){
            if(num==0) {
//                PrintUtils.print2DIntArray(grid);
                return 1;
            }
            else return 0;
        }
        return d1+d2+d3+d4;
    }
}
