package leetcode.realtest.realTest20201108_WC214;

import java.util.Arrays;

public class MinDeletions {
    public static void main(String[] args) {
        MinDeletions deletions = new MinDeletions();
        String s="abcdefg";
        s="abababab";
        System.out.println(deletions.minDeletions(s));
    }
    public int minDeletions(String s) {
        int[] cnt = new int[26];
        for(char c:s.toCharArray()) cnt[c-'a']++;
        Arrays.sort(cnt);
        int delcnt=0;
        for(int i=24; i>=0 && cnt[i]>0; i--){
            if(cnt[i]>=cnt[i+1]){
                int v = cnt[i+1]-1>=0?cnt[i+1]-1:0;
                delcnt += cnt[i]-v;
                cnt[i] = v;
            }
        }
        return delcnt;
    }
}
