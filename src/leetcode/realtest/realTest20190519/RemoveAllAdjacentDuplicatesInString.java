package leetcode.realtest.realTest20190519;

/**
 * @author shibing
 * @since 2019/5/19 10:57
 */
public class RemoveAllAdjacentDuplicatesInString {
    public static void main(String[] args) {
        RemoveAllAdjacentDuplicatesInString string=new RemoveAllAdjacentDuplicatesInString();
        String s="abbaca";
        s="d";
        s="daadseedsa";
        System.out.println(string.removeDuplicates(s));
    }
    public String removeDuplicates(String S) {
        int n=S.length();
        char[] stack=new char[n];
        int top=-1;
        for (int i = 0; i < n; i++) {
            if (top>=0 && S.charAt(i)==stack[top]) top--;
            else stack[++top]=S.charAt(i);
        }
        return new String(stack, 0, top+1);
    }
}
