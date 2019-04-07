package algorithmPracticeGuide;

import utils.PrintUtils;

/**
 * Given a triangle, find the path sum , whose last digit is biggest, from top to bottom.
 * Each step you may move to adjacent numbers on the row below.`
 * @author shibing
 * @since 2019/4/6 7:26
 */
public class triangleIII {
    public static void main(String[] args) {
        triangleIII triangleIII=new triangleIII();
        int[][] triangle=new int[][]{{1}, {3,2}, {4,10,1}, {4,3,2,20}};
        System.out.println(triangleIII.biggestLastDigitsSum(triangle));
    }





    //dp(i, j, k)=true means sub triangle with top point (i,j) have a sum whose last digit is k;
    //dp(i,j,k) = t+ A(i,j) and dp(i+1,j,t) or dp(i+1,j+1,t) is true
    public int biggestLastDigitsSum(int[][] triangle){
        int M=triangle.length;
        boolean[][][] dp=new boolean[M][M][10];
//        for (int i = M-1; i >= 0; i--) {
//            for (int j = 0; j <= i; j++) {
//                if(i==M-1)
//                    dp[i][j][triangle[i][j]%10]= true;
//                else {
//                    for (int k = 0; k < 10; k++) {
//                        if (dp[i + 1][j][k] || j<i && dp[i+1][j+1][k])
//                            dp[i][j][(triangle[i][j]+k)%10] = true;
//                    }
//                }
//            }
//        }
        //更新法
        for (int i = M-1; i >= 1; i--) {
            for (int j = 0; j <= i; j++) {
                  for (int k = 0; k < 10; k++) {
                      if(i==M-1)
                          dp[i][j][triangle[i][j]%10]=true;
                      if (dp[i][j][k]) {
                          if(j<i) dp[i - 1][j][(triangle[i - 1][j] + k) % 10]  = true;
                          if(j>0) dp[i - 1][j - 1][(triangle[i - 1][j - 1] + k) % 10] =true;
                      }
                  }
            }
        }

        int max=0;
        for (int k = 0; k < 10; k++) {
            if (dp[0][0][k])
                max=Math.max(max, k);
        }
        return max;
    }
}
