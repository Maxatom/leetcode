package leetcode.Mock.date20191207;

import java.util.HashMap;
import java.util.Map;

public class Q1_ITW2 {
    public static void main(String[] args) {
        Q1_ITW2 q=new Q1_ITW2();
        String s="abcabcbb";
        s="pwwkew";
        s="sdwerddwerqwe";
        System.out.println(q.lengthOfLongestSubstring(s));

    }
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map=new HashMap<>();
        int len=0, maxi=-1;
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            maxi=Math.max(maxi, map.getOrDefault(c, -1));
            len=Math.max(len, i-maxi);
            map.put(c, i);
        }
        return len;
    }
}
