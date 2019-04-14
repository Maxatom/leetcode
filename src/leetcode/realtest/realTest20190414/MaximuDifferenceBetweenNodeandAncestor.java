package leetcode.realtest.realTest20190414;

import leetcode.common.TreeNode;
import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/4/14 12:12
 */
public class MaximuDifferenceBetweenNodeandAncestor {
    public static void main(String[] args) {
        MaximuDifferenceBetweenNodeandAncestor ancestor=new MaximuDifferenceBetweenNodeandAncestor();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[8,3,10,1,6,null,14,null,null,4,7,13]");
        root=PrintUtils.convertStringToBinaryTree("[8,3]");
        System.out.println(ancestor.maxAncestorDiff(root));
    }

    //optimize code
    public int maxAncestorDiff1(TreeNode root) {
        return dfs(root, root.val, root.val);
    }
    public int dfs(TreeNode node, int max, int min){
        if(node==null) return max-min;
        max=Math.max(node.val, max);
        min=Math.min(node.val, min);
        return Math.max(dfs(node.left, max, min), dfs(node.right, max, min));
    }


    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val, 0);
    }
    public int dfs(TreeNode node, int max, int min, int value){
        if(node==null) return value;
        int current=Math.max(Math.abs(node.val-max), Math.abs(node.val-min));
        max=Math.max(node.val, max);
        min=Math.min(node.val, min);
        value=dfs(node.left, max, min, Math.max(current,value));
        value=dfs(node.right, max, min, value);
        return value;
    }
}
