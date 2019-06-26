package leetcode.TopInterview;

import leetcode.common.TreeNode;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Shibing
 * @since 2019-06-17 17:22:51
 **/
public class BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversalII traversalII=new BinaryTreeLevelOrderTraversalII();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[3,9,20,null,null,15,7]");
        PrintUtils.printList(traversalII.levelOrderBottom(root), l->l.toString());
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list=new LinkedList<>();
        dfs(list, root, 1);
        return list;
    }
    public void dfs(List<List<Integer>> res, TreeNode node, int height){
        if(node==null) return;
        if(height>res.size()){
            res.add(0, new ArrayList<>());
        }
        res.get(res.size()-height).add(node.val);
        dfs(res, node.left, height+1);
        dfs(res, node.right, height+1);
    }
}
