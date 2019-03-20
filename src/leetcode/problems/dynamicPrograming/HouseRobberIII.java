package leetcode.problems.dynamicPrograming;

import leetcode.common.TreeNode;
import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/1/27 1:29
 */
public class HouseRobberIII {
    public static void main(String[] args) {
        TreeNode root=PrintUtils.convertStringToBinaryTree("[3,2,3,null,3,null,1]");
        root=PrintUtils.convertStringToBinaryTree("[3,4,5,1,3,null,1]");
        root=PrintUtils.convertStringToBinaryTree("[]");
        root=PrintUtils.convertStringToBinaryTree("[8]");
        root=PrintUtils.convertStringToBinaryTree("[4,5]");
        root=PrintUtils.convertStringToBinaryTree("[7,null,5]");
        root=PrintUtils.convertStringToBinaryTree("[4,5,1]");
        root=PrintUtils.convertStringToBinaryTree("[9,5,1]");
        root=PrintUtils.convertStringToBinaryTree("[2,1,3,null,4]");
        root=PrintUtils.convertStringToBinaryTree("[5,3,6,1,4,null,null,null,2]");
        HouseRobberIII robberIII=new HouseRobberIII();
//        System.out.println(robberIII.rob(root));
        System.out.println(robberIII.rob1(root));
//        robberIII.map.forEach((k,v)->System.out.println(k+":"+v));
    }
    public int rob(TreeNode root) {
        return recursive(root, false);
    }
    Map<String, Integer> map=new HashMap<>();
    public int recursive(TreeNode node, boolean par){
        if(node==null) return 0;
        String key=node.toString()+par;
        if(map.containsKey(key)) return map.get(key);
        int  max;
        if(par) {
            max=recursive(node.left, false)+recursive(node.right, false);
        }else {
                int robbed=recursive(node.left, true)+recursive(node.right, true);
                int notrobbed=recursive(node.left, false)+recursive(node.right, false);
                max=Math.max(robbed+node.val, notrobbed);
        }
        map.put(key,max);
        return max;
    }

    public int rob1(TreeNode root) {
        return recursive(root, 0,0);
    }
//    Map<String, Integer> map=new HashMap<>();
    //1-rob  2-not rob  0-no par
    public int recursive(TreeNode node, int par, int grandpar){
        if(node==null) return 0;
        String key=node.toString()+par+grandpar;
        if(map.containsKey(key)) return map.get(key);
        int  max=0;
        if(par==1) {
            max=recursive(node.left, 2, par)+recursive(node.right, 2, par);
        }
        if (par==0 || (par==2 && grandpar!=2)) {
            int robbed = recursive(node.left, 1, par) + recursive(node.right, 1, par);
            int notrobbed = recursive(node.left, 2, par) + recursive(node.right, 2, par);
            max = Math.max(robbed + node.val, notrobbed);
        }
        if(par==2 && grandpar==2) {
            max=node.val+recursive(node.left, 1, par)+recursive(node.right, 1, par);
        }
        map.put(key,max);
        return max;
    }
}
