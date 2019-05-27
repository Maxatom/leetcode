package leetcode.problems.dynamicPrograming;

/**
 * @author Shibing
 * @since 2019-05-27 15:48:24
 **/
public class IsSubsequence {
    public static void main(String[] args) {
        IsSubsequence subsequence=new IsSubsequence();
        String s = "abc", t = "ahbgdc";
        s = "abc"; t = "ahggdc";
        System.out.println(subsequence.isSubsequence(s, t));
    }


    //brute force O(N)
    public boolean isSubsequence(String s, String t) {
        int i=0, j=0;
        while (i<s.length() && j<t.length()){
            if(s.charAt(i)==t.charAt(j)) i++;
            j++;
        }
        return i==s.length();
    }
}
