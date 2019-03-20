package leetcode.realtest.realTest20190203;

import leetcode.common.Interval;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shibing
 * @since 2019/2/3 11:53
 */
public class IntervalListIntersections {
    public static void main(String[] args) {
        IntervalListIntersections interval=new IntervalListIntersections();
        int[][] A=new int[][]{{0,2},{5,10},{13,23},{24,25}}, B=new int[][]{{1,5},{8,12},{15,24},{25,26}};
        A=new int[0][]; B=new int[0][];
        A=new int[0][]; B=new int[][]{{1,2}};
        A=new int[][]{{1,2}}; B=new int[0][];
        A=new int[][]{{1,2}}; B=new int[][]{{1,2}};
        A=new int[][]{{1,2}}; B=new int[][]{{3,4}};
        A=new int[][]{{1,3}}; B=new int[][]{{3,4}};
        A=new int[][]{{1,3}}; B=new int[][]{{2,4}};
        Interval[] AA=interval.convert2DArrayToIntervals(A), BB=interval.convert2DArrayToIntervals(B);
        Interval[] result=interval.intervalIntersection(AA,BB);
        PrintUtils.printArray(result, p->"["+p.start+", "+p.end+"]");
    }
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        List<Interval> ans=new ArrayList<>();
        int i=0, j=0;
        while (i<A.length&&j<B.length){
            if(A[i].end<B[j].start){ i++; continue; }
            else if(B[j].end<A[i].start) {j++; continue; }
            ans.add(new Interval(Math.max(A[i].start,B[j].start), Math.min(A[i].end, B[j].end)));
            if(A[i].end<B[j].end) i++;
            else if(A[i].end>B[j].end) j++;
            else {i++; j++; }
        }
        Interval[] res=new Interval[ans.size()];
        int k=0;
        for(Interval inter:ans)
            res[k++]=inter;
        return res;
    }
    public Interval[] convert2DArrayToIntervals(int[][] A){
        Interval[] res=new Interval[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i]=new Interval(A[i][0], A[i][1]);
        }
        return res;
    }
}
