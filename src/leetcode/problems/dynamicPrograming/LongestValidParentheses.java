package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Shibing
 * @since 2019-03-20 18:49:47
 **/
public class LongestValidParentheses {
    public static void main(String[] args) {
        LongestValidParentheses valid=new LongestValidParentheses();
        String s ="(()";
        s=""; //0
        s="("; //0
        s=")"; //0
        s=")("; //0
        s="()"; //2
        s="(()";//2
        s="())";//2
        s=")()";//2
        s="()(";//2
        s="()()"; //4
        s="(())";//4
        s=")()())";//4
        s="(((())))"; //8
        s="(((()))()";//8
        s="(((()))(())";//10
        s="((((()))(()))((())())";//20
//        s="((((()))(()))";//12
//        s="(()))((()))(()))";//12
//        s="()(())"; //6
//        s=")(((((()())()()))()(()))("; //22
//        System.out.println(valid.longestValidParentheses(s));
        System.out.println(valid.longestValidParentheses1(s));
        System.out.println(valid.longestValidParentheses2(s));
    }
    //dp(i,j) means the length of the longest valid parentheses between i~j
    //dp(i,j)=dp(i,j-1)+2 if dp(i,j-1)=dp(i+1,j-1) and s(i)='(' and s(j)=')'   or dp(i,j-1)=dp(i,j-2) and s(j-1)='(' and s(j)=')'
    // result : dp[0][N-1]
    public int longestValidParentheses3(String s) {
        if(s==null||s.length()==0) return 0;
        int L=s.length();
        int[][] dp=new int[L][L];
        char[] arr=s.toCharArray();
        char lp='(', rp=')';
        for (int i = 0; i < L-1; i++)
            dp[i][i+1]=arr[i]==lp&&arr[i+1]==rp?2:0;
        for (int d = 3; d < L; d++) {
            for (int i = 0; i < L-d; i++) {
                int j=i+d;
                int len=j-1-i;
                if(len==dp[i+1][j-1] && arr[i]==lp&&arr[j]==rp
                        || len==dp[i][j-2] && arr[j-1]==lp&&arr[j]==rp
                        || len==dp[i+2][j] && arr[i]==lp&&arr[i+1]==rp)
                    dp[i][j]=len+2;
                else dp[i][j]=Math.max(dp[i][j-1], dp[i+1][j]);
            }
        }
        PrintUtils.print2DIntArray(dp);
        return dp[0][L-1];
    }

    //O(N)
    public int longestValidParentheses2(String s) {
        Stack<Integer> stack=new Stack<>();
//        Integer[] top=new Integer[s.length()+1];
        int top1=-1;
        int temp1=-1;
        Integer[] top=new Integer[s.length()+1];
        Integer[] temp=new Integer[s.length()+1];
        int max=0;
        int j=-1;
        while (++j<s.length()){
            if(s.charAt(j)=='(') {
                stack.push(j);
                if(top[stack.size()]==null) {
                    top[stack.size()] = j;
                }else temp[stack.size()]=j;
            } else {
                if(stack.empty()){
                    Arrays.fill(top, null);
                    continue;
                }
                stack.pop();
                top[stack.size()+2]=null;
                max=Math.max(max, j-top[stack.size()+1]+1);
            }
        }
        return max;
    }

    //O(N^2) brute force  TLE
    public int longestValidParentheses1(String s) {
        Stack<Character> stack=new Stack<>();
        int max=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.length()-i<=max)  break;
            stack.clear();
            int j=i-1;
            while ((++j)<s.length()){
                if(s.charAt(j)=='(') stack.push('(');
                else {
                    if(stack.empty()) break;
                    stack.pop();
                }
                if(stack.empty())
                    max=Math.max(max, j-i+1);
            }
        }
        return max;
    }

    //dp(i,j)=true if dp(i+1,j-1) is valid and s(i)='(' and s(j)=')'   or  dp(i,k) is true and dp(k+1,j) is true (i<k<j-1)
    //O(N^3) TLE DP
    public int longestValidParentheses(String s) {
        if(s==null||s.length()==0) return 0;
        int L=s.length();
        boolean[][] dp=new boolean[L][L];
        char[] arr=s.toCharArray();
        char lp='(', rp=')';
        int max=0;
        for (int i = 0; i < L-1; i++)
            if(arr[i]==lp&&arr[i+1]==rp) {
                dp[i][i + 1] = true;
                max=2;
            }
        for (int d = 3; d < L; d+=2) {
            for (int i = 0; i < L-d; i++) {
                int j=i+d;
                if(dp[i+1][j-1] && arr[i]==lp&&arr[j]==rp) {
                    dp[i][j] = true;
                } else{
                    for (int k = i+1; k < j-1; k+=2)
                        if(dp[i][k]&&dp[k+1][j]) {
                            dp[i][j]=true;
                            break;
                        }
                }
                if(dp[i][j]) max=j-i+1;
            }
        }
        return max;
    }
}
