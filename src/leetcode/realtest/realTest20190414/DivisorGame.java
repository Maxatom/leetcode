package leetcode.realtest.realTest20190414;

/**
 * @author shibing
 * @since 2019/4/14 10:43
 */
public class DivisorGame {
    public static void main(String[] args) {
        DivisorGame game=new DivisorGame();
        int N=2;

        for (int i = 1; i < 100; i++) {
            System.out.println(game.divisorGame(i));

        }
    }

    public boolean divisorGame1(int N) {
        return (N&1)==0;
    }
    //dp O(N^2)
    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N+1];
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= i/2; j++) {
                if(i%j==0)
                    dp[i]=dp[i]||!dp[i-j];
            }
        }
        return dp[N];
    }
}
