package leetcode.realtest.realTest20190609;

import utils.PrintUtils;

import java.util.*;

/**
 * @author shibing
 * @since 2019/6/9 11:34
 */
public class SmallestSubsequenceofDistinctCharacters {
    public static void main(String[] args) {
        SmallestSubsequenceofDistinctCharacters characters=new SmallestSubsequenceofDistinctCharacters();
        String text="cdadabcc";
//        text="abcd";
//        text="ecbacba";
//        text="leetcode";
        System.out.println(characters.smallestSubsequence(text));
    }
    public String smallestSubsequence(String text) {
        return recursion(text);
    }
    public String recursion(String text){
        if(text.length()<=1) return text;
        int[] letters=new int[26];
        Arrays.fill(letters, -1);
//        System.out.println(text);
        for (int i = 0; i < text.length(); i++) {
//            System.out.println("i="+i+" "+", "+text.charAt(i)+" "+(text.charAt(i)-'a'));
            letters[text.charAt(i)-'a']=i;
        }
//        PrintUtils.printArray(letters);
        int min=text.length();
        for (int i = 0; i < 26; i++) {
            if(letters[i]!=-1) min=Math.min(min, letters[i]);
        }
//        System.out.println("Min="+min);
        int minc=0;
        for (int i = 0; i < min; i++) {
            if(text.charAt(i)<text.charAt(minc)) minc=i;
        }
//        System.out.println("minc="+minc);
        char c=text.charAt(minc);
        return text.charAt(minc)+recursion(text.substring(minc+1).replace(String.valueOf(c),""));
    }
}
