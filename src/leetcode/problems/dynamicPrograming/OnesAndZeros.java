package leetcode.problems.dynamicPrograming;

import utils.ThreeTuple;
import utils.ThreeTuple;
import utils.Tuple;
import utils.Tuple;

import java.util.Random;

/**
 * @author shibing
 * @since 2018/12/10 21:12
 */
public class OnesAndZeros {
    public static void main(String[] args) throws InterruptedException {
        OnesAndZeros onesAndZeros=new OnesAndZeros();
        String[] strs=new String[]{"10", "0001", "111001", "1", "0"};
        String[] strs1=new String[]{"10", "0", "1"};
        String[] s=new String[]{"0","11","1000","01","0","101","1","1","1","0","0","0","0","1","0","0110101","0","11","01","00","01111","0011","1","1000","0","11101","1","0","10","0111"};
//        ThreeTuple<String[], Integer, Integer> testCase1=Tuple.tuple(strs, 5, 3);
//        ThreeTuple<String[], Integer, Integer> testCase1=Tuple.tuple(strs1, 1, 1);
//        ThreeTuple<String[], Integer, Integer> testCase1=Tuple.tuple(s, 9, 80);
        ThreeTuple<String[], Integer, Integer> testCase1=Tuple.tuple(onesAndZeros.generate(10000), 100, 210);
//        PrintUtils.printArray(onesAndZeros.generate(10));


       long start=System.currentTimeMillis();
        System.out.println(onesAndZeros.findMaxFormDP(testCase1.first, testCase1.second, testCase1.third)+", time="+(System.currentTimeMillis()-start));

//       start=System.currentTimeMillis();
//        System.out.println(onesAndZeros.findMaxForm(testCase1.first, testCase1.second, testCase1.third)+", time="+(System.currentTimeMillis()-start));

        start=System.currentTimeMillis();
        System.out.println(onesAndZeros.findMaxFormDP1(testCase1.first, testCase1.second, testCase1.third)+", time="+(System.currentTimeMillis()-start));
    }


    public int findMaxFormDP1(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        long time=0;
        long time1=0;
        for (int k = 0; k < strs.length; k++) {
            int n0 = 0;
            int n1;
            long start=System.currentTimeMillis();
            for (char c : strs[k].toCharArray()) {
                if (c == '0') n0++;
            }
            n1 = strs[k].length() - n0;
            time+=System.currentTimeMillis()-start;

            start=System.currentTimeMillis();
            for (int i = m; i >=n0; i--) {
                for (int j = n; j >= n1; j--) {
                    if (i >= n0 && j >= n1)
                        dp[i][j] = Math.max(dp[i][j], dp[i - n0][j - n1] + 1);
                    else dp[i][j] = dp[i][j];
                }
                if(k==strs.length) break;
            }
            time1+=System.currentTimeMillis()-start;
        }
//        PrintUtils.print2DIntArray(dp);
        System.out.println("time="+time+", time1="+time1);
        return dp[m][n];
    }

        //F(i,m,n)=max(F(i-1,m,n), F(i-1,m-mi, n-ni)+1) (m>mi, n>ni)
    //F(i, m, n)=F(i-1, m, n)
    //O(l*m*n)
    public int findMaxFormDP(String[] strs, int m, int n) {
       int[][] dplast=new int[m+1][n+1];
       int[][] dp=new int[m+1][n+1];
       for (int k=0; k<strs.length; k++){
           int n0=0;
           int n1;
           for(char c:strs[k].toCharArray()){
               if(c=='0') n0++;
           }
           n1=strs[k].length()-n0;

           for (int i = 0; i <= m; i++) {
               for (int j = 0; j <= n; j++) {
                   if(i>=n0&&j>=n1)
                       dp[i][j]=Math.max(dplast[i][j], dplast[i-n0][j-n1]+1);
                   else dp[i][j]=dplast[i][j];
               }
           }
           int[][] temp=dplast;
           dplast=dp;
           dp=temp;
       }
//        PrintUtils.print2DIntArray(dplast);
       return dplast[m][n];
    }


    public int findMaxForm(String[] strs, int m, int n) {
        return recursive(strs, 0, m, n, 0);
    }

    //TLE brote force
    int recursive(String[] strs, int i, int m, int n, int count){
        if(i==strs.length) return count;
        int m1=0,n1=0;
        for(char c:strs[i].toCharArray()){
            if(c=='0') m1++;
            else n1++;
        }
        if(m-m1>=0&&n-n1>=0) {
            int added = recursive(strs, i + 1, m-m1, n-n1, count+1);
            int unadded = recursive(strs, i + 1, m, n, count);
            return Math.max(added,unadded);
        }else {
            return recursive(strs, i + 1, m, n, count);
        }
    }

    String[] generate(int length) throws InterruptedException {
        String[] result=new String[length];
        Random rand=new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            int m=Math.abs(rand.nextInt(5));
            int n=Math.abs(rand.nextInt(5));
            StringBuilder sb=new StringBuilder();

            Thread t1=new Thread(() ->{
                    for (int j = 0; j < m; j++) {
                        sb.append("0");
                    }
            });
           Thread t2=new Thread(()->{
                    for (int j = 0; j < n; j++) {
                        sb.append("1");
                    }
                });
           t1.start(); t2.start();
           t1.join();t2.join();
           result[i]=sb.toString();
        }
        return result;
    }

}
