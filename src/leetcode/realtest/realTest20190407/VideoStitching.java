package leetcode.realtest.realTest20190407;

import utils.PrintUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author shibing
 * @since 2019/4/7 17:28
 */
public class VideoStitching {
    public static void main(String[] args) {
        VideoStitching video=new VideoStitching();
        int[][] clips=new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}}; int T=10;
        clips=new int[][]{{0,1},{1,2}}; T=5;
        clips=new int[][]{{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}}; T = 9;
        clips=new int[][]{{0,4},{2,8}}; T=5;
//        clips=new int[][]{{0,2},{4,5}}; T=2;
//        clips=new int[][]{{8,10},{17,39},{18,19},{8,16},{13,35},{33,39},{11,19},{18,35}}; T=20;
        System.out.println(video.videoStitching(clips,T));
        System.out.println(video.videoStitching1(clips,T));
    }

    //greedy
    public int videoStitching1(int[][] clips, int T) {
        Arrays.sort(clips, Comparator.comparingInt(p->p[0]));
        int curend=0, maxend=0, cnt=0;
        for (int i = 0; i < clips.length; i++) {
            int j=i;
            while (j<clips.length && clips[j][0]<=curend){
                maxend=Math.max(maxend, clips[j][1]);
                j++;
            }
            if(j==i) return -1;
            curend=maxend;
            cnt++;
            if(curend>=T) break;
            i=j-1;
        }
        return curend<T?-1:cnt;
    }

    //dp   similar with LongestIncreasingSubSequence
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, (p1,p2)->p1[0]!=p2[0]?(p1[0]-p2[0]):p1[1]-p2[1]);
        int[] dp=new int[clips.length];
        int min=Integer.MAX_VALUE;
        if(clips[0][0]>0) return -1;
        for (int i = 0; i < clips.length; i++) {
            if(clips[i][0]==0){
                dp[i]=1;
            }else {
                dp[i] = Integer.MAX_VALUE;
                for (int j = i - 1; j >= 0; j--) {
                    if (clips[j][1] >= clips[i][0] && dp[j] != -1)
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
            if(dp[i]==Integer.MAX_VALUE) dp[i]=-1;
            else {
                if(clips[i][1]>=T)
                    min=Math.min(min, dp[i]);
            }
        }
        return min==Integer.MAX_VALUE?-1:min;
    }
}
