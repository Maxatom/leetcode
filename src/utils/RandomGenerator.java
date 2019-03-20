package utils;

import java.util.Random;

/**
 * @author Shibing
 * @since 2018-09-26 11:35:45
 **/
public class RandomGenerator {
    private static Random r=new Random(System.currentTimeMillis());
    public static class Boolean implements Generator<java.lang.Boolean>{
        @Override
        public java.lang.Boolean next() {
            return r.nextBoolean();
        }
    }
    public static class Byte implements Generator<java.lang.Byte>{
        @Override
        public java.lang.Byte next() {
            return (byte)r.nextInt();
        }
    }
    public static class Character implements Generator<java.lang.Character>{
        int bottomBound=0;
        int upBound= CountingGenerator.chars.length;
        @Override
        public java.lang.Character next() {
                return CountingGenerator.chars[r.nextInt(upBound - bottomBound) + bottomBound];
        }
        public void setBound(java.lang.Character bottomBound, java.lang.Character upBound){
            this.bottomBound=bottomBound- 'a';
            this.upBound=upBound-'a';
        }
    }
    public static class String extends CountingGenerator.String {
        //Plugin in the random character generator
        { cg=new Character(); } //instance initializer
        public String(){}
        public String(int length){ super(length);}
        public void setCharBound(java.lang.Character bottomBound, java.lang.Character upBound){ ((Character)cg).setBound(bottomBound,upBound);}
    }
    public static class Short implements Generator<java.lang.Short>{
        @Override
        public java.lang.Short next() {
            return (short) r.nextInt();
        }
    }
    public static class Integer implements Generator<java.lang.Integer>{
        @Override
        public java.lang.Integer next() {
            return r.nextInt();
        }
    }
    public static class Long implements Generator<java.lang.Long>{
        private int mod=10000;

        public Long() { }

        public Long(int mod) { this.mod = mod; }

        @Override
        public java.lang.Long next() {
            return new java.lang.Long(r.nextInt(mod));
        }
    }
    public static class Float implements Generator<java.lang.Float>{
        @Override
        public java.lang.Float next() {
            //Trim all but the first two decimal places
            return (float)(Math.round(r.nextFloat()*100))/100;
        }
    }
    public static class Double implements Generator<java.lang.Double>{
        @Override
        public java.lang.Double next() {
            return (double)Math.round(r.nextFloat()*100)/100;
        }
    }
}
