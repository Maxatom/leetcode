package leetcode.realtest.realTest20181230;

import leetcode.common.TreeNode;
import utils.PrintUtils;

import java.util.*;

/**
 * @author shibing
 * @since 2018/12/30 13:11
 */


public class MinCameraCover {
    public static void main(String[] args) {
        TreeNode root=PrintUtils.convertStringToBinaryTree("[0,null,0,0,0,null,null,null,0]"); //2
        root=PrintUtils.convertStringToBinaryTree("[0,0,null,0,null,0,null,null,0]"); //2
        root=PrintUtils.convertStringToBinaryTree("[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]"); //5
        root=PrintUtils.convertStringToBinaryTree("[0,0,null,null,0,0,0]"); //2

        root=PrintUtils.convertStringToBinaryTree("[0,null,0,null,0,null,0,null,0,0,0,null,null,0,0]"); //3
        root=PrintUtils.convertStringToBinaryTree("[0,0,0,null,null,null,0]"); //2

//        root=PrintUtils.readArrayFromFile()
        MinCameraCover cover=new MinCameraCover();
        System.out.println(cover.minCameraCover4(root));
//        System.out.println(cover.minCameraCover3(root));
//        System.out.println(cover.minCameraCover2(root));
//        System.out.println(cover.minCameraCover1(root));
    }

    //greedy
    int res=0;
    public int minCameraCover4(TreeNode root) {
        return (minCameraCover4Recursive(root)==0?1:0)+res;
    }
    public int minCameraCover4Recursive(TreeNode root) {
        if(root==null) return 2;

        int left=minCameraCover4Recursive(root.left);
        int right=minCameraCover4Recursive(root.right);

        if(left==2 && right==2) {
            return 0;
        }
        if(left==0 || right==0) {
            res++;
            return 1;
        }
        return 2;
    }


    public int minCameraCover3(TreeNode root) {
        return minCameraCover3Recurse(root,null);
    }

    // Greedy bottom-up
    public int minCameraCover3Recurse(TreeNode root, TreeNode par) {
        if(root==null) return 0;
        int left=minCameraCover3Recurse(root.left, root);
        int right=minCameraCover3Recurse(root.right, root);
        if (root.left != null && root.left.val == 0 || root.right != null && root.right.val == 0) {
            root.val = 2;
            if(par!=null) par.val = 1;
            if (root.left != null && root.left.val == 0) root.left.val = 1;
            if (root.right != null && root.right.val == 0) root.right.val = 1;
            return left+right+1;
        }else if(par==null&&root.val==0){
            root.val = 2;
            return left + right + 1;
        }
        return left+right;
    }


    public int minCameraCover2(TreeNode root) {
        int[] result=minCameraCover2Recurse(root);
        return Math.min(result[1],result[2]);
    }

    //DP top-down O(N)
    public int[] minCameraCover2Recurse(TreeNode root){
        if(root==null) return new int[]{0,0,99999};

        int[] left=minCameraCover2Recurse(root.left);
        int[] right=minCameraCover2Recurse(root.right);
//        int minL= Math.min(left[1],left[2]);
//        int minR=Math.min(right[1],right[2]);

        int d0=left[1]+ right[1];
        int d1=Collections.min(Arrays.asList(left[1]+right[2], left[2]+right[1], left[2]+right[2]));
        int d2=1+Collections.min(Arrays.asList(left[0],left[1],left[2]))+Collections.min(Arrays.asList(right[0],right[1],right[2]));
        return new int[]{d0,d1,d2};
    }



    // succeed,  DP  up-bottom
    // history :error case: [0,null,0,0,0,null,null,0,0]
    Map<String, Integer> map=new HashMap<>();
    public int minCameraCover1(TreeNode root) {
        if(root==null) return 0;
        String key=root+","+root.val;
        if(map.containsKey(key)) return map.get(key);
        int left=Integer.MAX_VALUE, right=Integer.MAX_VALUE, mid=0;
        if(root.val==2){
            int left1=0,right1=0;
            if(root.left!=null) left1=minCameraCover1(root.left);
            if(root.right!=null) right1=minCameraCover1(root.right);
            int result=left1+right1+1;
            map.put(root+",2", result);
            return result;
        }else {
            if(root.val==1){
                int left1=0, right1=0;
                if(root.left!=null) left1=minCameraCover1(root.left);
                if(root.right!=null) right1=minCameraCover1(root.right);
                left= left1+right1;
            }
            //watched by root
            int left1 = 0, right1 = 0;
            root.val = 2;
            if (root.left != null) {
                root.left.val = 1;
                left1 = minCameraCover1(root.left);
            }
            if (root.right != null) {
                root.right.val = 1;
                right1 = minCameraCover1(root.right);
            }
            mid = left1 + right1 + 1;
            root.val = 0;
            if (root.left != null) root.left.val = 0;
            if (root.right != null) root.right.val = 0;
            if(left!=Integer.MAX_VALUE) {// root=1
                int result=Math.min(left,mid);
                map.put(root+",1"+root.val, result);
                return result;
            }

            if (root.left!=null) { //watched by left tree
                right1 = 0;
                root.val = 1;
                root.left.val = 2;
                if (root.left.left != null) {
                    root.left.left.val = 1;
                }
                if (root.left.right != null) {
                    root.left.right.val = 1;
                }

                left1 = minCameraCover1(root.left);
                if(root.right!=null)  right1=minCameraCover1(root.right);

                left = left1 + right1;
                root.val = 0;
                root.left.val = 0;
                if (root.left.left != null) root.left.left.val = 0;
                if (root.left.right != null) root.left.right.val = 0;
            }
            if (root.right!=null) { //Watched by right tree
                root.val = 1;
                left1 = 0;
                root.right.val = 2;
                if (root.right.left != null) {
                    root.right.left.val = 1;
                }
                if (root.right.right != null) {
                    root.right.right.val = 1;
                }
                if(root.left!=null) left1 = minCameraCover1(root.left);
                right1 = minCameraCover1(root.right);

                right = left1 + right1;
                root.val = 0;
                root.right.val = 0;
                if (root.right.left != null) root.right.left.val = 0;
                if (root.right.right != null) root.right.right.val = 0;
            }
        }
        int result=Collections.min(Arrays.asList(left,right,mid));
        map.put(root+",0", result);
        return result;
    }

}
