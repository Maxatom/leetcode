package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;
import utils.PrintUtils;

/**
 * @author Shibing
 * @since 2018-12-17 09:53:34
 **/
public class DeleteColumnsToMakeSortedIII {
    public static void main(String[] args) {
        DeleteColumnsToMakeSortedIII sortedIII=new DeleteColumnsToMakeSortedIII();
        DeleteColumnsToMakeSortedII sortedII=new DeleteColumnsToMakeSortedII();
        String[] testcase=PrintUtils.convertStringToStringArray("[\"ghi\",\"def\",\"abc\"]");
//        String[] testcase=PrintUtils.convertStringToStringArray("[\"babca\",\"bbazb\"]");
//        String[] testcase=PrintUtils.convertStringToStringArray("[\"bgd\",\"bad\"]");
//        String[] testcase=PrintUtils.convertStringToStringArray("[\"edcba\"]");
        testcase=sortedII.generateCase(3,4);
        PrintUtils.printArray(testcase);
        System.out.println(sortedIII.minDeletionSize(testcase));
    }

    //DP
    public int minDeletionSize(String[] A) {
        int M=A[0].length();
        int[] dp=new int[M];
        dp[0]=1;
        int max=0;
        for (int i = 1; i < M; i++) {
            dp[i]=1;
           search: for (int j = 0; j < i; j++) {
                for(String s:A)
                    if(s.charAt(i)<s.charAt(j))  continue search;
                dp[i]=Math.max(dp[i],dp[j]+1);
            }
            max=Math.max(dp[i],max);
        }
        return M-max;
    }
    //找出字符串中每个元素右边比它大的第一个元素
    int[] generateAssendingSeq(String s){
        int[] result=new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            char c=s.charAt(i);
            for (int j = 0; j < i; j++) {
                if(result[j]==0&&c>=s.charAt(j)) result[j]=i;
            }
        }
        return result;
    }
}
