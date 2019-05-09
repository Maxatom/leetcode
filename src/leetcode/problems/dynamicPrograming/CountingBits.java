package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-09 10:30:51
 **/
public class CountingBits {
    public static void main(String[] args) {
        CountingBits bit=new CountingBits();
        int num=2;
//        num=5;
//        num=8;
//        num=16;
        PrintUtils.printArray(bit.countBits(num));
        PrintUtils.printArray(bit.countBits1(num));
    }
    //more simple
    public int[] countBits1(int num) {
        int[] res=new int[num+1];
        for (int i = 1; i <= num; i++) res[i]=res[i>>1]+(i&1);
        return res;
    }
    //D(n) = D(n-2^m) +1;  m is bits(n) -1 , bits(n) si the total bit of n
    public int[] countBits(int num) {
        int[] res=new int[num+1];
        int  offset=1;
        for(int i=1; i<=num; i++){
            if((offset<<1)==i) offset<<=1;
            res[i]=res[i&~offset]+1;
        }
        return res;
    }
}
