package leetcode.realtest.realTest20191117_WT163;

import leetcode.common.TreeNode;

public class FindElementsinaContaminatedBinaryTree {
    public static void main(String[] args) {

    }
    TreeNode root;
    public FindElementsinaContaminatedBinaryTree(TreeNode root) {
        this.root=root;
        root.val=0;
        dfs(root);
    }
    public void dfs(TreeNode root){
        if(root.left!=null) {
            root.left.val=2*root.val+1;
            dfs(root.left);
        }
        if(root.right!=null){
            root.right.val=2*root.val+2;
            dfs(root.right);
        }

    }
    public boolean find(int target) {
        return dfsFind(root, target);
    }
    boolean dfsFind(TreeNode root, int target){
        if(root!=null && root.val==target) return true;
        return dfsFind(root.left, target) || dfsFind(root.right, target);
    }
}
