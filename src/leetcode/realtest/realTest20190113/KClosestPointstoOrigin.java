package leetcode.realtest.realTest20190113;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author shibing
 * @since 2019/1/13 10:31
 */
public class KClosestPointstoOrigin {
    public static void main(String[] args) {
        KClosestPointstoOrigin origin=new KClosestPointstoOrigin();
        int[][] points = new int[][]{{1,3},{-2,2}}; int K = 1;
//        points =new int[][]{{3,3},{5,-1},{-2,4}}; K = 2;
//        points =new int[][]{{10000,10000}}; K = 1;
//        points =new int[][]{{10000,10000},{1,1},{1,-9},{0,0},{0,1},{-1,-1},{1,2}}; K = 5;
//        points=new int[][]{{-2,10},{-4,-8},{10,7},{-4,-7}}; K=3;
//        points=new int[][]{{-5,4},{-6,-5},{4,6}}; K=2;
        points=new int[][]{{9,0},{7,10},{-4,-2},{3,-9},{9,1},{-5,-1}}; K= 5;
//        int[][] result=origin.kClosest(points,K);
        int[][] result1=origin.kClosest1(points,K);
        PrintUtils.print2DIntArray(result1);
//        for (int i = 0; i < 20; i++)
            PrintUtils.print2DIntArray(origin.kClosest2(points,K));
    }

    //divide and conquer
    public int[][] kClosest2(int[][] points, int K) {
        devideAndConquer(points, K, 0, points.length-1);
        return Arrays.copyOf(points,K);
    }

    void devideAndConquer(int[][] points, int K, int left, int right){
        if(left>=right) return;
        int rand=(left+right)/2;
        int i=left,j=right;
        int distrand=dist(points[rand]);
        while (i<j){
            while (i<j&& dist(points[i]) < distrand) i++;
            while (i<j&& dist(points[j]) > distrand) j--;
            int[] temp=points[j];
            points[j]=points[i];
            points[i]=temp;
        }
        if(i==K) return;
        if(i>K) devideAndConquer(points, K, left, i-1);
        else devideAndConquer(points, K, i+1, right);
    }
    int dist(int[] a){
        return a[0]*a[0]+a[1]*a[1];
    }



    // optimizing code
    public int[][] kClosest1(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparing(p-> p[0]*p[0]+p[1]*p[1]));
        return Arrays.copyOf(points,K);
    }


    //O(NlogN)
    public int[][] kClosest(int[][] points, int K) {
        Integer[] distance=new Integer[points.length];
        for (int i = 0; i <points.length ; i++) {
            distance[i]=points[i][0]*points[i][0]+points[i][1]*points[i][1];
        }
        Integer[] B=new Integer[points.length];
        for (int i = 0; i < B.length; i++) {
            B[i]=i;
        }
        Arrays.sort(B, (i,j)->distance[i]-distance[j]);
        int[][] result=new int[K][2];
        for (int i = 0; i < K; i++) {
            result[i]=points[B[i]];
        }
        return result;
    }
}
