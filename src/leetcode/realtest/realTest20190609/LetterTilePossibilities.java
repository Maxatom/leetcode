package leetcode.realtest.realTest20190609;

/**
 * @author shibing
 * @since 2019/6/9 13:39
 */
public class LetterTilePossibilities {
    public static void main(String[] args) {
        LetterTilePossibilities possibilities=new LetterTilePossibilities();
        String tiles="AAB";
        System.out.println(possibilities.numTilePossibilities(tiles));
    }
    public int numTilePossibilities(String tiles) {
        int n=tiles.length();
        if(n<=1) return n;
        int[] letters=new int[26];
//        System.out.println("tiles="+tiles);
        for (int i = 0; i < n; i++) {
//            System.out.println("i="+i+", c="+tiles.charAt(i));
            letters[tiles.charAt(i)-'A']++;
        }
        int res=factor(n);
        for (int i = 0; i < 26; i++) {
            if(letters[i]>0) res/=factor(letters[i]);
        }
        for (int i = 0; i < n; i++) {
            if(letters[i]>0){
               res+= numTilePossibilities(tiles.substring(0,i)+tiles.substring(i+1, n));
                letters[i]=0;
            }
        }
        return res;
    }
    public int factor(int n){
        int res=1;
        for (int i = 1; i <= n; i++) {
            res*=i;
        }
        return res;
    }
}
