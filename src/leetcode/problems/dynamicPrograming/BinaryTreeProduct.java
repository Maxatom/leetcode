package leetcode.problems.dynamicPrograming;

import leetcode.common.TreeNode;
import utils.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeProduct {
    public static void main(String[] args) {
        BinaryTreeProduct product=new BinaryTreeProduct();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[1,2,3,4,5,6]");
        root=PrintUtils.convertStringToBinaryTree("[1,null,2,3,4,null,null,5,6]");
        System.out.println(product.maxProduct(root));
    }
    public int maxProduct(TreeNode root) {
        int sum=accum(root);
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root.left); queue.add(root.right);
        int min=Integer.MAX_VALUE, prod=0, MOD=1000000007;
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            if(node==null) continue;
            int a=Math.abs(sum-node.val-node.val);
            if(a<min){
                prod=(int)((long)(sum-node.val)*node.val)%MOD;
                min=a;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
        return prod;
    }
    int accum(TreeNode root){
        if(root==null) return 0;
        return root.val+=accum(root.left)+accum(root.right);
    }
}
