package leetcode.realtest.realTest20190120;

import leetcode.common.TreeNode;
import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-01-21 17:06:16
 **/
public class DistributeCoinsinBinaryTree {
    public static void main(String[] args) {
        DistributeCoinsinBinaryTree tree=new DistributeCoinsinBinaryTree();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[3,0,0]");
        root=PrintUtils.convertStringToBinaryTree("[1]");
        root=PrintUtils.convertStringToBinaryTree("[0,3,0]");
        root=PrintUtils.convertStringToBinaryTree("[1,0,2]");
        root=PrintUtils.convertStringToBinaryTree("[1,0,0,null,3]");
        root=PrintUtils.convertStringToBinaryTree("[1,0,0,null,3,null,2,0,1]");
        System.out.println(tree.distributeCoins(root));
    }

    //DFS 8ms
    public int distributeCoins(TreeNode root) {
        if(root==null) return 0;
        int left=distributeCoins(root.left);
        int right=distributeCoins(root.right);
        if(root.left!=null) root.val+=root.left.val-1;
        if(root.right!=null) root.val+=root.right.val-1;
        return left+right+Math.abs(root.val-1);
    }
}
