package leetcode.problems.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shibing
 * @since 2019-03-18 18:23:47
 **/
public class NQueensII {
    public static void main(String[] args) {
        NQueensII queens=new NQueensII();
        int n=4;
        n=1;
        n=2;
        n=3;
        n=8;
        System.out.println(queens.totalNQueens(n));
    }
    public int totalNQueens(int n) {
        List<boolean[]> list=new ArrayList<>();
        list.add(new boolean[n]); list.add(new boolean[2*n-1]); list.add(new boolean[2*n-1]);
        return recursive( 0, n, list);
    }
    public int recursive( int id, int n, List<boolean[]> list){
        if(id==n) return 1;
        boolean[] cols=list.get(0);
        boolean[] diag=list.get(1);
        boolean[] antidiag=list.get(2);
        int count=0;
        for (int i = 0; i < n; i++) {
            if(cols[i] || diag[id+i] || antidiag[id-i+n-1]) continue;
            cols[i]=true; diag[id+i]=true; antidiag[id-i+n-1]=true;
            count+=recursive( id+1, n, list);
            cols[i]=false; diag[id+i]=false; antidiag[id-i+n-1]=false;
        }
        return count;
    }
}
