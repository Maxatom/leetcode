package leetcode.realtest.realTest20190331;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shibing
 * @since 2019/3/31 10:34
 */
public class BinaryPrefixDivisibleBy5 {
    public static void main(String[] args) {
        BinaryPrefixDivisibleBy5 divisible=new BinaryPrefixDivisibleBy5();
        int[] A=new int[]{0,1,1};
        A=new int[]{1,1,1};
        A=new int[]{0,1,1,1,1,1};
        A=new int[]{1,1,1,0,1};
        A=new int[]{1};
        A=new int[]{0};
        PrintUtils.printList(divisible.prefixesDivBy5(A), p->p+"");
    }
    public List<Boolean> prefixesDivBy5(int[] A) {
        int r=0;
        List<Boolean> res=new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            r<<=1;
            r+=A[i];
            r%=5;
            res.add(r==0);
        }
        return res;
    }
}
