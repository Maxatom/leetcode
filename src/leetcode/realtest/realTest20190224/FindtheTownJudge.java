package leetcode.realtest.realTest20190224;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 有向无环图
 * directed graph
 * @author shibing
 * @since 2019/2/24 10:31
 */
public class FindtheTownJudge {
    public static void main(String[] args) {
        FindtheTownJudge judge=new FindtheTownJudge();
        int[][] trust=new int[][]{{1,2}}; int N=2; //2
        trust=new int[][]{{1,3},{2,3}}; N=3; //3
        trust=new int[][]{{1,3},{2,3},{3,1}}; N=3; //-1
        trust=new int[][]{{1,2},{2,3}}; N=3; //-1
        trust=new int[][]{{1,3},{1,4},{2,3},{2,4},{4,3}}; N=4; //3
        System.out.println(judge.findJudge(N,trust));
        System.out.println(judge.findJudge1(N,trust));
    }

    //Directed graph
    public int findJudge1(int N, int[][] trust) {
        int[] count=new int[N+1];
        for(int[] t:trust){
            count[t[0]]--;
            count[t[1]]++;
        }
        for (int i = 1; i < count.length; i++)
            if(count[i]==N-1) return i;
        return -1;
    }

    public int findJudge(int N, int[][] trust) {
        int[] count=new int[N+1];
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i < trust.length; i++) {
            count[trust[i][1]]++;
            set.add(trust[i][0]);
        }
        for (int i = 1; i < count.length; i++)
            if(count[i]==N-1 && !set.contains(i)) return i;
        return -1;
    }
}
