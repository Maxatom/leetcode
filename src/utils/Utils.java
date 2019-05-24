package utils;

import leetcode.common.TreeNode;

import java.util.*;

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

    /**
     * quickSort
     * @param a
     * @param c
     * @param <T> type
     */
    public static <T> void quickSort(T[] a, Comparator<? super T> c) {
        quickSort0(a, 0, a.length - 1, c);
    }
    private static <T> void quickSort0(T[] A, int left, int right, Comparator<? super T> c) {
        if (left >= right) return;
        T x = A[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && c.compare(A[j], x) ==1 ) j--;
            if (i < j) A[i++] = A[j];
            while (i < j && c.compare(A[i] , x)==-1) i++;
            if (i < j) A[j--] = A[i];
        }
        A[i] = x;
        quickSort0(A, left, i - 1, c);
        quickSort0(A, i + 1, right, c);
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

    /**
     * find all occurrence of s1 in s
     * @param s
     * @param s1
     * @return  the indexes of all occurrence
     */
    public static List<Integer> allOccurence(String s, String s1){
        int idx, fromIndex=0, len=s.length();
        List<Integer> res=new ArrayList<>();
        while ((idx=sunday(s, s1, fromIndex, len))!=-1){
            res.add(idx);
            if(idx+s1.length()>=s.length()) return res;
            int j=s1.length()-1; char c= s.charAt(idx+s1.length());
            while (j>=0 && c!=s1.charAt(j)) j--;
            fromIndex = idx + s1.length() - j;
            len = s.length() - fromIndex;
        }
        return res;
    }

    public static int sunday(String s, String s1){
        return sunday(s, s1, 0, s.length());
    }

    /**
     *  Return the index with s of the first  occurrence of s1, starting at a specified index.
     *
     * @param s  the string to be searched in
     * @param s1 the substring to search for
     * @param fromIndex  the index from which to start search
     * @param length  length
     * @return the index of the first occurrence of s1, starting at a specified index
     *   or -1 if there is no such occurrence
     */
    public static int sunday(String s, String s1, int fromIndex, int length){
        if(s1.length()>length) return -1;
        char[] schars=s.toCharArray();
        char[] s1chars=s1.toCharArray();
        int targetIndex=fromIndex+length;
        for (int i = fromIndex; i < targetIndex; i++) {
            if(i+s1.length()>targetIndex) return -1;
            int k=i;
            for (int j = 0; j <s1.length() && schars[k]==s1chars[j]; j++, k++);
            if(k==i+s1chars.length) return i;
            int k1=s1chars.length-1, next=i+s1chars.length;
            if(next>=schars.length) return -1;
            while (k1>=0 && s1chars[k1]!=schars[next]) k1--;
            i+=s1chars.length-k1-1;
        }
        return -1;
    }
}
