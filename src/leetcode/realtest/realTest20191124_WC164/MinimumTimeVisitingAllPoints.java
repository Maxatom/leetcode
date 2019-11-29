package leetcode.realtest.realTest20191124_WC164;

public class MinimumTimeVisitingAllPoints {
    public static void main(String[] args) {
        MinimumTimeVisitingAllPoints minTime=new MinimumTimeVisitingAllPoints();
        int[][] points = {{1,1},{3,4},{-1,0}};
        points=new int[][] {{3,2},{-2,2}};
        System.out.println(minTime.minTimeToVisitAllPoints(points));


    }
    public int minTimeToVisitAllPoints(int[][] points) {
        int n=points.length, min=0;
        for (int i = 1; i < n; i++) {
            min += Math.max(Math.abs(points[i][0]-points[i-1][0]) , Math.abs(points[i][1]-points[i-1][1]));
        }
        return min;
    }
}
