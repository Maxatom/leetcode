package leetcode.realtest.realTest20190602;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/6/2 11:19
 */
public class NumberofSubmatricesThatSumtoTarget {
    public static void main(String[] args) {
        NumberofSubmatricesThatSumtoTarget matrices=new NumberofSubmatricesThatSumtoTarget();
        int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}}; int target = 0;
        matrix = new int[][]{{1,-1},{-1,1}}; target = 0;
//        System.out.println(matrices.numSubmatrixSumTarget(matrix, target));
        System.out.println(matrices.numSubmatrixSumTarget1(matrix, target));
        System.out.println(matrices.numSubmatrixSumTarget2(matrix, target));
    }

    //O(N^3) rowwise
    public int numSubmatrixSumTarget2(int[][] matrix, int target) {
        int m=matrix.length, n=matrix[0].length, cnt=0;
        Map<Integer, Integer> map=new HashMap<>();
        for (int[] row:matrix)
            for (int j = 1; j < n; j++)
                row[j]+=row[j-1];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                map.clear();
                map.put(0,1);
                int cur=0;
                for (int k = 0; k < m; k++) {
                    cur+=matrix[k][j]-(i>0?matrix[k][i-1]:0);
                    cnt+=map.getOrDefault(cur-target, 0);
                    map.put(cur, map.getOrDefault(cur, 0)+1);
                }
            }
        }
        return cnt;
    }
    // column wise
    //O(N^3) find the Subarray with target sum
    public int numSubmatrixSumTarget1(int[][] matrix, int target) {
        int m=matrix.length, n=matrix[0].length, cnt=0;
        int[] sum=new int[n];
        final Map<Integer, Integer> map=new HashMap<>();
        for (int i = 0; i < m; i++) {
            Arrays.fill(sum, 0);
            for (int t = i; t < m; t++) {
                for (int j = 0; j < n; j++) sum[j]+=matrix[t][j];
                map.clear();
                map.put(0,1);
                int cur=0;
                for (int j = 0; j < n; j++) {
                    cur+=sum[j];
                    cnt+=map.getOrDefault(cur-target, 0);
                    map.put(cur, map.getOrDefault(cur, 0)+1);
                }
            }
        }
        return cnt;
    }
    //brute force  O(N^4)
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m=matrix.length, n=matrix[0].length, cnt=0;
        int[][] preSum=new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i+1][j+1]=matrix[i][j]+preSum[i+1][j]+preSum[i][j+1]-preSum[i][j];
                for (int k = i; k >=0 ; k--)
                    for (int l = j; l >=0 ; l--)
                        if(preSum[i+1][j+1]-preSum[i+1][l]-preSum[k][j+1]+preSum[k][l]==target) cnt++;
            }
        }
        return cnt;
    }
}
