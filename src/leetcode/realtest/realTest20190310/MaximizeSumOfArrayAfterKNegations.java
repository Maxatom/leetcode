package leetcode.realtest.realTest20190310;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/3/10 10:34
 */
public class MaximizeSumOfArrayAfterKNegations {
    public static void main(String[] args) {
        MaximizeSumOfArrayAfterKNegations negations=new MaximizeSumOfArrayAfterKNegations();
        int[] A=new int[]{4,2,3}; int K=1; //5
//        A=new int[]{3,-1,0,2}; K=3;   //6
//        A=new int[]{2,-3,-1,5,-4}; K=2;  //13
        A=new int[]{3,4,5}; K =9;  //6
        A=new int[]{3,4,5}; K =8;  //12
        A=new int[]{-8,3,-5,-3,-5,-2}; K= 6; //22
        A=new int[]{-8,-3,-5,-3,-5,-2}; K= 6; //26
//        System.out.println(negations.largestSumAfterKNegations(A, K));
        System.out.println(negations.largestSumAfterKNegations1(A, K));
    }
    public int largestSumAfterKNegations1(int[] A, int K) {
        Arrays.sort(A);
        for (int i = 0; i < A.length && A[i]<0 && K>0; i++) {
            A[i] = -A[i]; K--;
        }
        int sum=Arrays.stream(A).sum();
        int min=Arrays.stream(A).min().getAsInt();
        return sum - ((K&1)==1?2*min:0);
    }

    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int sum=0;
        for (int i = 0; i < A.length; i++) {
            if(A[i]<0 ){
                if(K>0) {
                    A[i] = -A[i];
                    K--;
                }
            } else{
                if(K>0) {
                    if((K&1)==1) {
                        if(i>0 && A[i-1]<A[i]){
                            A[i-i]=-A[i-1];
                            sum-=2*A[i-1];
                        } else A[i]=-A[i];
                    }
                    K=0;
                }
            }
            sum+=A[i];
        }
        return sum;
    }
}
