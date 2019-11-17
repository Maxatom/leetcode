package leetcode.realtest.realTest20191117_WT163;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class Shift2DGrid {
    public static void main(String[] args) {
        Shift2DGrid sgrid=new Shift2DGrid();
        int[][] grid = new int[][]{{1,2,3},{4,5,6},{7,8,9}}; int k = 1;
        grid = new int[][]{{3,8,1,9},{19,7,2,5},{4,6,11,10},{12,0,21,13}};  k = 4;
        grid = new int[][]{{1,2,3},{4,5,6},{7,8,9}}; k = 9;
       grid=new int[][]{{1},{2},{3},{4},{7},{6},{5}}; k=23;
       grid=new int[][]{{1},{2}}; k=19;
        PrintUtils.printList(sgrid.shiftGrid(grid,k), p->p.toString());
    }
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int M=grid.length, N=grid[0].length, L=M*N;
        int[] mid=new int[L];
        List<List<Integer>> res=new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                mid[i*N+j]=grid[i][j];
            }
        }
        k=k%mid.length;
        int[] tempArray=new int[L];
        if(k!=0){
            for (int i = 0; i < L; i++) {
                tempArray[(i+k)%L]=mid[(i)%L];
            }
            mid=tempArray;
        }
        for (int i = 0; i < M; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < N; j++) {
                res.get(i).add(mid[i*N+j]);
            }
        }
        return res;
    }
}
