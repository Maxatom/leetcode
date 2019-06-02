package leetcode.realtest.BiWeeklyTest20190601;

/**
 * @author shibing
 * @since 2019/6/1 22:48
 */
public class FixedPoint {
    public static void main(String[] args) {
        FixedPoint point=new FixedPoint();
        int[] A=new int[]{-10,-5,0,3,7};
        A=new int[]{0,2,5,8,17};
        A=new int[]{-10,-5,3,4,7,9};
        System.out.println(point.fixedPoint(A));
    }
    public int fixedPoint(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if(A[i]==i) return i;
        }
        return -1;
    }
}
