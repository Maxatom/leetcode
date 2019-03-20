package leetcode.problems.tree;

import leetcode.common.TreeNode;
import utils.Utils;

/**
 * @author Shibing
 * @since 2019-02-26 14:51:00
 **/
public class MaximumBinaryTree {
    public static void main(String[] args) {
        MaximumBinaryTree tree=new MaximumBinaryTree();
        int[] nums=new int[]{3,2,1,6,0,5};
//        nums=new int[]{1};
//        nums=new int[]{1,2,4};
//        nums=new int[]{5,4,2};
        TreeNode res=tree.constructMaximumBinaryTree(nums);
//        TreeNode res1=tree.constructMaximumBinaryTree1(nums);
        TreeNode res2=tree.constructMaximumBinaryTree2(nums);
        System.out.println(Utils.isTreeEqual(res, res2));
    }

    //O(N) space O(N)
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        TreeNode root=new TreeNode(nums[0]);;
        TreeNode[] rightPath=new TreeNode[nums.length];
        rightPath[0]=root;
        int r=0;
        for (int i = 1; i < nums.length; i++) {
            int pos=binarySearch(rightPath, r, nums[i]);
            TreeNode temp=new TreeNode(nums[i]);
            if(pos<=r) temp.left=rightPath[pos];
            if(pos>0) rightPath[pos-1].right=temp;
            else root=temp;
            r=pos;
            rightPath[r]=temp;
        }
        return root;
    }

    private int binarySearch(TreeNode[] nodes, int R, int cur){
        int i=0, j=R, mid;
        while (i<j){
            mid=(i+j)/2;
            if(nodes[mid].val>cur) i=mid+1;
            else if(nodes[mid].val<cur) j=mid;
        }
        return cur>nodes[i].val?i:i+1;
    }


    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        return recursive(nums, 0, nums.length-1);
    }
    //O(N^2)
    public TreeNode recursive(int[] nums, int left, int right){
        if(left>right) return null;
        int maxi=left;
        for (int i = left+1; i <= right; i++)
            maxi=nums[i]>nums[maxi]?i:maxi;
        TreeNode root=new TreeNode(nums[maxi]);
        root.left=recursive(nums, left, maxi-1);
        root.right=recursive(nums, maxi+1, right);
        return root;
    }

    //loop O(N^2)
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root=null;
        for (int i = 0; i < nums.length; i++) {
            TreeNode node = new TreeNode(nums[i]), cur = root;
            if (cur == null || cur.val < nums[i]) {
                node.left = cur;
                root = node;
            }else {
                while (cur.right != null && cur.right.val > nums[i]) cur = cur.right;
                node.left = cur.right;
                cur.right = node;
            }
        }
        return root;
    }


}
