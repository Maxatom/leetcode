package leetcode.realtest.realTest20190602;

/**
 * @author shibing
 * @since 2019/6/2 11:19
 */
public class NumberofSubmatricesThatSumtoTarget {
    public static void main(String[] args) {
        NumberofSubmatricesThatSumtoTarget matrices=new NumberofSubmatricesThatSumtoTarget();
        int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}}; int target = 0;
        matrix = new int[][]{{1,-1},{-1,1}}; target = 0;
        System.out.println(matrices.numSubmatrixSumTarget(matrix, target));
    }

    //O(N^3) find the Subarray with target sum
    public int numSubmatrixSumTarget1(int[][] matrix, int target) {
        int m=matrix.length, n=matrix[0].length, cnt=0;
        int[][] preSum=new int[m+1][n+1];
        return 0;
    }
    //brute force  O(N^4)
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m=matrix.length, n=matrix[0].length, cnt=0;
        int[][] preSum=new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i+1][j+1]=matrix[i][j]+preSum[i+1][j]+preSum[i][j+1]-preSum[i][j];
                for (int k = i; k >=0 ; k--) {
                    for (int l = j; l >=0 ; l--) {
                        if(preSum[i+1][j+1]-preSum[i+1][l]-preSum[k][j+1]+preSum[k][l]==target) cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
