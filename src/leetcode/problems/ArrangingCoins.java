package leetcode.problems;

/**
 * @author Shibing
 * @since 2019-01-10 16:54:47
 **/
public class ArrangingCoins {
    public static void main(String[] args) {
        ArrangingCoins arrange=new ArrangingCoins();
        int test=1804289383;
        test=1;
        System.out.println(arrange.arrangeCoins(test));
        System.out.println(arrange.arrangeCoins1(test));
    }

    public int arrangeCoins1(int n) {
        int min=(int)Math.pow(n, 0.5);
        int max=(int)(Math.pow(n, 0.5)*Math.pow(2, 0.5));
        int sum=(1+min)*min/2;
        if(sum==n) return min;
        for (int i = min+1; i <= max; i++) {
            if(i>n-sum) return i-1;
            sum+=i;
        }
        return 0;
    }

    public int arrangeCoins(int n) {
        return (int)(Math.sqrt(n+1./8)*Math.sqrt(2)-1./2);
    }
}
