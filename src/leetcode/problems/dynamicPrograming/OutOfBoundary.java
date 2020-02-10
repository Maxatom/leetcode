package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class OutOfBoundary {
    public static void main(String[] args) {
        OutOfBoundary outOfBoundary = new OutOfBoundary();
        int m = 7, n = 6, N = 13, i = 0, j = 2;
//         m= 2; n = 2; N = 2; i = 0; j = 0;
        m = 1;
        n = 3;
        N = 3;
        i = 0;
        j = 1;
        System.out.println(outOfBoundary.findPaths(m, n, N, i, j));
    }

    int[][] dire={{-1,0},{0,1},{1,0},{0,-1}};
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][][] dp=new int[m][n][N+1];
        int sum=0, M=1000000007;
        dp[i][j][0]=1;
        for(int p=1; p<N; p++){
            for (int k = 0; k < m; k++) {
                for (int l = 0; l < n; l++) {
                    for(int[] dr:dire){
                        int x=k+dr[0], y=l+dr[1];
                        if(x<0 || x>=m || y<0 || y>=n) continue;
                        dp[k][l][p]+=dp[x][y][p-1];
                        dp[k][l][p]%=M;
                    }
                }
            }
        }
        printArr(dp);
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < N; l++) {
                sum = (sum + dp[0][k][l])%M;
                sum = (sum + dp[m-1][k][l])%M;
            }
        }
        for (int k = 0; k < m; k++) {
            for (int l = 0; l < N; l++) {
                sum = (sum + dp[k][0][l])%M;
                sum = (sum + dp[k][n-1][l])%M;
            }
        }
        return sum;
    }
    void printArr(int[][][] arr){
        int m=arr.length, n=arr[0].length, k=arr[0][0].length;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(Arrays.toString(arr[i][j]));
                if(j<n-1) System.out.print(",");
            }
            System.out.println();
        }
    }
}
