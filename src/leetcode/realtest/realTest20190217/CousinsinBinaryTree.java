package leetcode.realtest.realTest20190217;

import leetcode.common.TreeNode;
import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/2/17 10:36
 */
public class CousinsinBinaryTree {
    public static void main(String[] args) {
        CousinsinBinaryTree tree=new CousinsinBinaryTree();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[1,2,3,4]"); int  x = 4, y = 3;
//        root=PrintUtils.convertStringToBinaryTree("[1,2,3,null,4,null,5]"); x=5; y=4;
//        root=PrintUtils.convertStringToBinaryTree("[1,2,3,null,4]"); x=2; y=3;
//        root=PrintUtils.convertStringToBinaryTree("[1,2]"); x=1; y=2;
//        root=PrintUtils.convertStringToBinaryTree("[1,2,3]"); x=3; y=2;
        System.out.println(tree.isCousins(root, x, y));
        System.out.println(tree.isCousins1(root, x, y));


    }

    public boolean isCousins1(TreeNode root, int x, int y) {
        int[][] tuples=new int[2][];
        findParent(root, null, x, y, 0, tuples);
        if(tuples[0][0]!=tuples[1][0] && tuples[0][1]==tuples[1][1]) return true;
        else return false;
    }
    public void findParent(TreeNode node, TreeNode par, int x, int y, int depth, int[][] tuples){
        if(node==null) return;
        if(node.val==x) {
            tuples[0]=new int[]{par!=null?par.val:-1, depth};
        } else if(node.val==y) {
            tuples[1]=new int[]{par!=null?par.val:-1, depth};
        }
        if(tuples[0]!=null && tuples[1]!=null) return;
        findParent(node.left, node,  x,y, depth+1, tuples);
        findParent(node.right, node, x, y, depth+1, tuples);
    }
    class Tuple{
        TreeNode node;
        int depth;

        public Tuple(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    public boolean isCousins(TreeNode root, int x, int y) {
        Map<TreeNode, Integer> map=new HashMap<>();
        Tuple xnode=findParent(root, null, x, 0);
        Tuple ynode=findParent(root, null, y, 0);
        if(xnode.depth==ynode.depth && xnode.node!=ynode.node) return true;
        else return false;
    }
    public Tuple findParent(TreeNode node, TreeNode par, int value, int depth){
       if(node==null) return null;
       if(node.val==value) {
           return new Tuple(par, depth);
       } else {
           Tuple left=findParent(node.left, node,  value, depth+1);
           Tuple right=findParent(node.right, node, value, depth+1);
           return left!=null?left:right;
       }
    }
}
