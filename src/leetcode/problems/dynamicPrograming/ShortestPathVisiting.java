package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisiting {
    public static void main(String[] args) {
        ShortestPathVisiting path=new ShortestPathVisiting();
        int[][] graph={{1,2,3},{0},{0},{0}};
        graph=new int[][]{{1},{0,2,4},{1,3,4},{2},{1,2}};
        graph=new int[][]{{2,3,5,7},{2,3,7},{0,1},{0,1},{7},{0},{10},{9,10,0,1,4},{9},{7,8},{7,6}};
        System.out.println(path.shortestPathLength(graph));
    }

    //BFS  state=(cover, head) next state=( cover | (1<<child), child)
    public int shortestPathLength(int[][] graph) {
        Queue<int[]> queue= new LinkedList<>();
        int n=graph.length;
        int[][] res=new int[1<<n][n];

        int s=0;
//        while (true){
//            for (int i = 0; i < (1<<n); i++) {
//                for (int j = 0; j < n; j++) {
////                    res[i][j]
//                }
//            }
//        }

        return 0;
    }

}
