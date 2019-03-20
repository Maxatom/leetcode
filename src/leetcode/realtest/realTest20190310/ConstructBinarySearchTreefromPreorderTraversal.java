package leetcode.realtest.realTest20190310;

import leetcode.common.TreeNode;
import utils.Utils;

/**
 * @author shibing
 * @since 2019/3/10 11:36
 */
public class ConstructBinarySearchTreefromPreorderTraversal {
    public static void main(String[] args) {
        ConstructBinarySearchTreefromPreorderTraversal tree=new ConstructBinarySearchTreefromPreorderTraversal();
        int[] preorder=new int[]{8,5,1,7,10,12};
        preorder=new int[]{9};
        preorder=new int[]{4,2};
        preorder=new int[]{4,2, 1};
        preorder=new int[]{2,4};
        preorder=new int[]{2,4,6};
        TreeNode root=tree.bstFromPreorder(preorder);
        TreeNode root1=tree.bstFromPreorder1(preorder);
        System.out.println(Utils.isTreeEqual(root, root1));
    }


    public TreeNode bstFromPreorder1(int[] preorder) {
        index=0;
        return create1(preorder, Integer.MAX_VALUE);
    }
    //O(N)
    int index=0;
    public TreeNode create1(int[] preorder, int bound){
        if(index==preorder.length || preorder[index]>bound) return null;
        int val=preorder[index++];
        TreeNode node=new TreeNode(val);
        node.left=create1(preorder, val);
        node.right=create1(preorder, bound);
        return node;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return create(preorder, 0, preorder.length-1);
    }
    //O(N^2)
    public TreeNode create(int[] preorder, int index, int max){
        if(index==max) return new TreeNode(preorder[index]);
        if(index>max) return null;
        TreeNode node=new TreeNode(preorder[index]);
        int split=max+1;
        for (int i = index; i <= max; i++)
            if(preorder[i]>preorder[index]){
                split=i;
                break;
            }
        node.left=create(preorder, index+1, split-1);
        node.right=create(preorder, split, max);
        return node;
    }
}
