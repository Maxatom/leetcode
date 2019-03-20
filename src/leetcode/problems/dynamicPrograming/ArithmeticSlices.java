package leetcode.problems.dynamicPrograming;

/**
 * @author Shibing
 * @since 2019-01-30 16:03:53
 **/
public class ArithmeticSlices {
    public static void main(String[] args) {
        ArithmeticSlices slices=new ArithmeticSlices();
        int[] A=new int[]{1, 2, 3, 4};
        A=new int[]{1, 3, 5, 7, 9};
        A=new int[]{1, 3, 5, 0, 3, 6};
//        A=new int[0];
        System.out.println(slices.numberOfArithmeticSlices(A));
        System.out.println(slices.numberOfArithmeticSlices1(A));

    }
    //dp O(N)
    //sum=S(dp(i))  , dp(i) is the length of arithmetic slice end with the ith element
    public int numberOfArithmeticSlices1(int[] A) {
        if(A==null||A.length<3) return 0;
        int num=0, sum=0;
        for (int i = 2; i < A.length; i++)
            if(A[i]-A[i-1]==A[i-1]-A[i-2]) sum+=++num;
            else num=0;
        return sum;
    }
    //Math O(N)
    public int numberOfArithmeticSlices(int[] A) {
        if(A==null||A.length<3) return 0;
        int d=A[1]-A[0];
        int num=2;
        int count=0;
        for (int i = 2; i < A.length; i++) {
            if(A[i]-A[i-1]==d) num++;
            else {
                count+=(num-1)*(num-2)/2;
                d=A[i]-A[i-1];
                num=2;
            }
        }
        count+=(num-1)*(num-2)/2;
        return count;
    }
}
