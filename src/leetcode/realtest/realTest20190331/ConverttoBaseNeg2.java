package leetcode.realtest.realTest20190331;

import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/3/31 19:25
 */
public class ConverttoBaseNeg2 {
    public static void main(String[] args) {
        ConverttoBaseNeg2 neg2=new ConverttoBaseNeg2();
        int N=10; //11110
        N=16; //10000
        N=2; //110
        N=3; //111
        N=4; //100
        N=6; //11010
        System.out.println(neg2.baseNeg2(N));
    }
//    public String baseNeg21(int N) {
//        String s=Integer.toBinaryString(N);
//        int[] cs=new int[s.length()+2];
//        int f=0, j=0;
//        for (int i = s.length()-1; i >=0 ; i--) {
//            int temp=f+s.charAt(i)-'0';
//            cs[j]=temp%2;
//            f=temp/2;
//            if((j&1)==1 && cs[j]==1) f=1;
//            j++;
//        }
//
//
//    }
    public String baseNeg2(int N) {
        String s=Integer.toBinaryString(N);
        char[] cs=new char[s.length()+2];
        char f='0';
        int j=0;
        for (int i = s.length()-1; i >=0 ; i--) {
            if(s.charAt(i)=='0'){
                cs[j]=f;
                f='0';
            }else {
                cs[j]=f=='0'?'1':'0';
            }
            if((j&1)==1 && cs[j]=='1') f='1';
            j++;
        }
        while (f=='1'){
            cs[j]='1';
            if((j&1)==1) {
                f='1';
            }else {
                f='0';
            }
            j++;
        }
        char[] res=new char[j];
        for (int i = 0; i < j; i++) {
            res[i]=cs[j-i-1];
        }
        return new String(res);
    }

}
