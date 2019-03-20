package leetcode.realtest.realTest20190106;

import utils.PrintUtils;
import leetcode.common.TreeNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shibing
 * @since 2019/1/6 11:24
 */
public class FlipBinaryTree {
    public static void main(String[] args) {
        FlipBinaryTree tree=new FlipBinaryTree();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[1,2]");
        int[] voyage=PrintUtils.convertStringToIntArray("[2,1]");
//        root=PrintUtils.convertStringToBinaryTree("[1]");
//        voyage=PrintUtils.convertStringToIntArray("[2]");
//        root=PrintUtils.convertStringToBinaryTree("[1,2,3]");
//        voyage=PrintUtils.convertStringToIntArray("[1,3,2]");
//        root=PrintUtils.convertStringToBinaryTree("[1,2,3]");
//        voyage=PrintUtils.convertStringToIntArray("[1,2,3]");
//        root=PrintUtils.convertStringToBinaryTree("[1,2,6, null,3,null,null, 4,5]");
//        voyage=PrintUtils.convertStringToIntArray("[1,2,3,5,4, 6]");
        root=PrintUtils.convertStringToBinaryTree("[1,2,6, null,3,null,null, 4,5]");
        voyage=PrintUtils.convertStringToIntArray("[1,6,2,3,4,5]");
        root=PrintUtils.convertStringToBinaryTree("[1,2,null,3,null,4,null,5,6]");
        voyage=PrintUtils.convertStringToIntArray("[1,2,3,5,4,6]");
        List<Integer> result=tree.flipMatchVoyage(root, voyage);
        PrintUtils.printList(result, p->p+"");
    }

//    public List<Integer> flipMatchVoyage1(TreeNode root, int[] voyage) {
//        int i= bottomUpRecursvie(root,voyage, voyage.length);
//        return new ArrayList<>();
//    }
//
//    int bottomUpRecursvie(TreeNode node, int[] voyage, int i){
//        i=bottomUpRecursvie(node.right,voyage,i);
//        bottomUpRecursvie(node.left,voyage,i);
//        return 0;
//    }

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        Set<Integer> set=new HashSet<>();
        int result=preOrder(root, voyage, 0, null, set, true);
        if(result==-1){ set.clear(); set.add(-1);}
        return set.stream().collect(Collectors.toList());
    }

    int preOrder(TreeNode node, int[] voyage, int i, TreeNode par, Set<Integer> set, boolean left){
        if(node==null) return i;
        if(par==null){
            if(voyage[i]!=node.val) {return -1;}
        }else {
            if(voyage[i]!=node.val){
                if(left && par.right!=null && par.right.val==voyage[i]) {
                        set.add(par.val);
                        TreeNode temp = par.left;
                        par.left = par.right;
                        par.right = temp;
                        node=par.left;
                }else {
                    return -1;
                }
            }
        }
       if(node.left!=null) i=preOrder(node.left, voyage, ++i, node, set, true);
        if(i==-1) return -1;
       if(node.right!=null) i=preOrder(node.right, voyage, ++i, node, set, false);
        return i;
    }

}
