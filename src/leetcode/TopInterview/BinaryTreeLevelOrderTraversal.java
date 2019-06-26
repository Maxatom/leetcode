package leetcode.TopInterview;

import leetcode.common.TreeNode;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Shibing
 * @since 2019-06-17 16:51:14
 **/
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal traversal=new BinaryTreeLevelOrderTraversal();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[3,9,20,null,null,15,7]");
        root=PrintUtils.convertStringToBinaryTree("[]");
        List<List<Integer>> list=traversal.levelOrder(root);
        PrintUtils.printList(list, l->l.toString());
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list=new ArrayList<>();
            Queue<TreeNode> next=new LinkedList<>();
            TreeNode node;
            while ((node=queue.poll())!=null){
                list.add(node.val);
                if(node.left!=null) next.add(node.left);
                if(node.right!=null) next.add(node.right);
            }
            res.add(list);
            queue=next;
        }
        return res;
    }
}
