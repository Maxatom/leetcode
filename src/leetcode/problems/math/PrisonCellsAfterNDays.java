package leetcode.problems.math;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * @author shibing
 * @since 2018/12/22 15:17
 */
public class PrisonCellsAfterNDays {
    public static void main(String[] args) throws Exception {
        PrisonCellsAfterNDays days = new PrisonCellsAfterNDays();
        int[] testcase = PrintUtils.convertStringToIntArray("[0,1,0,1,1,0,0,1]");
//        testcase=PrintUtils.convertStringToIntArray("[1,0,0,1,0,0,1,0]");
        testcase=PrintUtils.convertStringToIntArray("[0, 1, 0, 0, 0, 0, 1, 1]");
        for (int i = 2; i < (1<<11)-1; i++) {
//            testcase = days.generate(9);
            testcase=days.intToArray(i, 10);
            int[] testcase1 = Arrays.copyOf(testcase, testcase.length);
            int[] testcase2 = Arrays.copyOf(testcase, testcase.length);
            int N = 100;
            int[] result;
//            PrintUtils.printArray("testcase:",testcase);
//            System.out.println("num=" + days.arrayToInt(testcase));
//            System.out.println("-------------------begin");
            long start = System.currentTimeMillis();
//            result= days.prisonAfterNDays(testcase, N);
//            int reuslt0 = days.arrayToInt(result);
//            System.out.println("-------------------time=" + (System.currentTimeMillis() - start) + ",  result0=" + reuslt0);
//                PrintUtils.printArray(result);

//            System.out.println("-------------------1---begin");
//            start = System.currentTimeMillis();
//            result = days.prisonAfterNDays1(testcase1, N);
//            int result1 = days.arrayToInt(result);
//            System.out.println("-------------------time1=" + (System.currentTimeMillis() - start) + ",  result1=" + result1);
//                PrintUtils.printArray(result);

//            System.out.println("-------------------2---begin");
            start = System.currentTimeMillis();
            result = days.prisonAfterNDays2(testcase2, N);
            int result2 = days.arrayToInt(result);
//            System.out.println("-------------------time2=" + (System.currentTimeMillis() - start) + ",  result=" + result2);
//            PrintUtils.printArray(result);

//            if (result1 != result2) throw new Exception("Not Equal");
        }

    }

    //optimizer  O(1)
    //if a number begins with 0 and ends with 0 , it can be itself after some steps
    public int[] prisonAfterNDays2(int[] cells, int N) throws Exception {
        int[] medians = new int[1 << cells.length];
        int count = 0;
        int num = arrayToInt(cells);
        int max = (1 << (cells.length - 1)) - 2;
        if(num<max && (num&1)==0) medians[count++]=num;
        for (int i = 0; i < N; i++) {
            num = (~(num << 1) ^ (num >> 1)) & max;
//            PrintUtils.printArray("num="+num+", j=:"+(count-1), intToArray(num, cells.length));

            //与上一个数相等，退出
            if (count != 0 && num == medians[count - 1]) {
//                System.out.println("count=" + (count - 1));
                break;
            }
            if (count>0 && num == medians[0]) {
//                System.out.println(" num=" + num);
                num = medians[(N - i - 1) % count ];
                break;
            }
            medians[count++] = num;
        }

        System.out.println("count=" + count);
        return intToArray(num, cells.length);
    }
        //Optimize transfer to number
    public int[] prisonAfterNDays1(int[] cells, int N) throws Exception {
        int[] medians=new int[1<<cells.length];
        int count=0;
        int num=arrayToInt(cells);
//        medians[count++]=num;
        int max=(1<<(cells.length-1))-2;
        for (int i = 0; i < N; i++) {
            num=(~(num<<1)^(num>>1))&max;
//            System.out.println(num);
//            PrintUtils.printArray("j=:",intToArray(num, cells.length));

            if(count!=0&&num==medians[count-1]){
                System.out.println("count="+(count-1));
                break;
            }
            int j;
            for (j = 0; j < count; j++) {
                if(num==medians[j])  break;
            }
            if(j!=count){
                System.out.println(" j="+j+" , num="+num);
                int T=i-j;//cycle
                num= medians[(N-i-1)%T+j];
                break;
            } else medians[count++]=num;
        }
        for (int i = cells.length-1; i >=0; i--) {
            cells[i]=num&1;
            num>>=1;
        }
        return cells;
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] cellNext=new int[cells.length];
        for (int i = 0; i < N; i++) {
            cellNext[0]=0;cellNext[cellNext.length-1]=0;
            for (int j = 1; j < cells.length-1; j++) {
                cellNext[j]=1-(cells[j-1]^cells[j+1]);
            }
//            if(i>N-10)
//                PrintUtils.printArray(cellNext);
//            System.out.println(arrayToInt(cellNext));
            int[] temp=cells;
            cells=cellNext;
            cellNext=temp;
        }
        return cells;
    }

    int arrayToInt(int[] cells){
        int num=0;
        for (int j = 0; j < cells.length; j++) {
            num=(num<<1)|cells[j];
        }
        return num;
    }

    int[] intToArray(int num, int length){
        int[] result=new int[length];
        for (int i = length-1; i >=0; i--) {
            result[i]=num&1;
            num>>=1;
        }
        return result;
    }

    int[] generate(int length){
        int[] result=new int[length];
        Random random=new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            result[i]=random.nextInt(2);
        }
        return result;
    }
}
