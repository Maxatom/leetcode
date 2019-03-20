package leetcode.realtest.realTest20190210;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shibing
 * @since 2019/2/10 10:33
 */
public class AddtoArrayFormofInteger {
    public static void main(String[] args) {
        int[] A=new int[]{1,2,0,0}; int K=34;
        A=new int[]{2,7,4}; K=181;
        A=new int[]{2,1,5}; K=806;
        A=new int[]{9,9,9,9,9,9,9,9,9,9}; K=1;
        A=new int[]{0}; K=0;
        A=new int[]{0}; K=234;
        A=new int[]{8,9}; K=97234;
        AddtoArrayFormofInteger integer=new AddtoArrayFormofInteger();
        System.out.println(integer.addToArrayForm(A,K));
    }
    public List<Integer> addToArrayForm(int[] A, int K) {
        int bits=0, K1=K;
        while (K1!=0) {K1/=10;bits++;}
        int[] result=new int[Math.max(A.length, bits)+1];
        int s=0, r=0;
        for (int i = result.length-1, j=A.length-1; i >0; i--) {
            result[i]=j>=0?A[j--]:0;
            s=result[i]+K%10+r;
            r=s/10;
            result[i]=s%10;
            K/=10;
        }
        result[0]=r;
        List<Integer> res=new ArrayList<>();
        if(result[0]!=0) res.add(result[0]);
        for (int i = 1; i < result.length; i++) {
            res.add(result[i]);
        }
        return res;
    }
}
