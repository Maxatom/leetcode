package leetcode.realtest.realTest20190414;

/**
 * @author shibing
 * @since 2019/4/14 10:43
 */
public class DivisorGame {
    public static void main(String[] args) {
        DivisorGame game=new DivisorGame();
        int N=2;
        N=3;
        N=4;
        N=5;
        System.out.println(game.divisorGame(N));
    }
    public boolean divisorGame(int N) {
        return recursive(N, true);
    }
    public boolean recursive(int N, boolean isA){
        if(N==1) return !isA;
        if(N==2) return isA;
        int i=1;
        boolean res=false;
        while (i<=N/2 ){
            if(N%i==0) {
                res =res || recursive(N-i, !isA);
            }
            i++;
        }
        return res;
    }
}
