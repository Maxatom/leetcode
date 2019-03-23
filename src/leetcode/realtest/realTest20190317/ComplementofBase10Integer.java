package leetcode.realtest.realTest20190317;

/**
 * @author shibing
 * @since 2019/3/17 10:32
 */
public class ComplementofBase10Integer {
    public static void main(String[] args) {
        ComplementofBase10Integer comp=new ComplementofBase10Integer();
        int N=5;
//        N=7;
//        N=10;
//        N=0;
//        N=1;
        System.out.println(comp.bitwiseComplement(N));
        System.out.println(comp.bitwiseComplement1(N));
    }
    public int bitwiseComplement1(int N) {
        if(N==0) return 1;
        int h=Integer.highestOneBit(N);
        return N^((h<<1)-1);
    }

    public int bitwiseComplement(int N) {
        if(N==0) return 1;
        int i=0, n=N;
        while (n>0){
            n<<=1;
            i++;
        }
        i=32-i;
        return (~N)&((1<<i)-1);
    }
}
