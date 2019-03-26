package leetcode.realtest.realTest20190324;


/**
 * @author shibing
 * @since 2019/3/24 10:45
 */
public class SmallestIntegerDivisiblebyK {
    public static void main(String[] args) {
        SmallestIntegerDivisiblebyK divisiblebyK=new SmallestIntegerDivisiblebyK();
        int K=9;
        K=1;
        K=2;
        K=3;
//        System.out.println(Integer.MAX_VALUE);
        System.out.println(divisiblebyK.smallestRepunitDivByK(K));
    }
    public int smallestRepunitDivByK(int K) {
        int sum=0, cnt=0;
        do {
            boolean flag=true;
            for (int d =0; d<10; d++) {
                int temp = sum + K * d ;
                if (temp%10 == 1) {
                    sum=temp/10;
                    cnt++;
                    flag=false;
                    break;
                }
            }
            if(flag) return -1;
        }while (sum>0);
        return cnt;
    }
}
