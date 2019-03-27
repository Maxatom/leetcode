package leetcode.realtest.realTest20190324;


/**
 * @author shibing
 * @since 2019/3/24 10:45
 */
public class SmallestIntegerDivisiblebyK {
    public static void main(String[] args) {
        SmallestIntegerDivisiblebyK divisiblebyK=new SmallestIntegerDivisiblebyK();
        int K=9;
//        K=1;
        K=2;
        K=3;
        K=7;
//        System.out.println(Integer.MAX_VALUE);
        System.out.println(divisiblebyK.smallestRepunitDivByK(K));
        System.out.println(divisiblebyK.smallestRepunitDivByK1(K));

    }

    public int smallestRepunitDivByK1(int K) {
        if(K%10!=1&& K%10!=3 && K%10!=7 && K%10 !=9) return -1;
        int r=0;
        for (int i = 1; i <= K; i++) {
            r=(r*10+1)%K;
            if(r==0) return i;
        }
        return -1;
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
