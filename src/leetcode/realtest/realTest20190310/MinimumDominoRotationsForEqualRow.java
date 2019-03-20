package leetcode.realtest.realTest20190310;

/**
 * @author shibing
 * @since 2019/3/10 17:26
 */
public class MinimumDominoRotationsForEqualRow {
    public static void main(String[] args) {
        MinimumDominoRotationsForEqualRow domino=new MinimumDominoRotationsForEqualRow();
        int[] A = new int[]{2,1,2,4,2,2}, B = new int[]{5,2,6,2,3,2};
//        A = new int[]{3,5,1,2,3}; B = new int[]{3,6,3,3,4};
//        A= new int[]{1,1,1,1,1,1,1,1}; B=new int[] {1,1,1,1,1,1,1,1};
        A=new int[]{2,2}; B=new int[]{3,3};
        A=new int[]{2,1, 3}; B=new int[]{3,3, 6};
        A=new int[]{2,2,3,2}; B=new int[]{3,3, 2, 3};
        System.out.println(domino.minDominoRotations(A,B));
    }
    public int minDominoRotations(int[] A, int[] B) {
        int[] count=new int[7];
        for (int i = 0; i < A.length; i++) {
            count[A[i]]++;
            if(A[i]!=B[i]) count[B[i]]++;
        }
        int min=A.length;
        for (int i = 1; i < 6; i++)
            if(count[i]==A.length) {
                int na = 0, nb = 0;
                for (int j = 0; j < A.length; j++) {
                    if (A[j] != i) na++;
                    if (B[j] != i) nb++;
                }
                min=Math.min(min, Math.min(na,nb));
            }
        return min==A.length?-1:min;
    }
}
