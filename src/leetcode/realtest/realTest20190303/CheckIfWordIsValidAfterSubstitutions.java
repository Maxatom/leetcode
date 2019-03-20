package leetcode.realtest.realTest20190303;

import java.util.Stack;

/**
 * @author shibing
 * @since 2019/3/9 11:11
 */
public class CheckIfWordIsValidAfterSubstitutions {
    public static void main(String[] args) {
        CheckIfWordIsValidAfterSubstitutions check=new CheckIfWordIsValidAfterSubstitutions();
        String S="aabcbc";
//        S="abcabcababcc";
//        S="abccba";
//        S="cababc";
        System.out.println(check.isValid(S));

    }

    public boolean isValid(String S) {
        Stack<Character> stack=new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i)=='c'){
                if (stack.empty()||stack.pop()!='b') return false;
                if (stack.empty()||stack.pop()!='a') return false;
            }else stack.push(S.charAt(i));
        }
        return stack.size()==0;
    }
}
