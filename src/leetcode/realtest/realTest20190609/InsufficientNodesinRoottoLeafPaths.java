package leetcode.realtest.realTest20190609;

import leetcode.common.TreeNode;
import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/6/9 11:09
 */
public class InsufficientNodesinRoottoLeafPaths {
    public static void main(String[] args) {
        InsufficientNodesinRoottoLeafPaths paths=new InsufficientNodesinRoottoLeafPaths();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14]"); int limit=1;
        root=PrintUtils.convertStringToBinaryTree("[5,4,8,11,null,17,4,7,1,null,null,5,3]"); limit = 22;
        root=PrintUtils.convertStringToBinaryTree("[10,5,10]"); limit = 21;
        TreeNode res=paths.sufficientSubset(root, limit);
        System.out.println(res);
    }
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        int res=recursion(root, limit, 0);
        if(res<limit) root=null;
        return root;
    }
    public int recursion(TreeNode root, int limit, int sum){
        if(root==null) return sum;
        int left=recursion(root.left, limit, sum+root.val);
        int right=recursion(root.right, limit, sum+root.val);
        System.out.println(root.val+", le="+left+", ri="+right);
        if(left<limit) root.left=null;
        if(right<limit) root.right=null;
        return Math.max(left,right);
    }
}
