package newcode;

/**
 * @author Shibing
 * @since 2018-12-25 10:09:54
 **/
public class AccumulateFromOneToN {
    public static void main(String[] args) {
        AccumulateFromOneToN oneToN=new AccumulateFromOneToN();
        System.out.println(oneToN.accumulator(100));
        System.out.println(oneToN.accumulator1(100));
    }
    public int accumulator(int n){
        int reuslt=(int)(Math.pow(n, 2))+n;
        return reuslt>>1;
    }

    public int accumulator1(int n){
        int sum=n;
        boolean x=n>0&&((sum+=accumulator1(n-1))>0);
        return sum;
    }
}
