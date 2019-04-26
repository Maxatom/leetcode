package leetcode.realtest.realTest20190421;

import utils.PrintUtils;

import java.util.*;

/**
 * @author shibing
 * @since 2019/4/21 10:38
 */
public class MatrixCellsinDistanceOrder {
    public static void main(String[] args) {
        MatrixCellsinDistanceOrder order=new MatrixCellsinDistanceOrder();
        int R = 1, C = 2, r0 = 0, c0 = 0;
//        R = 2; C = 2; r0 = 0; c0 = 1;
//        R = 2; C = 3; r0 = 1; c0 = 2;
//        R = 1; C = 1; r0 = 0; c0 = 0;
//        R=1; C=2; r0=0; c0=0;
//        System.out.println("result----");
//        PrintUtils.print2DIntArray(order.allCellsDistOrder(R,C,r0,c0));
        System.out.println("result2----");
        PrintUtils.print2DIntArray(order.allCellsDistOrder1(R,C,r0,c0));
//        System.out.println("result3----");
//        PrintUtils.print2DIntArray(order.allCellsDistOrder2(R,C,r0,c0));
        System.out.println("result4----");
        PrintUtils.print2DIntArray(order.allCellsDistOrder3(R,C,r0,c0));
    }

    //Counting sort
    public int[][] allCellsDistOrder3(int R, int C, int r0, int c0) {
        int[] counts=new int[R+C+1];
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++) {
                int dist=Math.abs(i-r0)+Math.abs(j-c0);
                counts[dist+1]++;
            }
        for (int i = 1; i < counts.length; i++)
            counts[i] += counts[i - 1];
        int[][] res=new int[R*C][];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int dist=Math.abs(i-r0)+Math.abs(j-c0);
                res[counts[dist]]=new int[]{i,j};
                counts[dist]++;
            }
        }
        return res;
    }
    //BFS
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int[][] coordinates=new int[R*C][2];
        boolean[][] has=new boolean[R][C];
        Queue<int[]> queue=new ArrayDeque<>();
        coordinates[0]=new int[]{r0,c0};
        queue.add(coordinates[0]);
        has[r0][c0]=true;
        int[][] dire=new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        int i=1;
        int[] cell;
        while ((cell=queue.poll())!=null){
            for(int[] dr:dire){
                int r=cell[0]+dr[0] , c=cell[1]+dr[1];
                if(r>=0 && r<R && c>=0 && c<C && !has[r][c]){
                    coordinates[i++]=new int[]{r,c};
                    queue.add(coordinates[i-1]);
                    has[r][c]=true;
                }
            }
        }
        return coordinates;
    }

    //Math, expanding
    public int[][] allCellsDistOrder1(int R, int C, int r0, int c0) {
        int[][] coordinates=new int[R*C][2];
        int i=0;
        for(int d=0; d<R+C; d++){
            for(int x=-d; x<=d; x++){
                int r = r0+x, c1= c0+d-Math.abs(x), c2=c0-d+Math.abs(x);
                if(r>=0 && r<R){
                    if(c1>=0 && c1<C) coordinates[i++]=new int[]{r,c1};
                    if(c2!=c1 && c2>=0 && c2<C) coordinates[i++]=new int[]{r,c2};
                }
            }
        }
        return coordinates;
    }

    //O(NlogN) sort
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
        Arrays.sort(coordinates, Comparator.comparingInt(p->
                manhattan(p,new int[]{r0,c0})));
        return coordinates;
    }
    int manhattan(int[] p1, int[] p2){
        return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]);
    }
}
