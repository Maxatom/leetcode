package leetcode.TopInterview;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Shibing
 * @since 2019-06-11 14:36:16
 **/
public class SetMatrixZeroes {
    public static void main(String[] args) {

    }
    public void setZeroes1(int[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0) return;
        int m=matrix.length, n=matrix[0].length, firstColumn=1;
        for (int i = 0; i < m; i++) {
            if(matrix[i][0]==0) firstColumn=0;
            for (int j = 1; j < n; j++) {
                if(matrix[i][j]==0) {
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][0]==0 || j==0 && firstColumn==0 || matrix[0][j]==0) matrix[i][j]=0;
            }
        }
    }
    public void setZeroes(int[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0) return;
        int m=matrix.length, n=matrix[0].length;
        Set<Integer> rows=new HashSet<>();
        Set<Integer> cols=new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if(rows.contains(i) || cols.contains(j)) matrix[i][j]=0;
    }
}
