package leetcode.realtest.realTest20200209_WC175;

import java.util.*;

public class TweetCounts {
    public static void main(String[] args) {

    }
    Map<String, TreeSet<Integer>> map;
    public TweetCounts() {
        map=new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        if(!map.containsKey(tweetName)){
            map.put(tweetName, new TreeSet<>());
        }
        map.get(tweetName).add(time);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> res=new ArrayList<>();
        int delta=0;
        if(freq.equals("minute")) delta=60;
        else if(freq.equals("hour")) delta=60*60;
        else if(freq.equals("day")) delta=24*60*60;
        for (int i = startTime; i <= endTime; i=i+delta) {
            int size=map.get(tweetName).subSet(i, Math.min(i+delta, endTime+1)).size();
            res.add(size);
        }
        return res;
    }
}

