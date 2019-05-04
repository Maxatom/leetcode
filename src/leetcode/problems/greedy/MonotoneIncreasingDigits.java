package leetcode.problems.greedy;

/**
 * @author shibing
 * @since 2019/5/4 10:06
 */
public class MonotoneIncreasingDigits {
    public static void main(String[] args) {
        MonotoneIncreasingDigits digits=new MonotoneIncreasingDigits();
        int N = 10;
//        N= 8432;
//        N = 1234;
//        N = 332;
//        N=232;
//        N=12340;
//        N=3;
//        N=1242384;
        System.out.println(digits.monotoneIncreasingDigits(N));
        System.out.println(digits.monotoneIncreasingDigits1(N));
    }

    public int monotoneIncreasingDigits1(int N) {
        char[] digits=String.valueOf(N).toCharArray();
        int i=0;
        while (i<digits.length-1 && digits[i]<=digits[i+1]) i++;
        while (i>=0 && i<digits.length-1 && digits[i]>digits[i+1]) digits[i--]--;
        for (int j = i+2; j < digits.length; j++) digits[j]='9';
        return Integer.valueOf(new String(digits));
    }


    public int monotoneIncreasingDigits(int N) {
        if(N<10) return N;
        int n=N, len=0, cnt=0, res=0;
        int r = n % 10;
        n/=10;
        while (n>0){
            cnt++;
            int rn=n%10;
            if(rn<=r) r=rn;
            else {
                r=rn-1;
                len=cnt;
                res=n-1;
            }
            n/=10;
        }
        if(len==0) return N;
        while (len>0){
            res=res*10+9;
            len--;
        }
        return res;
    }
}
