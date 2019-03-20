package leetcode.realtest.realTest20190113;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/1/13 11:20
 */
public class LargestPerimeterTriangle {
    public static void main(String[] args) {
        LargestPerimeterTriangle triangle=new LargestPerimeterTriangle();
        int[] test=new int[]{2,1,2};
        test=new int[]{1,2,1};
        test=new int[]{3,2,3,4};
        test=new int[]{3,6,2,3};
        System.out.println(triangle.largestPerimeter(test));

    }
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length-1; i >=2 ; i--) {
            if(A[i-2]+A[i-1]>A[i]){
                return A[i-2]+A[i-1]+A[i];
            }
        }
        return 0;
    }
}
