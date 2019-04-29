package leetcode.realtest.realTest20190428;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Shibing
 * @since 2019-04-28 10:51:51
 **/
public class EscapeaLargeMaze {
    public static void main(String[] args) {
        EscapeaLargeMaze maze=new EscapeaLargeMaze();
        int[][] blocked ={{0,1},{1,0}}; int[] source = {0,0}, target = {0,2};
        blocked = new int[][]{}; source = new int[]{0,0}; target = new int[]{999999,999999};
        blocked= new int[][]{{0,1},{1,1},{2,1}}; source=new int[]{0,0}; target=new int[]{2,2};
//        blocked= new int[][]{{0,1},{1,1},{2,1},{2,0}}; source=new int[]{0,0}; target=new int[]{2,2};
        System.out.println(maze.isEscapePossible(blocked, source, target));
//        System.out.println(maze.isEscapePossible1(blocked, source, target));
    }
    //counting steps, time consuming
    public boolean isEscapePossible1(int[][] blocked, int[] source, int[] target) {
        Set<Long> blockedSet=new HashSet<>();
        for(int[] p:blocked)
            blockedSet.add(p[0]*M+p[1]);
        return backTrace(blockedSet, target, source, new HashSet<>(), new int[1])
                && backTrace(blockedSet, source, target, new HashSet<>(), new int[1]);
    }
    long M=1000000l;
    int maxsteps=20000;
    int[][] dire=new int[][]{{1,0},{0,1},{0,-1},{-1,0}};
    public boolean backTrace(Set<Long> blocked, int[] t, int[] p, Set<Long> visited, int[] steps){
        if(steps[0]>=maxsteps || !visited.isEmpty() && p[0]==t[0] && p[1]==t[1]) return true;
        visited.add(p[0]*M+p[1]);
        steps[0]++;
        boolean pass=false;
        for(int[] d:dire) {
            int x=p[0]+d[0], y=p[1]+d[1];
            if (x>=0 && x<M && y>=0 && y<M && !visited.contains(x*M + y) && !blocked.contains(x * M + y)) {
                System.out.println(steps[0]+", x="+x+", y="+y);
                pass = pass || backTrace(blocked, t, new int[]{x, y}, visited, steps);
            }
        }
        return pass;
    }

    //toward to one direction most
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<Long> blockedSet=new HashSet<>();
        for(int[] p:blocked)
            blockedSet.add(p[0]*M+p[1]);
        return backTrace(blockedSet, source,target, source, new HashSet<>())
                && backTrace(blockedSet, target, source, target, new HashSet<>());
    }
    public boolean backTrace(Set<Long> blocked,int[] s, int[] t, int[] p, Set<Long> visited){
        if(p[0]>=s[0]+200 || p[0]<=s[0]-200 || p[1]>=s[1]+200 || p[1]<=s[1]-200 || !visited.isEmpty() && p[0]==t[0] && p[1]==t[1]) return true;
        visited.add(p[0]*M+p[1]);
        boolean pass=false;
        for(int[] d:dire) {
            int x=p[0]+d[0], y=p[1]+d[1];
            if (!visited.contains(x*M + y) && !blocked.contains(x * M + y) && x>=0 && x<M && y>=0 && y<M) {

                System.out.println(", x="+x+", y="+y);
                pass = pass || backTrace(blocked, s, t, new int[]{x, y}, visited);
            }
        }
        return pass;
    }
}
