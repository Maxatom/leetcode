package leetcode.realtest.realTest20190505;

/**
 * @author Shibing
 * @since 2019-05-05 10:34:27
 **/
public class ValidBoomerang {
    public static void main(String[] args) {
        ValidBoomerang rang=new ValidBoomerang();
        int[][] points={{1,1},{2,3},{3,2}};
        points=new int[][]{{1,1},{2,2},{3,3}};
        points=new int[][]{{1,1},{1,1},{3,3}};
        points=new int[][]{{1,1},{1,1},{1,1}};
        points=new int[][]{{0,0},{0,0},{0,0}};
        points=new int[][]{{1,0},{1,1},{1,0}};
        System.out.println(rang.isBoomerang(points));
    }
    public boolean isBoomerang(int[][] points) {
        int dx1=points[0][0]-points[1][0];
        int dx2=points[1][0]-points[2][0];
        int dx3=points[0][0]-points[2][0];
        int dy1=points[0][1]-points[1][1];
        int dy2=points[1][1]-points[2][1];
        int dy3=points[0][1]-points[2][1];
        if(dx1==0 && dy1==0 || dx2==0 && dy2==0 || dx3==0 && dy3==0) return false;
        if((double)dy2/dx2==(double)dy1/dx1) return false;
        return true;
    }
}
