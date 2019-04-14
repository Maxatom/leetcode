package leetcode.realtest.realTest20190414;

import leetcode.common.TreeNode;
import utils.PrintUtils;
import utils.Utils;


/**
 * @author shibing
 * @since 2019/4/14 13:04
 */
public class RecoveraTreeFromPreorderTraversal {
    public static void main(String[] args) {
        RecoveraTreeFromPreorderTraversal tree=new RecoveraTreeFromPreorderTraversal();
        String S="1-2--3--4-5--6--7";
        S="1-2--3---4-5--6---7";
        S="1-401--349---90--88";
        TreeNode root=tree.recoverFromPreorder(S);
        TreeNode root1=tree.recoverFromPreorder1(S);
        System.out.println(Utils.isTreeEqual(root1,root));
    }

    //using stack
    public TreeNode recoverFromPreorder1(String S) {
        TreeNode[] stack=new TreeNode[1000];
        int top=0, i=0;
        while (i<S.length()) {
            int val = 0, level = 0;
            while (i< S.length() && S.charAt(i) == '-') { i++; level++;}
            while (i< S.length() && S.charAt(i) != '-') {
                val = val * 10 + (S.charAt(i) - '0');
                i++;
            }
            TreeNode node = new TreeNode(val);
            if(top==0){
                stack[top++] = node;
                continue;
            }
            if (top != level) top = level;
            if (stack[top - 1].left == null)
                stack[top - 1].left = node;
            else stack[top - 1].right = node;
            stack[top++] = node;
        }
        return stack[0];
    }



    public TreeNode recoverFromPreorder(String S) {
        return dfs(S,0);
    }
    int index=0;
    public TreeNode dfs(String S, int D) {
        int val=0, curD=0;
        while (index<S.length() && S.charAt(index)!='-') {
            val=val*10+S.charAt(index)-'0';
            index++;
        }
        TreeNode node=new TreeNode(val);
        while (index<S.length() && S.charAt(index)=='-') {
            index++; curD++;
        }
        if(curD==D+1){
            node.left=dfs(S, curD);
        }
        else {
            index-=curD;
            return node;
        }
        curD=0;
        while (index<S.length() && S.charAt(index)=='-') {
            index++; curD++;
        }
        if(curD==D+1){
            node.right=dfs(S,curD);
        }
        return node;
    }
}
