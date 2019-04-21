package leetcode.realtest.realTest20190421;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author shibing
 * @since 2019/4/21 10:38
 */
public class MatrixCellsinDistanceOrder {
    public static void main(String[] args) {
        MatrixCellsinDistanceOrder order=new MatrixCellsinDistanceOrder();
        int R = 1, C = 2, r0 = 0, c0 = 0;
        R = 2; C = 2; r0 = 0; c0 = 1;
        R = 2; C = 3; r0 = 1; c0 = 2;
        R = 1; C = 1; r0 = 0; c0 = 0;
        System.out.println("result----");
        PrintUtils.print2DIntArray(order.allCellsDistOrder(R,C,r0,c0));
    }
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] coordinates=new int[R*C][2];
        int k=0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                coordinates[k][0]=i;
                coordinates[k][1]=j;
                k++;
            }
        }
        PrintUtils.print2DIntArray(coordinates);
        System.out.println("-----codinates end");
        Arrays.sort(coordinates, (p1,p2)-> manhattan(p1,new int[]{r0,c0})  - manhattan(p2, new int[]{r0, c0}));
        return coordinates;
    }
    int manhattan(int[] p1, int[] p2){
        return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]);
    }
}
