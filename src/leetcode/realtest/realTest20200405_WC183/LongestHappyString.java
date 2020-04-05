package leetcode.realtest.realTest20200405_WC183;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static utils.Utils.max;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/4/516:13
 */
public class LongestHappyString {
    public static void main(String[] args) {
        LongestHappyString string=new LongestHappyString();
        int a = 1, b = 1, c = 7;
        System.out.println(string.longestDiverseString(a,b,c));
    }

    public String longestDiverseString(int a, int b, int c) {
        int[][] arr={{a,'a'},{b,'b'},{c,'c'}};
        StringBuilder sb=new StringBuilder();
        int i=0;
        while (i<a+b+c) {
            Arrays.sort(arr, Comparator.comparingInt(x -> x[0]));
            if(i>1&&sb.charAt(i-1)==sb.charAt(i-2)&&sb.charAt(i-1)==arr[2][1]){
                if(arr[1][0]==0) break;
                sb.append((char)arr[1][1]);
                arr[1][0]--;
            }else {
                if(arr[2][0]==0) break;
                sb.append((char)arr[2][1]);
                arr[2][0]--;
            }
            i++;
        }
        return sb.toString();
    }

}
