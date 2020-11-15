package leetcode.realtest.realTest20201003_DWC36;

import java.util.*;

public class FindServers {
    public static void main(String[] args) {
        FindServers find  = new FindServers();
        int k=3;int[] arrival = {1,2,3,4,5}, load = {5,2,3,3,3};
        System.out.println(find.busiestServers(k, arrival, load).toString());
        System.out.println(find.busiestServers1(k, arrival, load).toString());
        List<Integer> list = new LinkedList<>();
        list.remove(0);

    }
    //brute force
    public List<Integer> busiestServers1(int k, int[] arrival, int[] load) {
        int[] servers = new int[k];
        int[] cnt = new int[k];
        int n=arrival.length;
        int max = 0;

        for (int i = 0; i < n; i++) {
            int j=i%k;
            while (j-i%k < k && servers[j%k]>arrival[i]) j++;
            if(j-i%k>=k) continue;
            servers[j%k] = arrival[i]+ load[i];
            cnt[j%k]++;
            max=Math.max(max, cnt[j%k]);
        }
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<k; i++){
            if(cnt[i]==max) res.add(i);
        }
        return res;
    }
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        Queue<int[]> queue = new PriorityQueue<>((a,b)->a[1]-b[1]);
        int n=arrival.length;
        int[] cnt = new int[k];
        for(int i=0; i<k; i++){
            treeSet.add(i);
        }
        int max = 0;
        for(int i=0; i<n; i++){
            while (!queue.isEmpty() && queue.peek()[1]<=arrival[i]){
                treeSet.add(queue.poll()[0]);
            }
            if(treeSet.isEmpty()) continue;
            Integer server = treeSet.ceiling(i % k);
            if(server==null){
                server = treeSet.first();
            }
            treeSet.remove(server);
            queue.add(new int[]{ server, arrival[i]+load[i]});
            cnt[server]++;
            max=Math.max(cnt[server],max);
        }
//        System.out.println(Arrays.toString(cnt));
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<k; i++){
            if(cnt[i]==max) res.add(i);
        }
        return res;
    }
}
