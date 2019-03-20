package leetcode.realtest.realTest20190303;

/**
 * @author shibing
 * @since 2019/3/3 10:33
 */
public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        MaxConsecutiveOnesIII ones=new MaxConsecutiveOnesIII();
        int[] A=new int[]{1,0,1,1,1,0}; int K=1; //5
//        A=new int[]{1,0,0,1,1,0,0,0,0,1,0}; K=2; //5
//        A=new int[]{0,0,0,1,1,0,0,0,0,1,0}; K=2; //4
//        A=new int[]{0,0,0}; K=2;
        A=new int[]{0}; K=0;
//        A=new int[]{0,1,1,1,0,1,1}; K=0; //3
//        A=new int[]{1,1,1,0,0,0,1,1,1,1,0}; K=2; //6
//        A=new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}; K=3; //10
//        A=new int[]{1,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1,1,0,1,1}; K= 8; //25
        System.out.println(ones.longestOnes(A,K));
        System.out.println(ones.longestOnes1(A,K));
    }


    //sliding window
    public int longestOnes1(int[] A, int K) {
        int j=0, res=0;
        for (int i = 0; i < A.length; i++) {
            if(A[i]==0) K--;
            while (K<0)
                if(A[j++]==0) K++;
            res=Math.max(res, i-j+1);
        }
        return res;
    }

    //space O(K)
    public int longestOnes(int[] A, int K) {
        int max=0, num0=0, len=K+1;
        int[] pos0=new int[len];
        for (int i = 0; i < A.length; i++) {
            if(A[i]==0){
                num0++;
                if(num0>len) max=Math.max(max, i-1-pos0[(num0-1)%len]);
                else if(num0==len) max=i;
                else max=i+1;
                pos0[(num0-1)%len]=i;
            }
            if(i==A.length-1) max=Math.max(max, i-pos0[(num0)%len]);
        }
        return max;
    }
}
