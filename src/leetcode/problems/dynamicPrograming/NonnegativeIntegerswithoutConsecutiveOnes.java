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
        num=9;
        num=15;
//        num=16;
//        num=454;
//        num=10;
//        for (int i = 1; i < 20; i++) {
//            System.out.println("i="+i);
//            System.out.println(ones.findIntegers(num));
            System.out.println(ones.findIntegers1(num));
            System.out.println(ones.findIntegers2(num));
//        }
    }


    public int findIntegers2(int num) {
        int res=0, x=1, y=2, z=3;
        num++;//we plus 1 to num, when num has't consecutive 1s, then the result plus 1; if num has consecutive 1s, the result doesn't change
        while (num>0){
            if((num&1)==1 && (num&2)==2)
                res=0;
            res += x*(num&1);
            num>>=1;
            x=y; y=z; z=x+y;
        }
        return res;
    }

    //f(n)=f(n-1)+f(n-2)
    public int findIntegers1(int num) {
        int[] f=new int[32];
        f[1]=2; f[0]=1;
        for (int i = 2; i < f.length; i++)
            f[i]=f[i-1]+f[i-2];
        int k = 31, pre=0, sum=0;
        while (k>=0){
            if((num&(1<<(k)))!=0){
                if(pre==1) {
                    sum+=f[k]; return sum;
                }
                sum+=f[k];
                pre=1;
            }else pre=0;
            k--;
        }
        return sum+1;
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
        sum[bits.length]=sum[bits.length-1]+recursive(sum, bits, 0);
        return sum[bits.length];
    }
    int recursive( int[] sum,char[] bits, int i){
        if(bits[i+1]=='1') return sum[bits.length-i-2];
        i++;
        while (i<bits.length && bits[i]=='0') i++;
        if(i==bits.length-1) return 2;
        return i==bits.length? 1 : sum[bits.length-i-1] + recursive(sum, bits, i);
    }
}
