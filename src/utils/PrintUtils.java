package utils;

import leetcode.common.ListNode;
import leetcode.common.TreeNode;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shibing
 * @since 2018-11-26 14:16:44
 **/
public class PrintUtils {

    //-----------------------------------Int array-----------------------------------------
    /**
     * 打印整数数组
     * @param array 数组
     */
    public static void printArray(int[] array){
        System.out.println(Arrays.toString(array));
    }
    public static void printArray(String tips, int[] array){
        System.out.print(tips);
        System.out.println(Arrays.toString(array));
    }
    public static void printArray(int[] array, int width){
        printArray(array, width, false);
    }
    public static void printIntArrayWithIndex(int[] array){
        printArray(array, -1, true);
    }
    /**
     * 打印整数数组
     * @param array 数组
     * @param width 每个数打印宽度
     * @param withIndex 索引
     */
    public static void printArray(int[] array, int width, boolean withIndex){
        if(array==null) System.out.println("null");
        int i=0;
        StringBuilder format=new StringBuilder("%").append(width).append("d");
        System.out.print("[");
        for (int t:array){
            if(withIndex) System.out.print(i+":");
            if(width!=-1) System.out.printf(format.toString(),t);
            else System.out.print(t);
            if(i!=array.length-1) System.out.print(", ");
            i++;
        }
        System.out.println("]");
    }

    public static void print2DIntArray(int[][] a, boolean rowNum){
        print2DIntArray(a, -1, false, true);
    }
    public static void print2DIntArray(int[][] a){
        print2DIntArray(a, -1, false);
    }
    public static void print2DIntArray(int[][] a, int width){
        print2DIntArray(a, width, false);
    }
    public static void print2DIntArray(int[][] a, int width, boolean withIndex){
        print2DIntArray(a, width, withIndex, false);
    }
    /**
         * 打印二位整数数组
         * @param a 数组
         */
    public static void print2DIntArray(int[][] a, int width, boolean withIndex, boolean rowNum){
        if(a!=null) {
//            System.out.print("[");
            for (int i = 0; i < a.length; i++) {
                if(rowNum) System.out.print(i+": ");
                printArray(a[i], width, withIndex);
            }
//            System.out.println("]");
        }
    }

    //------------------------------------double array--------------------------------
    public static void printArray(double[] array, int width, boolean withIndex, int precision){
        if(array==null) System.out.println("null");
        int i=0;
        StringBuilder format=new StringBuilder("%").append(width).append(".").append(precision).append("f");
        System.out.print("[");
        for (double t:array){
            if(withIndex) System.out.print(i+":");
            if(width!=-1) System.out.printf(format.toString(),t);
            else System.out.print(t);
            if(i!=array.length-1) System.out.print(", ");
            i++;
        }
        System.out.println("]");
    }

    public static void print2DDoubleArray(double[][] a){
        print2DDoubleArray(a, 5, false, 2);
    }
    public static void print2DDoubleArray(double[][] a, int width, boolean withIndex, int precision){
        if(a!=null) {
//            System.out.print("[");
            for (double[] x : a) {
                printArray(x, width, withIndex, precision);
            }
//            System.out.println("]");
        }
    }

//-------------------------boolean array-----------------
    public static void printArray(boolean[] array) {
        printArray(array, false);
    }
    public static void printArrayWithIndex(boolean[] array) { printArray(array, true); }
    public static void printArray(boolean[] array, boolean withIndex) {
        if (array == null) System.out.println("null");
        int i = 0;
        System.out.print("[");
        for (boolean t : array) {
            if(withIndex) System.out.print(i+":");
            System.out.print(t?1:0);
            if (i != array.length - 1) System.out.print(", ");
            i++;
        }
        System.out.println("]");
    }
    public static void print2DArray(boolean[][] a){
        print2DArray(a, -1, false);
    }
    public static void print2DArray(boolean[][] a, int width, boolean withIndex){
        int i=0;
        StringBuilder format=new StringBuilder("%").append(width).append("d");
        for (boolean[] x: a){
            if(width!=-1) System.out.printf(format.toString(), i++);
            printArray(x, true);
        }
    }

    //----------------------------char array--------------------------
    public static void printArray(char[] array) {
        printArray(array, false);
    }
    public static void printArray(char[] array, boolean withIndex) {
        if (array == null) return;
        int i = 0;
        System.out.print("[");
        for (char t : array) {
            if(withIndex) System.out.print(i+":");
            System.out.print(t);
            if (i != array.length - 1) System.out.print(", ");
            i++;
        }
        System.out.println("]");
    }

    //----------------------------T array-------------------------------
    public static <T> void printArray(T[] array){
        printArray(array,",");
    }
    public static <T> void printArray(T[] array, String separator){
        printList(Arrays.stream(array).collect(Collectors.toList()), String::valueOf, separator);
    }
    public static <T> void printArray(T[] array,Function<T, String> f){
        printList(Arrays.stream(array).collect(Collectors.toList()),f, ",");
    }
    public static <T> void printList(List<T> list, Function<T, String> f){
        printList(list, f, ",");
    }
    public static <T> void printList(List<T> list, Function<T, String> f, String separator){
        printList(list, f, separator, false);
    }
    public static <T> void printList(List<T> list, Function<T, String> f, String separator, boolean withIndex){
        int i=0;
        System.out.print("[");
        for (T t:list){
            if(withIndex) System.out.print(i+":");
            System.out.print(f.apply(t));
            if(i!=list.size()-1) System.out.print(separator);
            i++;
        }
        System.out.println("]");
    }

