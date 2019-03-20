package leetcode.problems.dynamicPrograming;

import utils.ArrayGenerator;
import utils.ArrayGenerator;
import utils.PrintUtils;
import utils.PrintUtils;
import utils.Utils;
import utils.Utils;

import java.util.List;

/**
 * @author Shibing
 * @since 2018-12-10 14:04:46
 **/
public class Triangle {
    public static void main(String[] args) throws Exception {
        Triangle triangle = new Triangle();
        String s = "[\n" +
                "     [2],\n" +
                "    [3,4],\n" +
                "   [6,5,7],\n" +
                "  [4,1,8,3]\n" +
                "]";
        s = s.replace("\n", "");
        int[][] testcase = PrintUtils.convertStringTo2DIntArray(s);
        List<List<Integer>> testCase = Utils.convert2DIntArrayToList(testcase);
//        System.out.println(triangle.minimumTotal(testCase));

        final int Lines = 10000;
        testcase = new int[Lines][];
        for (int i = 0; i < Lines; i++) {
            testcase[i] = ArrayGenerator.intArray(i + 1, 1, 10000);
        }
//        PrintUtils.print2DIntArray(testcase);
        testCase = Utils.convert2DIntArrayToList(testcase);
        long start = System.currentTimeMillis();
        System.out.println(triangle.minimumTotal2(testCase) + ", time2=" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(triangle.minimumTotal1(testCase) + ", time1=" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(triangle.minimumTotal3(testCase) + ", time3=" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(triangle.minimumTotal5(testCase) + ", time5=" + (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        System.out.println(triangle.minimumTotal(testCase) + ", time=" + (System.currentTimeMillis() - start));

    }

    //优化转换成二维数组 space O(N^2)
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int[][] result = new int[triangle.size()][];
        int i = 0;
        for (List<Integer> row : triangle) {
            int j = 0;
            result[i] = new int[row.size()];
            for (Integer elem : row) {
                result[i][j++] = elem;
            }
            i++;
        }
        for (int j = result.length - 2; j >= 0; j--) {
            for (int k = 0; k < result[j].length; k++) {
                result[j][k] = result[j][k] + Math.min(result[j + 1][k], result[j + 1][k + 1]);
            }
        }
        return result[0][0];
    }

    //优化使用滚动数组记录中间结果 space O(N) time O(N^2)
    public int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        final int SIZE = triangle.size();
        int[] result = new int[SIZE];
        int[] nextResult = new int[SIZE];
        int min = Integer.MAX_VALUE;
        int i = 1;
        for (List<Integer> row : triangle) {
            int j = 0;
            min = Integer.MAX_VALUE;
            for (Integer elem : row) {
                if (j > 0 && j < row.size() - 1)
                    nextResult[j] = elem + Math.min(result[j - 1], result[j]);
                else if (j == 0) nextResult[j] = elem + result[j];
                else nextResult[j] = elem + result[j - 1];
                if (i == SIZE)
                    min = Math.min(min, nextResult[j]);
                j++;
            }
            i++;
            int[] temp = result;
            result = nextResult;
            nextResult = temp;
        }
        return min;
    }

    //DP space O(1) 主要耗时在List.set方法
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        long time1 = 0;
        long time2 = 0;
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> rowNext = triangle.get(i + 1);
            List<Integer> row = triangle.get(i);
            int j = 0;
            while (j < row.size()) {
                long start = System.currentTimeMillis();
                int value = row.get(j) + Math.min(rowNext.get(j), rowNext.get(j + 1));
                time1 += System.currentTimeMillis() - start;
                start = System.currentTimeMillis();
                row.set(j, value);
                time2 += System.currentTimeMillis() - start;
                j++;
            }
        }
        System.out.println(" time1=" + time1 + ", time2=" + time2);
        return triangle.get(0).get(0);
    }
    //优化， 去掉list.set操作
    public int minimumTotal3(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0) return 0;
        int[] result=new int[triangle.size()+1];
        for (int i=triangle.size()-1;i>=0;i--){
            List<Integer> row=triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                result[j]=row.get(j)+Math.min(result[j],result[j+1]);
            }
        }
        return result[0];
    }

    public int minimumTotal5(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0) return 0;
        int[] result=new int[triangle.size()+1];
        for (int i=triangle.size()-1;i>=0;i--){
            List<Integer> row=triangle.get(i);
            int j=0;
            for (Integer elem:row){
                result[j]=elem+Math.min(result[j],result[j+1]);
                j++;
            }
        }
        return result[0];
    }
}
