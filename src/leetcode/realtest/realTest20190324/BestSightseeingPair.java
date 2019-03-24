package leetcode.realtest.realTest20190324;

/**
 * @author shibing
 * @since 2019/3/24 12:05
 */
public class BestSightseeingPair {
    public static void main(String[] args) {
        BestSightseeingPair pair=new BestSightseeingPair();
        int[] A=new int[]{8,1,5,2,6};
        A=new int[]{1,2,3,4};
        A=new int[]{ 8,0};
        System.out.println(pair.maxScoreSightseeingPair(A));
        System.out.println(pair.maxScoreSightseeingPair1(A));
    }

    //dp(i)= max(max(A[j]+j-i)+A[i])
    public int maxScoreSightseeingPair1(int[] A) {
        int cur=A[0]-1, max=0;
        for (int i = 1; i < A.length; i++) {
            max=Math.max(cur+A[i], max);
            cur=Math.max(cur-1, A[i]-1);
        }
        return max;
    }


    //brute force
    public int maxScoreSightseeingPair(int[] A) {
        int max=0;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                max=Math.max(A[j]+A[i]+j-i, max);
            }
        }
        return max;
    }
}
