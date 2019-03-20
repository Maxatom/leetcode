package leetcode.problems.dynamicPrograming;

/**
 * @author shibing
 * @since 2018/12/2 17:02
 */
public class ClimbingStairs {
    public static void main(String[] args) throws Exception {
        ClimbingStairs stairs=new ClimbingStairs();
//        int[] testCases=ArrayGenerator.intArrayDinstinct(10,0,20);
        int[] testCases=new int[]{0,1,2,3,4,5, 100};
        for (int x=1;x<100;x++) {
            System.out.printf("test=%d, result=%d , max=%d\n", x, stairs.climbStairs(x), Integer.MAX_VALUE);
        }
    }
    //动态规划  f(n)=f(n-1)+f(n-2)  状态转移方程
    public int climbStairs(int n) {
        if(n<3) return n;
        int n1=1;
        int n2=2;
        int result=2;
        for (int i = 3; i <= n; i++) {
            result=n2+n1;
            n1=n2;
            n2=result;
        }
        return result;
    }
}
