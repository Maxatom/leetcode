package leetcode.realtest.realTest20190623;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/6/23 11:29
 */
public class CarPooling {
    public static void main(String[] args) {
        CarPooling carPooling=new CarPooling();
        int[][] trips = {{2,1,2},{3,3,7}}; int capacity = 4;
        trips = new int[][]{{2,1,5},{3,5,7}}; capacity = 3;
        trips=new int[][]{{4,3,4},{3,2,4},{1,8,9},{7,2,5}}; capacity=14;
        System.out.println(carPooling.carPooling(trips, capacity));
    }
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a,b)->a[1]-b[1]==0?a[2]-b[2]:a[1]-b[1]);
        int[] ends=new int[1001];
        int num=0, pre=0;
        for (int i = 0; i < trips.length; i++) {
            ends[trips[i][2]]+=trips[i][0];
            while (pre<=trips[i][1]) {
                num -= ends[pre];
                ends[pre]=0;
                pre++;
            }
            num+=trips[i][0];
            System.out.println("num="+num+", pre="+pre);
            PrintUtils.printArray(ends);
            if(num>capacity) return false;
        }
        return true;
    }
}
