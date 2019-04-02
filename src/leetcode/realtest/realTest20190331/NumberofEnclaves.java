package leetcode.realtest.realTest20190331;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/3/31 11:36
 */
public class NumberofEnclaves {
    public static void main(String[] args) {
        NumberofEnclaves enclaves=new NumberofEnclaves();
        int[][] A=new int[][]{{0,0,1,1,0,0,0,0,0,1},{1,1,0,1,0,0,1,0,0,1},{1,1,0,0,1,0,1,1,0,0},{1,0,0,1,0,0,0,0,0,1},{0,0,1,1,1,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{1,0,1,0,1,1,1,0,1,0},{0,1,1,1,0,0,1,0,0,1},{0,1,1,0,0,1,0,1,1,0},{1,0,1,1,0,0,1,1,0,0},{1,0,1,0,1,1,1,0,0,1}};
        System.out.println(enclaves.numEnclaves(A));
//        PrintUtils.print2DIntArray(A);
    }
    public int numEnclaves(int[][] A) {
        int M=A.length, N=A[0].length;
        for (int i = 0; i < M; i++) {
                recursive(A, i, 0);
                recursive(A, i, N-1);
        }
        for (int j = 0; j < N; j++) {
                recursive(A, 0, j);
                recursive(A, M-1, j);
        }
        int cnt=0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(A[i][j]==1) cnt++;
            }
        }
        return cnt;
    }

    public void recursive(int[][] A, int i, int j){
        if(i<0 || i==A.length || j<0 || j==A[0].length || A[i][j]!=1) return;
        A[i][j] = 2;
        recursive(A, i-1, j);
        recursive(A, i+1, j);
        recursive(A, i, j-1);
        recursive(A, i, j+1);
    }
}
