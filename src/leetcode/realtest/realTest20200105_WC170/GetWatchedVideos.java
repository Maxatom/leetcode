package leetcode.realtest.realTest20200105_WC170;

import utils.PrintUtils;

import java.util.*;

public class GetWatchedVideos {
    public static void main(String[] args) {
        GetWatchedVideos videos=new GetWatchedVideos();
        String[][] watchedVideos = new String[][]{{"A","B"},{"C"},{"B","C"},{"D"}}; int[][] friends = new int[][]{{1,2},{0,3},{0,3},{1,2}}; int id = 0, level = 1;
        PrintUtils.printList(videos.watchedVideosByFriends(arrayToList(watchedVideos),friends,id,level), p->p);
    }
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Map<String,Integer> map=new HashMap<>();
        boolean[] visited=new boolean[friends.length];
        Queue<Integer> queue=new LinkedList<>();
        queue.add(id);
        while (level>=0) {
            Queue<Integer> nqueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                int pid = queue.poll();
                if (visited[pid]) continue;
                visited[pid] = true;
                if(level==0) {
//                    System.out.println(watchedVideos.get(pid));
                    for (String s : watchedVideos.get(pid)) {
                        map.put(s, map.getOrDefault(s, 0) + 1);
                    }
                }
                for (int f : friends[pid]) nqueue.add(f);
            }
//            System.out.println(nqueue);
            queue=nqueue;
            level--;
        }
        Map.Entry<String,Integer>[] entries=new Map.Entry[map.entrySet().size()];
        int i=0;
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            entries[i++]=entry;
        }
        Arrays.sort(entries,(p1,p2)->{
            if(p1.getValue()==p2.getValue()){
                return p1.getKey().compareTo(p2.getKey());
            }else return p1.getValue()-p2.getValue();
        });
        List<String> res=new ArrayList<>();
        for(int j=0; j<entries.length;j++){
            res.add(entries[j].getKey());
        }
        return res;
    }

    static List<List<String>> arrayToList(String[][] watchedVedios){
        List<List<String>> list=new ArrayList<>();
        for(String[] strarr:watchedVedios){
            List<String> temp=new ArrayList<>();
            for(String str:strarr){
                temp.add(str);
            }
            list.add(temp);
        }
        return list;
    }
}
