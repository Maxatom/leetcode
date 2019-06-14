package leetcode.TopInterview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibing
 * @since 2019-06-13 10:49:24
 **/
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters characters=new LongestSubstringWithoutRepeatingCharacters();
        String s="abcabcbb";
//        s="bbbbb";
//        s="pwwkew";
//        s="abba";
        System.out.println(characters.lengthOfLongestSubstring(s));
        System.out.println(characters.lengthOfLongestSubstring1(s));
    }
    //sliding windows
    public int lengthOfLongestSubstring1(String s) {
        int l=-1, max=0;
        for (int i = 0; i < s.length(); i++) {
            int j=i-1;
            while (l<j && s.charAt(j)!=s.charAt(i)) j--;
            l=j;
            max=Math.max(max, i-l);
        }
        return max;
    }
    //Hashmap
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map=new HashMap<>();
        int max=0, start=-1;
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if(map.containsKey(c)) start=Math.max(start,map.get(c));
            max=Math.max(max, i-start);
            map.put(c, i);
        }
        return max;
    }
}
