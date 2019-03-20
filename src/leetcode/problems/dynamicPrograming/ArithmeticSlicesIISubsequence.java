package leetcode.problems.dynamicPrograming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibing
 * @since 2019-01-30 17:04:50
 **/
public class ArithmeticSlicesIISubsequence {
    public static void main(String[] args) {
        ArithmeticSlicesIISubsequence slice=new ArithmeticSlicesIISubsequence();
        int[] A=new int[]{2, 4, 6, 8, 10};
//        A=new int[]{0,1,2,2,2};
//        A=new int[]{0,1,2,2,2,3,3,3};
//        A=new int[]{2,2,2,2};
//        A=new int[]{2,2,2,3,4};
        A=new int[0];
        A=new int[]{1};
//        A=new int[]{2,2};
        A=new int[]{2,2,2};
        A=new int[]{0,2000000000,-294967296};
        System.out.println(2000000000+294967296);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(slice.numberOfArithmeticSlices(A));
    }
    //f[i][d] denotes the number of weak arithmetic slices that end with i and its common difference is d;
    // i<j; f[i][A[i]-A[j]]=f[j][A[i]-A[j]]+1
    public int numberOfArithmeticSlices(int[] A) {
        if(A==null||A.length==0) return 0;
        int N=A.length;
        Map<Long,Integer>[] f=new Map[N];
        f[0]=new HashMap<>();
        int ans=0;
        for (int i = 1; i < N; i++) {
            f[i]=new HashMap<>();
            for (int j = 0; j < i; j++) {
                long d=(long)A[i]-A[j];
                int sj=f[j].getOrDefault(d, 0)+1;
                f[i].put(d, f[i].getOrDefault(d, 0)+sj);
                ans+=sj;
            }
        }
        return ans-N*(N-1)/2;
    }
}
