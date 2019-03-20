package leetcode.realtest.realTest20181223;

import utils.ArrayGenerator;
import utils.ArrayGenerator;
import utils.PrintUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author shibing
 * @since 2018/12/23 10:50
 */
public class MaximumWidthRamp {
    public static void main(String[] args) throws IOException {
        MaximumWidthRamp ramp=new MaximumWidthRamp();
        int[] testcase = ArrayGenerator.intArray(12, 0, 10);
        testcase=PrintUtils.convertStringToIntArray("[9,8,1,0,1,9,4,0,4,1]");
        testcase=PrintUtils.convertStringToIntArray("[6,0,8,2,1,5]");
        testcase=PrintUtils.convertStringToIntArray("[2,2]");
        String company="E:\\MyProjects\\springdemo\\testlearning\\src\\main\\java\\leetcode\\realTest20181223\\MaximumWidthRampInputData.txt";
        String apartment="E:\\MyProjects\\springdemo\\testlearning\\src\\main\\java\\leetcode\\realTest20181223\\MaximumWidthRampInputData.txt";
        testcase=PrintUtils.readArrayFromFile(company);
//        PrintUtils.printIntArray(testcase);
        long start=System.currentTimeMillis();
//        System.out.println(ramp.maxWidthRamp(testcase)+", time="+(System.currentTimeMillis()-start)+", times1="+ramp.times1);
        start=System.currentTimeMillis();
        System.out.println(ramp.maxWidthRamp2(testcase)+", time1="+(System.currentTimeMillis()-start));
        start=System.currentTimeMillis();
        System.out.println(ramp.maxWidthRamp3(testcase)+", time2="+(System.currentTimeMillis()-start));
    }


    //O(N)
    public int maxWidthRamp3(int[] A) {
        List<Integer> stack=new LinkedList<>();
        stack.add(0);
        for (int i = 0; i < A.length; i++)
            if(A[i]<A[stack.get(stack.size()-1)])
                stack.add(i);
        int res=0;
        for (int i = A.length-1; i > res ; i--) {
            while (stack.size()>0 && A[i]>=A[stack.get(stack.size()-1)])
                res=Math.max(res, i-stack.remove(stack.size()-1));
        }
        return res;
    }

    // stack O(NlogN)
    public int maxWidthRamp2(int[] A) {
        List<Integer> stack = new ArrayList<>();
        stack.add(0);
        int res = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[stack.get(stack.size() - 1)]) {
                stack.add(i);
            } else {
                int left = 0, right = stack.size() - 1, mid;
                while (left < right) {
                    mid = (left + right) / 2;
                    if (A[i] >= A[stack.get(mid)]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                res = Math.max(res, i - stack.get(left));
            }
        }
        return res;
    }

     //optimizing order
    public int maxWidthRamp1(int[] A) {
        Integer[] B = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = i;
        }
        Arrays.sort(B, Comparator.comparingInt(i -> A[i]));
        int maxWidth = 0;
        int minIndex = B[0];
        for (int i = 1; i < B.length; i++) {
            int width = B[i] - minIndex;
            if (width > maxWidth) maxWidth = width;
            minIndex = Math.min(minIndex, B[i]);
        }
        return maxWidth;
    }

    int times1=0;
    public int maxWidthRamp(int[] A) {
        int maxWidth=0;
        int width=0;
        for (int i = 0; i < A.length; i++) {
            if(A.length-i<maxWidth) break;
            for (int j = A.length-1; j > i; j--) {
                times1++;
                if(A[j]>=A[i]) { width=j-i; break;}
            }
            maxWidth=Math.max(width,maxWidth);
        }
        return maxWidth;
    }
}
