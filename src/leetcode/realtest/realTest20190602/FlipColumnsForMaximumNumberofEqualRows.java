package leetcode.realtest.realTest20190602;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/6/2 11:39
 */
public class FlipColumnsForMaximumNumberofEqualRows {
    public static void main(String[] args) {
        FlipColumnsForMaximumNumberofEqualRows rows=new FlipColumnsForMaximumNumberofEqualRows();
        int[][] matrix= new int[][]{{0,1},{1,1}};
//        matrix=new int[][] {{0,1},{1,0}};
//        matrix=new int[][] {{0,0,0},{0,0,1},{1,1,0}};
        System.out.println(rows.maxEqualRowsAfterFlips(matrix));
        System.out.println(rows.maxEqualRowsAfterFlips1(matrix));
    }
    //find maximum number of rows that have identical relative ordering with respect to top elements
    public int maxEqualRowsAfterFlips1(int[][] matrix) {
        int m=matrix.length, n=matrix[0].length, max=0;
        Map<String, Integer> map=new HashMap<>();
        for (int i = 0; i < m; i++) {
            StringBuilder sb=new StringBuilder();
            for (int j = 0; j < n; j++)
                sb.append(matrix[i][j]==matrix[i][0]?1:0);
            String key=sb.toString();
            map.put(key, map.getOrDefault(key, 0)+1);
            max=Math.max(max, map.get(key));
        }
        System.out.println(map.toString());
        return max;
    }
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m=matrix.length, n=matrix[0].length, max=0;
        int[] flip=new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                flip[j]=1-matrix[i][j];
            int temp=0;
            for (int j = 0; j < m; j++) {
                if(Arrays.equals(matrix[i], matrix[j]) || Arrays.equals(matrix[j], flip)) temp++;
            }
            max=Math.max(max, temp);
        }
        return max;
    }
}
