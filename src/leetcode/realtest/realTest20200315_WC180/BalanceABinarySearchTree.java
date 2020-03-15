package leetcode.realtest.realTest20200315_WC180;

import leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/3/1511:31
 */
public class BalanceABinarySearchTree {
    public static void main(String[] args) {

    }
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        preTra(root, list);
        return create(list,0,list.size()-1);
    }
    void preTra(TreeNode node, List<Integer> list){
        if(node==null) return;
        preTra(node.left,list);
        list.add(node.val);
        preTra(node.right,list);
    }
    TreeNode create(List<Integer> list,int left, int right){
        int mid=(left+right)/2;
        TreeNode node=new TreeNode(list.get(mid));
        node.left =create(list, left,  mid-1);
        node.right=create(list, mid+1, right);
        return node;
    }
}
