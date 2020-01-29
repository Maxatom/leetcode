package leetcode.realtest.realTest20191229_WC169;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VerbalArithmeticPuzzle {
    public static void main(String[] args) {
        VerbalArithmeticPuzzle puzzle=new VerbalArithmeticPuzzle();
        String[] words = {"SEND","MORE"};String result = "MONEY";
//        words = new String[]{"SIX","SEVEN","SEVEN"}; result = "TWENTY";
        words = new String[]{"LEET","CODE"}; result = "POINT";
        System.out.println(puzzle.isSolvable(words, result));
    }
    public boolean isSolvable(String[] words, String result) {
        int wl=words.length;
        int[] arr=new int[26];
        Arrays.fill(arr,-1);
        return backtrace(words, 0, 0, arr, new int[wl], result);
    }
    boolean[] used=new boolean[10];
    boolean backtrace(String[] words, int w, int p, int[] arr, int[] nums,String result){
        int n=words.length;
        if(p==words[w].length()){
            if(w==n-1) {
                return check(nums, result, Arrays.copyOf(arr, arr.length));
            }else {
                w++; p=0;
            }
        }
        int orig=nums[w];
        char c=words[w].charAt(p);
        boolean res=false;
        if(arr[c-'A']!=-1){
            nums[w] = nums[w]*10 + arr[c-'A'];
            res = backtrace(words, w, p+1, arr, nums, result);
            nums[w] = orig;
        }else {
            for (int i = 0; i < 10; i++) {
                if(used[i]) continue;
                arr[c-'A']=i;
                nums[w] = nums[w]*10 + i;
                used[i]=true;
                res = res || backtrace(words, w, p+1, arr, nums, result);
                used[i]=false;
                arr[c-'A']=-1;
                nums[w]=orig;

            }
        }
        return res;
    }
    boolean check(int[] nums, String result, int[] arr){
        int sum=0;
        for(int num:nums) sum+=num;
        int x=0, n=result.length()-1;
        boolean[] usedc=Arrays.copyOf(used, used.length);
        do{
            x=sum%10;
            int c=result.charAt(n)-'A';
            if(arr[c]!=-1 && arr[c]!=x) return false;
            if(arr[c]==-1){
                if(usedc[x]) return false;
                else {
                    arr[c]=x;
                    usedc[x]=true;
                }
            }
            sum=sum/10; n--;
            if(sum==0&&n>=0 || sum!=0&&n<0) return false;
        }while (sum!=0);
        return true;
    }
}
