package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.*;

/**
 * @author Shibing
 * @since 2019-05-28 16:17:27
 **/
public class LargestPlusSign {
    public static void main(String[] args) {
        LargestPlusSign sign=new LargestPlusSign();
        int N = 5; int[][] mines = {{4, 2}};
//        N = 2; mines = new int[][]{};
//        N = 1; mines = new int[][]{{0, 0}};
//        N=3; mines=new int[][]{{0,0}};
        System.out.println(sign.orderOfLargestPlusSign(N, mines));
        System.out.println(sign.orderOfLargestPlusSign1(N, mines));
    }
    //new solution
//    public int orderOfLargestPlusSign2(int N, int[][] mines) {
//        int[][] grid=new int[N][N];
//        for(int[] m:mines){
//            for (int i = 0; i < N; i++) {
//                grid[m[0]][i]=Math.min(grid[m[0]][i], -x);
//            }
//        }
//    }

    //dp
    public int orderOfLargestPlusSign1(int N, int[][] mines) {
        int[][] dp=new int[N][N];
        for(int[] p:mines) dp[p[0]][p[1]]=-1;
        int max=0, count=0;
        for (int i = 0; i < N; i++) {
            //up
            count=0;
            for (int j = 0; j < N; j++)
                dp[i][j]=count=dp[i][j]==-1?0:count+1;
            //down
            count=0;
            for (int j = N-1; j >= 0; j--)
                dp[i][j]=Math.min(dp[i][j], count=dp[i][j]==0?0:count+1);
        }
        for (int j = 0; j < N; j++) {
            //right
            count=0;
            for (int i = N-1; i >=0 ; i--)
                dp[i][j]=Math.min(dp[i][j], count=dp[i][j]==0?0:count+1);
            //left
            count=0;
            for (int i = 0; i < N; i++) {
                dp[i][j]=Math.min(dp[i][j], count=dp[i][j]==0?0:count+1);
                max=Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    //TLE
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int M=N/2+(N&1), max=0;
        Set<Integer> set=new HashSet<>();
        for(int[] p:mines) set.add(p[0]*1000+p[1]);
        Queue<int[]> queue=new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!set.contains(i*1000+j)) queue.add(new int[]{i, j, 1});
            }
        }
        int[] x;
        while ((x=queue.poll())!=null){
            max=Math.max(max, x[2]);
            if(isValid(N, x[0], x[1], x[2]+1, set)) {
                queue.add(new int[]{x[0], x[1], x[2]+1});
            }
        }
        return max;
    }
    public boolean isValid(int N, int i, int j, int k, Set<Integer> set){
        int iu=i-k+1, id=i+k-1, ju=j-k+1, jd=j+k-1;
        return iu>=0 && !set.contains(iu*1000+j) && id<N && !set.contains(id*1000+j) && ju>=0 && !set.contains(i*1000+ju) && jd<N && !set.contains(i*1000+jd);
    }
}
