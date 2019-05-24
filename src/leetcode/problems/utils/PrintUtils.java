package leetcode.problems.utils;

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
    /**
     * 打印整数数组
     * @param array 数组
     */
    public static void printArray(int[] array){
        System.out.println(Arrays.toString(array));
    }

    public static void printIntArray(String tips,int[] array){
        System.out.print(tips);
        printIntArray(array, -1, false);
    }
    public static void printIntArray(int[] array, int width){
        printIntArray(array, width, false);
    }

    public static void printIntArrayWithIndex(int[] array){
        printIntArray(array, -1, true);
    }

    /**
     * 打印整数数组
     * @param array 数组
     * @param width 每个数打印宽度
     * @param withIndex 索引
     */
    public static void printIntArray(int[] array, int width, boolean withIndex){
        if(array==null) return;
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

    public static void print2DIntArray(int[][] a){
        print2DIntArray(a, -1, false);
    }
    public static void print2DIntArray(int[][] a, int width){
        print2DIntArray(a, width, false);
    }
    /**
         * 打印二位整数数组
         * @param a 数组
         */
    public static void print2DIntArray(int[][] a, int width, boolean withIndex){
        if(a!=null) {
//            System.out.print("[");
            for (int[] x : a) {
                if (x == null){
                    System.out.println("null");
                }else if (x.length==0) {
                    System.out.println("[]");
                }else {
                    printIntArray(x, width, withIndex);
                }
            }
//            System.out.println("]");
        }
    }

    public static void print2DArray(boolean[][] a){
        for (boolean[] x: a){
            printArray(x);
        }
    }

    public static void printArray(boolean[] array) {
        if (array == null) return;
        int i = 0;
        System.out.print("[");
        for (boolean t : array) {
            System.out.print(t?1:0);
            if (i != array.length - 1) System.out.print(", ");
            i++;
        }
        System.out.println("]");
    }

    public static <T> void printArray(T[] array){
        printArray(array,",");
    }

    public static <T> void printArray(T[] array, String separator){
        int i=0;
        System.out.print("[");
        for (T t:array){
            System.out.print(t);
            if(i!=array.length-1) System.out.print(separator);
            i++;
        }
        System.out.println("]");
    }
    public static <T> void printArray(T[] array,Function<T, String> f){
        int i=0;
        System.out.print("[");
        for (T t:array){
            System.out.print(f.apply(t));
            if(i!=array.length-1) System.out.print(", ");
            i++;
        }
        System.out.println("]");
    }

    public static <T> void printList(List<T> list, Function<T, String> f){
        int i=0;
        System.out.print("[");
        for (T t:list){
            System.out.print(f.apply(t));
            if(i!=list.size()-1) System.out.print(", ");
            i++;
        }
        System.out.println("]");
    }

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
        if(s==null) return null;
        if(s.equals("")||s.equals("[]")) return new int[0];
        int[] result=new int[s.split(",").length];
            Pattern pattern1 = Pattern.compile("(?<=\\[).*?(?=])");
            Matcher matcher1 =pattern1.matcher(s);
            int i=0;
            if(matcher1.find()){
                String[] strs=matcher1.group().split(",");
                if(strs.length==1&&strs[0].equals("")) return result;
                else {
                    for(String se:strs) result[i++]=Integer.valueOf(se.trim());
                }
            }
        return result;
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



    /**
     * 从文件读取一维数组
     * @param filePath 文件路径
     * @return 数组
     * @throws IOException
     */
    public static int[] readArrayFromFile(String filePath) throws IOException {
        int[] array;
        try(Stream<String> lines= Files.lines(Paths.get(filePath) ,Charset.defaultCharset())){
            array=lines.map(p->convertStringToIntArray(p)).collect(Collectors.toList()).get(0);
        }
        return array;
    }
}
