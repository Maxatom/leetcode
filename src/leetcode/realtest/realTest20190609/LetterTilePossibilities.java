package leetcode.realtest.realTest20190609;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shibing
 * @since 2019/6/9 13:39
 */
public class LetterTilePossibilities {
    public static void main(String[] args) {
        LetterTilePossibilities possibilities=new LetterTilePossibilities();
        String tiles="AAB";
//        tiles="AAABBC";
        tiles="CDC";
        System.out.println(possibilities.numTilePossibilities(tiles));
        System.out.println(possibilities.numTilePossibilities1(tiles));
    }

    public int numTilePossibilities1(String tiles) {
        int[] cnts=new int[26];
        for (int i = 0; i < tiles.length(); i++) {
            cnts[tiles.charAt(i)-'A']++;
        }
        return recursion(cnts);
    }
    public int recursion(int[] cnts){
        int sum=0;
        for (int i = 0; i < cnts.length; i++) {
            if(cnts[i]>0){
                sum++;
                cnts[i]--;
                sum+=recursion(cnts);
                cnts[i]++;
            }
        }
        return sum;
    }


    public int numTilePossibilities(String tiles) {
        char[] arr=tiles.toCharArray();
        Arrays.sort(arr);
        return recursion(String.valueOf(arr));
    }
    Set<String> set=new HashSet<>();
    public int recursion(String tiles) {
//        System.out.println("tiles="+tiles);
        if(set.contains(tiles)) return 0;
        int n=tiles.length();
        if(n<=1){
            set.add(tiles);
            return n;
        }
        int[] letters=new int[26];
        for (int i = 0; i < n; i++)
            letters[tiles.charAt(i)-'A']++;
        int res=factor(n);
        for (int i = 0; i < 26; i++)
            if(letters[i]>0) res/=factor(letters[i]);
        for (int i = 0; i < n; i++) {
            if(letters[tiles.charAt(i)-'A']>0)
                res+= recursion(tiles.substring(0,i)+tiles.substring(i+1, n));
        }
//        System.out.println("tiles="+tiles+", res="+res);
        set.add(tiles);
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
