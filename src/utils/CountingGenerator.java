package utils;

import java.util.Random;

/**
 * 随机生成器，随机生成基础类型数据
 * @author Shibing
 * @since 2018-09-26 13:48:09
 **/
public class CountingGenerator {
    static char[] chars=("abcdefthijklmnopqrstuvwxyz"+"ABCDEFTHIJKLMNOPQRSTUVWXYZ").toCharArray();

    public static class Character implements Generator<java.lang.Character>{
        int index=-1;
        @Override
        public java.lang.Character next() {
            index = (index+1) % chars.length;
            return chars[index];
        }
    }
    public static class Integer implements Generator<java.lang.Integer>{
        private Random rand=new Random(47);
        @Override
        public java.lang.Integer next() {
            return rand.nextInt();
        }
    }
    public static class String implements Generator<java.lang.String>{
        private int length=7;
        Generator<java.lang.Character> cg=new Character();
        public String(){}
        public String(int length) {this.length=length;}

        @Override
        public java.lang.String next() {
            char[] buff=new char[length];
            for (int i=0;i<length;i++)
                buff[i]=cg.next();
            return new java.lang.String(buff);
        }
    }
}
