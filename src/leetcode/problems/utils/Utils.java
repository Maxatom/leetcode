package leetcode.problems.utils;

import utils.RandomGenerator;
import leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author shibing
 * @since 2018/11/16 22:32
 */
public class Utils {

    //最大值
    public static int max(int... x) {
        int max = x[0];
        for (int i = 1; i < x.length; i++)
            max = x[i] > max ? x[i] : max;
        return max;
    }

    //最小值
    public static int min(int... x) {
        int min = x[0];
        for (int i = 1; i < x.length; i++)
            min = x[i] < min ? x[i] : min;
        return min;
    }

    //返回T类型最大值
    public static <T> T max(Comparator comparator, T... ts) {
        T max = ts[0];
        for (int i = 1; i < ts.length; i++)
            max = comparator.compare(max, ts[i]) >= 0 ? max : ts[i];
        return max;
    }

    /**
     * 求最大公因数
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static List<List<Integer>> convert2DIntArrayToList(int[][] array) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] arr : array) {
            if (arr != null) {
                List<Integer> row = new ArrayList<>();
                for (int i : arr) {
                    row.add(i);
                }
                result.add(row);
            } else {
                result.add(null);
            }
        }
        return result;
    }

    //快速排序
    public static void quickSort(int[] A) {
        quickSort0(A, 0, A.length - 1);
    }

    //快速排序递归体
    public static void quickSort0(int[] A, int left, int right) {
        if (left >= right) return;
        int x = A[left], i = left, j = right;
        while (i < j) {
            while (i < j && A[j] > x) j--;
            if (i < j) A[i++] = A[j];
            while (i < j && A[i] < x) i++;
            if (i < j) A[j--] = A[i];
        }
        A[i] = x;
        quickSort0(A, left, i - 1);
        quickSort0(A, i + 1, right);
    }

    public static String generateString(int strlen, char min, char max) {
        RandomGenerator.String string = new RandomGenerator.String(strlen);
        string.setCharBound(min,(char)(max+1));
        return string.next();
    }

    //判断树是否相等 根节点值相等，并且左右子树都相等
    public static boolean isTreeEqual(TreeNode tree1, TreeNode tree2){
        if(tree1==tree2) return true;
        if(tree1==null||tree2==null||tree1.val!=tree2.val) return false;
        return isTreeEqual(tree1.left, tree2.left)&&isTreeEqual(tree1.right, tree2.right);
    }

    //复制一棵树
    public static TreeNode copyTree(TreeNode root){
        if(root==null) return null;
        TreeNode node=new TreeNode(root.val);
        node.left=copyTree(root.left);
        node.right=copyTree(root.right);
        return node;
    }
}
