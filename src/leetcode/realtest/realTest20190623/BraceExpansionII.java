package leetcode.realtest.realTest20190623;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author shibing
 * @since 2019/6/23 10:48
 */
public class BraceExpansionII {
    public static void main(String[] args) {

    }
    public List<String> braceExpansionII(String expression) {
        Stack<String> stack=new Stack<>();
        char[] exparr=expression.toCharArray();
        String pre="";
        for (int i = 0; i < exparr.length; i++) {
            if(exparr[i]=='{'){
                stack.push(pre.concat(String.valueOf(exparr[i])));
            }
            if(exparr[i]<='z' && exparr[i]>='a') {
                int j=i;
                for (; j < exparr.length && exparr[j]<='z' && exparr[j]>='a'; j++);
                String sub=expression.substring(i,j);
                stack.push(sub);
                i=j;
                if(exparr[j]=='{') pre=sub;
            }
            if(exparr[i]=='}'){
                List<String> list=new ArrayList<>();
                while (!stack.isEmpty() && !stack.peek().equals("{")) list.add(stack.pop());
                list.sort(Comparator.naturalOrder());
            }
        }
        return null;
    }

}
