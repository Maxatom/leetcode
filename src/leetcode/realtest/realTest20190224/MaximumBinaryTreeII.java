package leetcode.realtest.realTest20190224;

import leetcode.common.TreeNode;
import utils.PrintUtils;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shibing
 * @since 2019/2/24 11:26
 */
public class MaximumBinaryTreeII {
    public static void main(String[] args) {
        MaximumBinaryTreeII treeII=new MaximumBinaryTreeII();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[4,1,3,null,null,2]");  int value=5;
//        root=PrintUtils.convertStringToBinaryTree("[5,2,4,null,1]"); value=3;
//        root=PrintUtils.convertStringToBinaryTree("[5,2,3,null,1]"); value=4;
//        root=PrintUtils.convertStringToBinaryTree("[4]"); value=5;
//        root=PrintUtils.convertStringToBinaryTree("[]"); value=5;
//        root=PrintUtils.convertStringToBinaryTree("[5]"); value=3;
//        root=PrintUtils.convertStringToBinaryTree("[5,2,4,null,1]"); value = 3;
        TreeNode root1=Utils.copyTree(root);
//        TreeNode result=treeII.insertIntoMaxTree(root, value);
//        TreeNode result1=treeII.insertIntoMaxTree1(root, value);
        TreeNode result2=treeII.insertIntoMaxTree2(root, value);
        TreeNode result3=treeII.insertIntoMaxTree3(root1, value);
        System.out.println(Utils.isTreeEqual(result3, result2));
    }

    //space O(1)
    public TreeNode insertIntoMaxTree3(TreeNode root, int val) {
        TreeNode cur=root, pre=root, node=new TreeNode(val);
        while (cur!=null && cur.val>val){
            pre=cur;
            cur=cur.right;
        }
        node.left=cur;
        if(root!=null && cur!=root)
            pre.right=node;
        else root=node;
        return root;
    }


    //several lines  space O(N)
    public TreeNode insertIntoMaxTree2(TreeNode root, int val) {
        if(root!=null && root.val>val){
            root.right=insertIntoMaxTree2(root.right, val);
            return root;
        }
        TreeNode node=new TreeNode(val);
        node.left=root;
        return node;
    }

    public TreeNode insertIntoMaxTree1(TreeNode root, int val) {
        if(root==null) return new TreeNode(val);
        return dfs(root, val, null,root);
    }
    TreeNode dfs(TreeNode node, int val, TreeNode par, TreeNode root){
        if(node==null){
            par.right=new TreeNode(val);
            return root;
        }
        if(node.val<val) {
            TreeNode temp = new TreeNode(val);
            temp.left = node;
            if (par != null) par.right = temp;
            else root = temp;
        }else  dfs(node.right, val, node,root);
        return root;
    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        List<Integer> nodes=new ArrayList<>();
        preOrder(root, nodes, val, true);
        PrintUtils.printList(nodes, p->p+"");
        return construct(nodes);
    }
    boolean added=false;
    void preOrder(TreeNode node, List<Integer> nodes, int val, boolean in){
        if(node==null) {
            if(!added && in){ nodes.add(val);nodes.add(null);nodes.add(null); }
            else nodes.add(null);
            return;
        }
        if(!added&&node.val<val && in) {nodes.add(val); added=true;}
        nodes.add(node.val);
        preOrder(node.left, nodes, val, false);
        preOrder(node.right, nodes, val, in);
    }

    int index=0;
    TreeNode construct(List<Integer> nodes){
        if(index==nodes.size() ) return null;
        if(nodes.get(index)==null) {index++; return null;}
        TreeNode node =new TreeNode(nodes.get(index));
        index++;
        node.left=construct(nodes);
        node.right=construct(nodes);
        return node;
    }
}
