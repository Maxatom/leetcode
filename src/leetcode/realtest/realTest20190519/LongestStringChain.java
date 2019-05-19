package leetcode.realtest.realTest20190519;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author shibing
 * @since 2019/5/19 11:12
 */
public class LongestStringChain {
    public static void main(String[] args) {
        LongestStringChain chain=new LongestStringChain();
        String[] str={"a","b","ba","bca","bda","bdca"};
        str=new String[]{"sed"};
        str=new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};
        System.out.println(chain.longestStrChain(str));
    }
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(p->p.length()));
        int n=words.length, max=1;
        int[] dp=new int[n];
        for (int i = 0; i < n ; i++) {
            dp[i]=1;
            for (int j = i-1; j >=0 ; j--) {
                if(isPredecessor(words[j], words[i])) dp[i]=Math.max(dp[i], dp[j]+1);
            }
            max=Math.max(max, dp[i]);
        }
        PrintUtils.printArray(dp);
        return max;
    }
    boolean isPredecessor(String s1,String s2){
        int n1=s1.length(), n2=s2.length();
        if(n1!=n2-1) return false;
        for (int i = 0; i < n2; i++) {
            boolean flag=true;
            for (int j = 0; j < n1; j++) {
                if(s1.charAt(j)!=s2.charAt(j>=i? j+1 : j)) {
                    flag=false; break;
                }
            }
            if(flag) return true;
        }
        return false;
    }
}
