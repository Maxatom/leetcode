package leetcode.realtest.realTest20200216_WC176;

import java.util.Arrays;

public class MaximumNumberOfEvents {
    public static void main(String[] args) {
        MaximumNumberOfEvents events=new MaximumNumberOfEvents();

    }
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (e1, e2)->e1[1]==e2[1]?e1[0]-e2[0]:e1[1]-e2[1]);
        int n=events.length;
        int cnt=0, s=events[0][0], end=events[n-1][1];
        int j=0;
        for(int i=s; i<=end; i++){
            for(;j<n;j++){
                if(events[j][0]<=i && i<=events[j][1]) {
                    cnt++; j++; break;
                }
            }
        }
        return cnt;
    }
}
