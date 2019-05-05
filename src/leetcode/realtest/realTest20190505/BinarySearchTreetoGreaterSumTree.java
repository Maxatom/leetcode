package leetcode.realtest.realTest20190505;

import leetcode.common.TreeNode;
import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2019-05-05 15:48:49
 **/
public class BinarySearchTreetoGreaterSumTree {
    public static void main(String[] args) {
        BinarySearchTreetoGreaterSumTree tree=new BinarySearchTreetoGreaterSumTree();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]");
//        root=PrintUtils.convertStringToBinaryTree("[4]");
//        root=PrintUtils.convertStringToBinaryTree("[4,2,3,null,2,1,0]");
       TreeNode res= tree.bstToGst(root);
        System.out.println(res);
    }
    public TreeNode bstToGst(TreeNode root) {
        recusive(root, 0);
        return root;
    }
    public int recusive(TreeNode root, int sum){
        if(root==null) return sum;
        return recusive(root.left, root.val+=recusive(root.right, sum));
    }
}
