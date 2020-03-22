package leetcode.realtest.realTest20200322_WC181;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/3/2215:11
 */
public class LongestHappyPrefix {
    public static void main(String[] args) {
        LongestHappyPrefix prefix=new LongestHappyPrefix();
        String s = "level";
//        s = "ababab";
        System.out.println(prefix.longestPrefix(s));

    }
    //KMP
    public String longestPrefix(String s) {
        int n=s.length();
        int[] next=new int[n];
        next[0]=-1;
        for(int i=1; i<n; i++){
            int si=next[i-1];
            while(s.charAt(i)!=s.charAt(si+1)){
                if(si==-1){
                    si=-2;
                    break;
                }
                si=next[si];
            }
            next[i]=si+1;
        }
        PrintUtils.printArray(next);
        return s.substring(0,next[n-1]+1);
    }
}
