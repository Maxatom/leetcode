package leetcode.realtest.realTest20190714;

import leetcode.common.TreeNode;
import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/7/14 11:24
 */
public class LowestCommonAncestorofDeepestLeaves {
    public static void main(String[] args) {
        LowestCommonAncestorofDeepestLeaves leaves=new LowestCommonAncestorofDeepestLeaves();
        String s="[1,2,3,4]";
        s="[1,2,3,4,5]";
        TreeNode root = PrintUtils.convertStringToBinaryTree(s);
        TreeNode res=leaves.lcaDeepestLeaves(root);
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        TreeNode[] nodes=new TreeNode[1001];
        int[] res=dfs(root, nodes);
        PrintUtils.printArray(res);
        return nodes[res[0]];
    }
    public int[] dfs(TreeNode node, TreeNode[] nodes){
        if(node==null) return new int[]{0, 0};
        nodes[node.val]=node;
        int[] left=dfs(node.left, nodes);
        int[] right=dfs(node.right, nodes);
        left[1]++; right[1]++;
        if(left[1]==right[1])  return new int[]{node.val, left[1]};
        return left[1]>right[1]?left:right;
    }
}
