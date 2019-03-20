package leetcode.realtest.realTest20190127;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/1/27 12:08
 */
public class MinimumCostForTickets {
    public static void main(String[] args) {
        MinimumCostForTickets tickets=new MinimumCostForTickets();
        int[] days=new int[]{1,4,6,7,8,20}, costs=  new int[]{2,7,15};
//        days=new int[]{1,2,3,4,5,6,7,8,9,10,30,31} ; costs=new int[]{2,7,15};
//        days=new int[]{1} ; costs=new int[]{2,3,4};
//        days=new int[]{1, 6} ; costs=new int[]{2,3,4};
//        days=new int[]{1, 10} ; costs=new int[]{2,3,4};
//        days=new int[]{1, 10} ; costs=new int[]{2,3,40};
//        days=new int[]{6,9,10,14,15,16,17,18,20,22,23,24,29,30,31,33,35,37,38,40,41,46,47,51,54,57,59,65,70,76,77,81,85,87,90,91,93,94,95,97,98,100,103,104,105,106,107,111,112,113,114,116,117,118,120,124,128,129,135,137,139,145,146,151,152,153,157,165,166,173,174,179,181,182,185,187,188,190,191,192,195,196,204,205,206,208,210,214,218,219,221,225,229,231,233,235,239,240,245,247,249,251,252,258,261,263,268,270,273,274,275,276,280,283,285,286,288,289,290,291,292,293,296,298,299,301,303,307,313,314,319,323,325,327,329,334,339,340,341,342,344,346,349,352,354,355,356,357,358,359,363,364};
//            costs=new int[]{21,115,345};
        System.out.println(tickets.mincostTickets(days, costs));
        System.out.println(tickets.mincostTickets1(days, costs));
    }


    //dp bottom-up
    public int mincostTickets2(int[] days, int[] costs) {
        int n=days[days.length-1];
        int[] dp=new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < days.length; i++)
            dp[days[i]]=1;
        dp[0]=0;
        for (int i = 1; i <= n; i++) {
            if(dp[i]==Integer.MAX_VALUE)
                dp[i]=dp[i-1];
            else {
                dp[i] = Math.min(dp[Math.max(0, i - 1)] + costs[0], dp[Math.max(0, i - 7)] + costs[1]);
                dp[i] = Math.min(dp[i], dp[i > 30 ? (i - 30) : 0] + costs[2]);
            }
        }
//        PrintUtils.printIntArray(dp);
        return dp[n];
    }

    public int mincostTickets1(int[] days, int[] costs) {
        return recursive(days, costs, 0, 0, 0);
    }
    Map<Integer, Integer> map=new HashMap<>();
    //dp top-down 2
    public int recursive(int[] days, int[] costs, int i, int ticket, int start){
        if(i>=days.length) return 0;
        int key= (i*10+ticket)*100+start;
        if(map.containsKey(key)) return map.get(key);
        int day=days[i]-days[start];
        int result;
        if(ticket==0 || ticket==1 && day>=7 || ticket==2 && day>=30) {
            int t0 = costs[0]+recursive(days, costs, i + 1, 0, i );
            int t1 = costs[1]+recursive(days, costs, i + 1, 1, i );
            int t2 = costs[2]+recursive(days, costs, i + 1, 2, i );
            result= Math.min(t0, Math.min(t1,t2));
        }else {
            result= recursive(days, costs, i+1, ticket, start);
        }
        map.put(key, result);
        return result;
    }


    public int mincostTickets(int[] days, int[] costs) {
        int[] memo=new int[days.length];
        return mincostTickets(days, costs, 0, memo);
    }
    //dp top-down
    public int mincostTickets(int[] days, int[] costs, int i, int[] memo) {
        if(i>=days.length) return 0;
        if(memo[i]!=0) return memo[i];
        int one, week, month;
        one=costs[0]+mincostTickets(days, costs, i+1, memo);

        int j=i+1;
        while (j<days.length && days[j]-days[i]<7) j++;
        week=costs[1]+mincostTickets(days, costs, j, memo);

        j=i+1;
        while (j < days.length && days[j]-days[i]<30) j++;
        month=costs[2]+mincostTickets(days, costs, j, memo);
        int result=Math.min(Math.min(one, week), month);
        memo[i]=result;
        return result;
    }
}
