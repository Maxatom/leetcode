package leetcode.realtest.realTest20190203;

import leetcode.common.TreeNode;
import utils.PrintUtils;

import java.util.*;

/**
 * @author shibing
 * @since 2019/2/3 12:29
 */
public class VerticalOrderTraversalofaBinaryTree {
    public static void main(String[] args) {
        VerticalOrderTraversalofaBinaryTree tree=new VerticalOrderTraversalofaBinaryTree();
        TreeNode root=PrintUtils.convertStringToBinaryTree("[3,9,20,null,null,15,7]");
//        root=PrintUtils.convertStringToBinaryTree("[1,2,3,4,5,6,7]");
//        root=PrintUtils.convertStringToBinaryTree("[1]");
//        root=PrintUtils.convertStringToBinaryTree("[0,2,1,3,null,null,null,4,5,null,7,6,null,10,8,11,9]");
        List<List<Integer>> res=tree.verticalTraversal(root);
        PrintUtils.printList(res, List::toString);
        PrintUtils.printList(tree.verticalTraversal1(root), List::toString);
    }

    //user treemap & treeset
    public List<List<Integer>> verticalTraversal1(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, TreeSet<Integer>>> nodes=new TreeMap<>();
        dfs(nodes, root, 0, 0);
        List<List<Integer>> res=new ArrayList<>();
        for (TreeMap<Integer, TreeSet<Integer>> map:nodes.values()){
            List<Integer> list=new ArrayList<>();
            for(TreeSet<Integer> set:map.values())
                    list.addAll(set);
            res.add(list);
        }
        return res;
    }

    void dfs(TreeMap<Integer, TreeMap<Integer, TreeSet<Integer>>> nodes, TreeNode node, int X, int Y){
        if(node==null) return;
        TreeMap<Integer, TreeSet<Integer>> map=nodes.getOrDefault(X, new TreeMap<>(Comparator.reverseOrder()));
        TreeSet<Integer> set=map.getOrDefault(Y, new TreeSet<>());
        set.add(node.val);
        map.put(Y, set);
        nodes.put(X, map);
        dfs(nodes, node.left, X-1, Y-1);
        dfs(nodes, node.right, X+1, Y-1);
    }

    class TreeNode2 extends TreeNode{
        public int X;
        public int Y;

        public TreeNode2(int x1, int x, int y) {
            super(x1);
            X = x;
            Y = y;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<TreeNode2> nodes=new ArrayList<>();
        getPosition(nodes, root, 0, 0);
//        PrintUtils.printList(nodes, p->"["+p.X+", "+p.Y+", val="+p.val+"]");
        nodes.sort((p1,p2)-> p1.X==p2.X?(p2.Y==p1.Y?(p1.val-p2.val):(p2.Y-p1.Y)):(p1.X-p2.X));
//        PrintUtils.printList(nodes, p->"["+p.X+", "+p.Y+", val="+p.val+"]");
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> list=null;
        int recentX=0;
        for(TreeNode2 node: nodes){
            if(list==null || recentX!=node.X){
                if(list!=null) res.add(list);
                list=new ArrayList<>();
                list.add(node.val);
                recentX=node.X;
            }else{
                list.add(node.val);
            }
        }
        res.add(list);
        return res;
    }
    void getPosition(List<TreeNode2> nodes, TreeNode node, int X, int Y){
        if(node==null) return;
        nodes.add(new TreeNode2(node.val, X , Y));
        getPosition(nodes, node.left, X-1, Y-1);
        getPosition(nodes, node.right, X+1, Y-1);
    }
}
