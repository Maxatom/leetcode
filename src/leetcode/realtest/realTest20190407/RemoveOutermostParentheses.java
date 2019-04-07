package leetcode.realtest.realTest20190407;

import java.util.Stack;

/**
 * @author shibing
 * @since 2019/4/7 10:34
 */
public class RemoveOutermostParentheses {
    public static void main(String[] args) {
        RemoveOutermostParentheses parentheses=new RemoveOutermostParentheses();
        String S="(()())(())";
//        S="(()())(())(()(()))";
//        S="()()";
//        S="(()()(()))";
//        S="((()))";
//        S="()";
        System.out.println(parentheses.removeOuterParentheses(S));
        System.out.println(parentheses.removeOuterParentheses1(S));
    }
    //optimizing code
    public String removeOuterParentheses1(String S) {
        StringBuilder sb=new StringBuilder();
        int opened=0;
        for(char c:S.toCharArray()){
            if(c== '(' && opened++ > 0) sb.append(c);
            if(c== ')' && --opened > 0) sb.append(c);
        }
        return sb.toString();
    }

    public String removeOuterParentheses(String S) {
        if(S.length()==0) return S;
        StringBuilder sb=new StringBuilder();
        int left=0, right=0;
        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i)=='(') left++;
            else right++;
            if(left==right){
                left=right=0;
            }else if(left!=1){
                    sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
}
