package leetcode.realtest.realTest20190224;

import utils.ThreeTuple;
import utils.Tuple;
import utils.PrintUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * @author shibing
 * @since 2019/3/2 23:46
 */
public class GridIllumination {
    public static void main(String[] args) throws IOException {
        GridIllumination grid=new GridIllumination();
        int N = 5; int[][] lamps = new int[][]{{0,0},{4,4}}, queries = new int[][]{{1,1},{1,0}};
//        N=5; lamps=new int[][]{{0,0},{0,4}}; queries=new int[][] {{0,4},{0,1},{1,4}};
//        N=5; lamps=new int[][]{{0,0},{0,4},{0,1},{0,2},{1,1}}; queries=new int[][] {{0,4},{0,1},{1,4}};
        N=5; lamps=new int[][]{}; queries=new int[][] {{0,4},{0,1},{1,4}};
        N=5; lamps=new int[][]{{0,0},{0,4},{0,1},{0,2},{1,1}}; queries=new int[][] {};
        ThreeTuple<Integer, int[][], int[][]> threeTuple=
        grid.readArrayFromFile("E:\\MyProjects\\springdemo\\testlearning\\src\\main\\java\\leetcode\\realTest20190224\\gridData");
        N=threeTuple.first; lamps=threeTuple.second; queries=threeTuple.third;
        PrintUtils.printArray(grid.gridIllumination1(N, lamps, queries));
//        PrintUtils.printArray(grid.gridIllumination(N, lamps, queries));
        System.out.println(Math.pow(10, 18)+","+Integer.MAX_VALUE);
    }

    //O(M)
    public int[] gridIllumination1(int N, int[][] lamps, int[][] queries) {
        List<Map<Integer, Integer>> maplist=new ArrayList<>();
        for (int i = 0; i < 4; i++) maplist.add(new HashMap<>());
        Set<String> lampset=new HashSet<>();
        for(int[] lamp:lamps){
            int[] key=new int[]{lamp[0], lamp[1], lamp[0]+lamp[1], lamp[0]-lamp[1]};
            for (int i = 0; i < 4; i++) {
                Map<Integer, Integer> map=maplist.get(i);
                map.put(key[i], map.getOrDefault(key[i], 0)+1);
            }
            lampset.add(lamp[0]+","+lamp[1]);
        }
        int[] ans=new int[queries.length];
        int i=0;
        for(int[] query:queries){
            if(maplist.get(0).containsKey(query[0]) || maplist.get(1).containsKey(query[1])
                    || maplist.get(2).containsKey(query[0]+query[1]) || maplist.get(3).containsKey(query[0]-query[1]))
                ans[i++]=1;
            else i++;
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    int[] point=new int[]{query[0]+j, query[1]+k};
                    if(lampset.contains(point[0]+","+point[1]))
                        turnOff(point, maplist, lampset);
                }
            }
        }
        return ans;
    }
    public void turnOff(int[] query,  List<Map<Integer, Integer>> maplist, Set<String> lampset){
        lampset.remove(query[0] + "," + query[1]);
        int[] key=new int[]{query[0], query[1], query[0]+query[1], query[0]-query[1]};
        for (int i = 0; i < 4; i++) {
            Map<Integer, Integer> map=maplist.get(i);
            if(map.get(key[i])==1) map.remove(key[i]);
            else map.put(key[i], map.get(key[i])-1);
        }
    }
    //O(NL)
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        int[] ans=new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q=queries[i];
            boolean flag=true;
            for (int j = 0; j < lamps.length; j++) {
                if(lamps[j]==null) continue;
                flag=false;
                if(q[0]==lamps[j][0] || q[1]==lamps[j][1] || Math.abs(q[0]-lamps[j][0]) == Math.abs(q[1]-lamps[j][1]))
                    ans[i]=1;
                //turn off lamp
                if(Math.abs(q[0]-lamps[j][0])<=1 && Math.abs(q[1]-lamps[j][1])<=1) lamps[j] =null;
            }
            if(flag) break;
        }
        return ans;
    }
    public ThreeTuple<Integer, int[][], int[][]> readArrayFromFile(String filePath) throws IOException {
        List<String> lines= Files.readAllLines(Paths.get(filePath) ,Charset.defaultCharset());
        int N=Integer.valueOf(lines.get(0));
        int[][] lamps=PrintUtils.convertStringTo2DIntArray(lines.get(1));
        int[][] queries=PrintUtils.convertStringTo2DIntArray(lines.get(2));
        return Tuple.tuple(N, lamps, queries);
    }
}
