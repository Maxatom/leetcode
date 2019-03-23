package leetcode.realtest.realTest20190317;

import java.util.HashMap;
import java.util.Map;

/**
 * similar with two sum
 * @author shibing
 * @since 2019/3/17 10:53
 */
public class PairsofSongsWithTotalDurationsDivisibleby60 {
    public static void main(String[] args) {
        PairsofSongsWithTotalDurationsDivisibleby60 songs=new PairsofSongsWithTotalDurationsDivisibleby60();
        int[] time=new int[]{30,20,150,100,40};
        time=new int[]{60,60,60};
        time=new int[]{60};
        System.out.println(songs.numPairsDivisibleBy60(time));
        System.out.println(songs.numPairsDivisibleBy601(time));
    }


    public int numPairsDivisibleBy601(int[] time) {
        int[] rem=new int[60];
        int count=0;
        for (int i = 0; i < time.length; i++) {
            int rem1=time[i]%60;
            count+= rem[(60-rem1)%60];
            rem[rem1]++;
        }
        return count;
    }


    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map=new HashMap<>();
        map.put(time[0],1);
        int pairs=0;
        for (int i = 1; i < time.length; i++) {
            int n=60-time[i]%60;
            while (n<=500){
                if(map.containsKey(n)) pairs+=map.get(n);
                n+=60;
            }
            map.put(time[i], map.getOrDefault(time[i], 0)+1);
        }
        return pairs;
    }
}
