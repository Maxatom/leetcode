package leetcode.realtest.realTest20190203;

import leetcode.common.TreeNode;
import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/2/3 11:04
 */
public class SmallestStringStartingFromLeaf {
    public static void main(String[] args) {
        SmallestStringStartingFromLeaf leaf=new SmallestStringStartingFromLeaf();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[0,1,2,3,4,3,4]");
//        root=PrintUtils.convertStringToBinaryTree("[25,1,3,1,3,0,2]");
//        root=PrintUtils.convertStringToBinaryTree("[2,2,1,null,1,0,null,0]");
//        root=PrintUtils.convertStringToBinaryTree("[1]");
//        root=PrintUtils.convertStringToBinaryTree("[0,null,1]");
        System.out.println(leaf.smallestFromLeaf(root));
        System.out.println(leaf.smallestFromLeaf1(root));
    }
    //use string
    public String smallestFromLeaf1(TreeNode root) {
        if(root==null) return "|";
        char c=(char)('a'+root.val);
        if(root.left==null&&root.right==null) return c+"";
        String L=smallestFromLeaf1(root.left);
        String R=smallestFromLeaf1(root.right);
        return (L.compareTo(R)<0?L:R)+c;
    }

    String letters="abcdefghijklmnopqrstuvwxyz";
    public String smallestFromLeaf(TreeNode root) {
        return recursive(root).toString();
    }
    public StringBuilder recursive(TreeNode node) {
        if(node==null) return new StringBuilder();
        StringBuilder left=recursive(node.left);
        StringBuilder right=recursive(node.right);
        if(!left.toString().equals("")&&!right.toString().equals(""))
            return compare(left,right).append(letters.charAt(node.val));
        if(left.toString().equals("")) return right.append(letters.charAt(node.val));
        else return left.append(letters.charAt(node.val));
    }
    StringBuilder compare(StringBuilder s1, StringBuilder s2){
        int i=0;
        while (i<s1.length()&&i<s2.length()){
            char c1=s1.charAt(i), c2=s2.charAt(i);
            if(c1>c2) return s2;
            else if(c1<c2) return s1;
            i++;
        }
        return s1.length()<s2.length()?s1:s2;
    }
}
