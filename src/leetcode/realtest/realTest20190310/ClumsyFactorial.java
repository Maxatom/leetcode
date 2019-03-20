package leetcode.realtest.realTest20190310;

/**
 * @author shibing
 * @since 2019/3/10 11:12
 */
public class ClumsyFactorial {
    public static void main(String[] args) {
        ClumsyFactorial clumsy=new ClumsyFactorial();
        int N=4;  //7;
//        N=10;   //12
        N=1;
        N=2;
        N=3;
        N=5;
        N=6;
//        N
        System.out.println(clumsy.clumsy(N));
    }
    public int clumsy(int N) {
        int n1=N, sum=0;
        sum+=muldiv(n1);
        n1-=4;
        while (n1>0){
            sum-=muldiv(n1);
            n1-=4;
        }
        int n2=N-3;
        while (n2>0){
            sum+=n2;
            n2-=4;
        }
        return sum;
    }
    public int muldiv(int N){
        if(N<=2) return N;
        int sum=0;
        sum+=N*(--N)/(--N);
        return sum;
    }
}
