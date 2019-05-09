package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-09 15:54:21
 **/
public class NonnegativeIntegerswithoutConsecutiveOnes {
    public static void main(String[] args) {
        NonnegativeIntegerswithoutConsecutiveOnes ones=new NonnegativeIntegerswithoutConsecutiveOnes();
        int num=5;
        num=1;
//        num=9;
//        num=15;
//        num=16;
        num=454;
        num=10;
//        for (int i = 1; i < 20; i++) {
//            System.out.println("i="+i);
            System.out.println(ones.findIntegers(num));
//        }
    }
    //D(i)=D(j) (0<j<=i-2)
    //F(2^(i-1), 2^i)=
    public int findIntegers(int num) {
        if(num==1) return 2;
        char[] bits=Integer.toBinaryString(num).toCharArray();
        int[] dp=new int[bits.length+1];
        int[] sum=new int[bits.length+1];
        dp[0]=dp[1]=1;
        sum[0]=1; sum[1]=2;
        for (int i = 2; i < bits.length; i++) {
            for (int j = 0; j < i-1; j++) {
                dp[i]+=dp[j];
            }
            sum[i]=sum[i-1]+dp[i];
        }
        //2^n~num
        sum[bits.length]=sum[bits.length-1]+recursive(dp, sum, bits, 0);
//        PrintUtils.printArray(dp);
        return sum[bits.length];
    }
    int recursive(int[] dp, int[] sum,char[] bits, int i){
        if(bits[i+1]=='1') return sum[bits.length-i-2];
        int j=i;
        i++;
        while (i<bits.length && bits[i]=='0') i++;
        if(i==bits.length-1) return 2;
        return i==bits.length? sum[bits.length-j] : dp[bits.length-i-1] + recursive(dp, sum, bits, i);
    }
}
