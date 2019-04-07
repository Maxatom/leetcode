package leetcode.realtest.realTest20190407;


import leetcode.common.TreeNode;
import utils.PrintUtils;

/**
 * @author shibing
 * @since 2019/4/7 10:50
 */
public class SumofRootToLeafBinaryNumbers {
    public static void main(String[] args) {
        SumofRootToLeafBinaryNumbers numbers=new SumofRootToLeafBinaryNumbers();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[1,0,1,0,1,0,1]");
//        root=PrintUtils.convertStringToBinaryTree("[1]");
//        root=PrintUtils.convertStringToBinaryTree("[1,null,0,1,0]");
        System.out.println(numbers.sumRootToLeaf(root));
        System.out.println(numbers.sumRootToLeaf1(root));
    }

    //optimizing code
    public int sumRootToLeaf1(TreeNode root) {
        return dfs(root, 0);
    }
    public int dfs(TreeNode node, int pathNum){
        if(node==null) return 0;
        pathNum=((pathNum<<1)+node.val)%MOD;
        return node.left==node.right?pathNum:(dfs(node.left, pathNum)+ dfs(node.right, pathNum))%MOD;
    }


    int MOD=(int)Math.pow(10,9)+7;
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0, 0);
    }
    public int dfs(TreeNode node, int sum, int pathNum){
        pathNum=((pathNum<<1)+node.val)%MOD;
        if(node.left== null && node.right==null)
            return (sum+pathNum)%MOD;
        if(node.left!=null)
            sum=dfs(node.left, sum, pathNum);
        if(node.right!=null)
            sum=dfs(node.right, sum, pathNum);
        return sum;
    }
}
