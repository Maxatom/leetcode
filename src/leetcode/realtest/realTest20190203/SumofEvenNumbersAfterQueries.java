package leetcode.realtest.realTest20190203;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/2/3 10:35
 */
public class SumofEvenNumbersAfterQueries {
    public static void main(String[] args) {
        SumofEvenNumbersAfterQueries sum=new SumofEvenNumbersAfterQueries();
        int[] A=new int[]{1,2,3,4}; int[][] queries=new int[][]{{1,0},{-3,1},{-4,0},{2,3}};
//        A=new int[]{1}; queries=new int[][]{{1,0},{2,0}};
//        A=new int[]{1}; queries=new int[][]{{1,0},{1,0}};
//        A=new int[]{1}; queries=new int[][]{{4,0}};
        PrintUtils.printIntArray(sum.sumEvenAfterQueries(A, queries));
    }
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int sum=0;
        int[] ans=new int[queries.length];
        for (int i = 0; i < A.length; i++)
            if((A[i]&1)==0) sum+=A[i];
        for (int i = 0; i < ans.length; i++) {
            int val=queries[i][0], index=queries[i][1];
//            if((A[index]&1)==0&&(val&1)==0)
//                sum+=val;
//            if((A[index]&1)==0&&(val&1)==1)
//                sum-=A[index];
//            if((A[index]&1)==1&&(val&1)==1)
//                sum+=A[index]+val;
//            A[index]+=val;
            if((A[index]&1)==0) sum-=A[index];
            A[index]+=val;
            if((A[index]&1)==0) sum+=A[index];
            ans[i]=sum;
        }
        return ans;
    }
}
