package leetcode.realtest.realTest20190602;

/**
 * @author shibing
 * @since 2019/6/2 11:39
 */
public class FlipColumnsForMaximumNumberofEqualRows {
    public static void main(String[] args) {

    }
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int flip=0, notflip=0, m=matrix.length, n=matrix[0].length;
        int[][] dp=new int[m][2];
        for (int i = 1; i < n; i++) {
            int[][] dpnew=new int[m][2];
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[j][i]==matrix[j][i-1]) dpnew[j][0]=0;
                else dpnew[j][0]=1;
            }

        }
        return 0;
    }
//    public int[] equal(int[][] matrix, int i){
//        int s1=0, s2=0;
//        for (int j = 0; j < matrix.length; j++) {
//            if(matrix[j][i]==matrix[j][i-1]) s1++;
//            else s2++;
//        }
//        if(s1==matrix.length) return 0;
//        if(s2==matrix.length) return 1;
//        re
//    }
}
