package leetcode.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2018/11/17 18:13
 */
public class ReverseBits {
    public static void main(String[] args) {
        ReverseBits reverse=new ReverseBits();
//        System.out.println(reverse.reverseBits1(1));
//        System.out.println(reverse.reverseBits(1));

//        System.out.println(Integer.toBinaryString(reverse.reverseBits1(1)));
//        System.out.println(Integer.toBinaryString(reverse.reverseBits2(1)));

        long start;
        start=System.currentTimeMillis();
        for (long i = 0; i < 10000000000l; i++) {
            reverse.reverseBits2((int)i);
        }
        System.out.println(System.currentTimeMillis()-start);

        start=System.currentTimeMillis();
        for (long i = 0; i < 10000000000l; i++) {
            reverse.reverseBits3((int)i);
        }
        System.out.println(System.currentTimeMillis()-start);

        start=System.currentTimeMillis();
        for (long i = 0; i < 100000000l; i++) {
            reverse.reverseBits4((int)i);
        }
        System.out.println(System.currentTimeMillis()-start);
    }

    Map<Byte,Byte> map=new HashMap<>();
    //very slow
    //optimizing 3  Using cache
    public int reverseBits4(int n){
        byte[] bytes=new byte[4];
        bytes[0]=(byte)((n)&0xFF);
        bytes[1]=(byte)((n>>8)&0xFF);
        bytes[2]=(byte)((n>>16)&0xFF);
        bytes[3]=(byte)((n>>24)&0xFF);
        int result=0;
        result |= reverseBytes(bytes[0]);
        result <<=8;
        result |= reverseBytes(bytes[1]);
        result <<=16;
        result |= reverseBytes(bytes[2]);
        result <<=24;
        result |= reverseBytes(bytes[3]);
        return result;
    }

    public byte reverseBytes(byte n) {
        if(map.get(n)!=null) return map.get(n);
        byte newNum=0;
        for (byte i = 0; i < 8; i++) {
            newNum<<=1;
            newNum|=(1&n);
            n>>=1;
        }
        map.put(n,newNum);
        return newNum;
    }

    //optimizing 2  without if
    public int reverseBits3(int n) {
        int newNum=0;
        for (int i = 0; i < 32; i++) {
            newNum<<=1;
            newNum|=(1&n);
            n>>=1;
        }
        return newNum;
    }

    //optimizing 2
    public int reverseBits2(int n) {
        int newNum=0;
        for (int i = 0; i < 32; i++) {
            newNum<<=1;
            if((1&n)==1)
                newNum|=1;
            n>>=1;
        }
        return newNum;
    }

    //optimizing
    public int reverseBits1(int n) {
        int newNum=0;
        for (int i = 0; i < 32; i++) {
            newNum<<=1;
            if((1&n)==1) {
                newNum++;
            }
            n>>=1;
        }
        return newNum;
    }

    public int reverseBits(int n) {
        int newNum=0;
        for (int i = 0; i < 32; i++) {
            int x=0x1;
            if((x&n)!=0) newNum+=(1<<(31-i));
            n>>=1;
        }
        return newNum;
    }

}