    //---------------------------------3D Array-----------------------------------
    public static void print3DArray(int[][][] arr){
        print3DArray(arr, false);
    }
    public static void print3DArray(int[][][] arr, boolean withIndex){
        int m=arr.length, n=arr[0].length, k=arr[0][0].length;
        for (int i = 0; i < m; i++) {
            System.out.print("[");
            for (int j = 0; j < n; j++) {
                System.out.print("[");
                for (int l = 0; l < k; l++) {
                    if(withIndex)
                        System.out.printf("%d:",l);
                    System.out.print(arr[i][j][l]);
                    if(l<k-1) System.out.print(",");
                }
                System.out.print("]");
            }
            System.out.println("]");
        }
    }

    //-----------------------------------String converting------------------------------------
    public static TreeNode convertStringToBinaryTree(String s){
        Queue<TreeNode> noChildren=new LinkedList<>();
        if(s==null) return null;
        if(s.equals("")||s.equals("[]")) return null;
//        int[] result=new int[s.split(",").length];
        Pattern pattern1 = Pattern.compile("(?<=\\[).*?(?=])");
        Matcher matcher1 =pattern1.matcher(s);
//        int i=0;
        TreeNode root=null;
        if(matcher1.find()){
            String[] strs=matcher1.group().split(",");
            if(strs.length==1&&strs[0].equals("")) return null;
            else {
                int j=0, k=0;
                TreeNode node = null;
                for(String se:strs) {
                    se=se.trim();
                    if(j!=0&&node==null&&noChildren.size()==0) return root;
                    else if(node==null&&noChildren.size()!=0) {node=noChildren.poll(); k=0;}
                    if (se.equals("null")){
                        if(j==0){
                            root=null;
                            node=null;
                        } else {
                            if(k==0) {node.left=null; k++;}
                            else if(k==1) {node.right=null; node=null;}
                        }
                    }else {
                        if(j==0){
                            node=new TreeNode(Integer.valueOf(se.trim()));
                             noChildren.add(node);
                             root=node;
                             node=null;
                        }else {
                            if(k==0) {node.left=new TreeNode(Integer.valueOf(se.trim())); noChildren.add(node.left); k++;}
                            else if(k==1) {node.right=new TreeNode(Integer.valueOf(se.trim())); noChildren.add(node.right); node=null;}
                        }
                    }
                    j++;
                }
            }
        }
        return root;
    }
    /**
     * 将String转为数组
     * ["ghi","def","abc"]
     * @param s 输入字符串
     * @return 字符串数组
     */
    public static String[] convertStringToStringArray(String s) {
        if (s == null) return null;
        if (s.equals("") || s.equals("[]")) return new String[0];
        String[] result = new String[s.split(",").length];
        Pattern pattern1 = Pattern.compile("(?<=\\[).*?(?=])");
        Matcher matcher1 = pattern1.matcher(s);
        int i = 0;
        if (matcher1.find()) {
            String[] strs = matcher1.group().split(",");
            if (strs.length == 1 && strs[0].equals("")) return result;
            else {
                for (String se : strs) result[i++] = se.replace("\"", "").trim();
            }
        }
        return result;
    }
    public static int[] convertStringToIntArray(String s){
        String[] sarr=convertStringToStringArray(s);
        int[] iarr=new int[sarr.length];
        for (int i = 0; i < sarr.length; i++)
            iarr[i]=Integer.valueOf(sarr[i]);
        return iarr;
    }
    /**
     * 将字符传转换成二维数组
     * 数组示例 [[2,3,4],[3,4,5]]
     *
     * @param s 字符串
     * @return 返回数组
     */
    public static int[][] convertStringTo2DIntArray(String s){
        int[][] result=new int[s.split("]\\s*,").length][];
        Pattern pattern = Pattern.compile("(?<=\\[).*(?=])");
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()){
            int i=0;
            Pattern pattern1 = Pattern.compile("(?<=\\[).*?(?=])");
            Matcher matcher1 =pattern1.matcher(matcher.group());
            while (matcher1.find()){
                String[] strs=matcher1.group().split(",");
                if(strs.length==1&&strs[0].equals("")) result[i]=new int[0];
                else {
                   result[i]=new int[strs.length];
                    int j=0;
                    for(String se:strs) result[i][j++]=Integer.valueOf(se);
                }
                i++;
            }
        }
        return result;
    }
    public static ListNode convertArrayToListNode(int[] A){
        if(A==null || A.length==0) return null;
        ListNode head=new ListNode(A[0]);
        ListNode node=head;
        for (int i = 1; i < A.length; i++) {
            node.next=new ListNode(A[i]);
            node=node.next;
        }
        return head;
    }
    /**
     * 从文件读取一维数组
     * @param filePath 文件路径
     * @return 数组
     * @throws IOException  IO异常
     */
    public static int[] readArrayFromFile(String filePath) throws IOException {
        int[] array;
        try(Stream<String> lines= Files.lines(Paths.get(filePath) ,Charset.defaultCharset())){
            array=lines.map(PrintUtils::convertStringToIntArray).collect(Collectors.toList()).get(0);
        }
        return array;
    }

    public static void printListNode(ListNode listNode) {
        System.out.print("(");
        while (listNode!=null){
            System.out.print(listNode.val);
            if(listNode.next!=null) System.out.print("->");
            listNode=listNode.next;
        }
        System.out.println(")");
    }
}
