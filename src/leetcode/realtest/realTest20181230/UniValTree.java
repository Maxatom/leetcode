package leetcode.realtest.realTest20181230;

/**
 * @author shibing
 * @since 2018/12/30 10:34
 */

import leetcode.common.TreeNode;

/**
     * Definition for a binary tree node.
     */
public class UniValTree {
        public static void main(String[] args) {
            TreeNode root=new TreeNode(3);
            root.left=new TreeNode(3);
            root.right=new TreeNode(3);
            root.right.right=new TreeNode(3);
            root.left.right=new TreeNode(3);
            UniValTree tree=new UniValTree();
            System.out.println(tree.isUnivalTree(root));
        }

        public boolean isUnivalTree(TreeNode root) {
            int val=root.val;
            return isUniVal(root,val);
        }

        boolean isUniVal(TreeNode root, int val){
            if(root==null) return true;
            if(root.val!=val) return false;
            return isUniVal(root.left,val)&&isUniVal(root.right,val);
        }
}
