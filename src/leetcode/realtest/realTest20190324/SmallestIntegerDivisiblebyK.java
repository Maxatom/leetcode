package leetcode.realtest.realTest20190324;

import java.math.BigInteger;

/**
 * @author shibing
 * @since 2019/3/24 10:45
 */
public class SmallestIntegerDivisiblebyK {
    public static void main(String[] args) {
        SmallestIntegerDivisiblebyK divisiblebyK=new SmallestIntegerDivisiblebyK();
        int K=9;
//        K=1;
//        K=2;
        K=3;
//        System.out.println(Integer.MAX_VALUE);
        System.out.println(divisiblebyK.smallestRepunitDivByK(K));
    }
    public int smallestRepunitDivByK(int K) {
        int[] set=new int[]{1,11,111,1111,11111,111111,1111111,11111111,111111111,1111111111};
        int res=-1;
        for (int x:set)
            if(x>=K && x%K==0) {
                res=countDigits(x);
                break;
            }
//        BigInteger x=BigInteger.ONE;
//        while (){
//            if(x>=K && x%K==0) break;
//            x=x*10+1;
//
//        }
        return res;
    }
    public int countDigits(int N){
        int cnt=1;
        while (N/10!=0){
            N/=10;
            cnt++;
        }
        return cnt;
    }
}
